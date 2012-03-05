/**
 * Simple migration groovy script for CLFC dbf data
 *
 * @author jaycverg
 *
 * injected variables:
 * - sqlCtx
 * - logger
 */

import com.rameses.util.*;
import com.rameses.sql.*;
import java.io.*;
import java.sql.ResultSet;
import clfcdatamigrator.DataHandler;


//global variable
boolean done;

/*================ MIGRATE CONTACT INFORMATION ========================*/
logger.log("migrating client information: ");
done = false;
while( !done ) 
{
    done = true;
    def qry = sqlCtx.createNamedQuery('temp:get_client_list');

    qry.fetchHandler = new DataHandler({ item ->
        done = false;
        def data = buildContactInfo(item);
        def info = data.info;
        try {
            data.info = ObjectSerializer.instance.toString(info);
            sqlCtx.createNamedExecutor('contact:insert').setParameters(data).execute();
            sqlCtx.createNamedExecutor('contact:insert_name_idx').setParameters(info).execute();
            sqlCtx.createNamedExecutor('temp_processed:insert_client').setParameters(item).execute();
            logger.log("  client $item.CLIENTCODE saved.", false);
        }
        catch(e) {
            logger.log("  error saving client $item.CLIENTCODE: $e.message");
            try {
                sqlCtx.createNamedExecutor('temp_unprocessed:insert_client').setParameters(item).execute();
            }
            catch(ex) {
                if((e.message+'').toLowerCase().contains('duplicate entry') ) {}//do nothing
                else {
                    throw ex;
                }
            }
        }
        sqlCtx.createNamedExecutor('temp:remove_client').setParameter(1,data.clientcode).execute();
    });
    
    qry.resultList;
}

/*================ MIGRATE LOAN INFORMATION ========================*/
logger.log("migrating loan information: ");
done = false;
while( !done ) 
{
    done = true;
    def qry = sqlCtx.createNamedQuery('temp:get_loan_list');
    
    qry.fetchHandler = new DataHandler({ item ->
        done = false;
        try {
            def data = buildLoanInfo(item);
            sqlCtx.createNamedExecutor('loan:insert').setParameters(data).execute();
            sqlCtx.createNamedExecutor('temp_processed:insert_loan').setParameters(item).execute();
            logger.log("  loan $item.LOANNOS saved.", false);
        }
        catch(e) {
            logger.log("  error saving loan $item.LOANNOS: $e.message");
            try {
                sqlCtx.createNamedExecutor('temp_unprocessed:insert_loan').setParameters(item).execute();
            }
            catch(ex) {
                if((e.message+'').toLowerCase().contains('duplicate entry') ) {}//do nothing
                else {
                    throw ex;
                }
            }
        }
        sqlCtx.createNamedExecutor('temp:remove_loan').setParameter(1,item.LOANNOS).execute();
    });
    
    qry.resultList;
}

/*================ MIGRATE LOAN PAYMENTS ========================*/
logger.log("migrating loan payments: ")
done = false;
while( !done ) 
{
    done = true;
    def qry = sqlCtx.createNamedQuery('temp:get_loan_payment_list');
    
    qry.fetchHandler = new DataHandler({ item ->
        done = false;
        try {
            sqlCtx.createNamedExecutor('payment:insert').setParameters(item).execute();
            sqlCtx.createNamedExecutor('temp_processed:insert_payment').setParameters(item).execute();
            logger.log("  loan payment $item.refno $item.date saved.", false);
        }
        catch(e) {
            logger.log("  error saving loan payment $item.refno $item.date: $e.message");
            try {
                sqlCtx.createNamedExecutor('temp_unprocessed:insert_payment').setParameters(item).execute();
            }
            catch(ex) {
                if((e.message+'').toLowerCase().contains('duplicate entry') ) {}//do nothing
                else {
                    throw ex;
                }
            }
        }
        sqlCtx.createNamedExecutor('temp:remove_loan_payment').setParameters(item).execute();
    });
    
    qry.resultList;
}

/*================ UPDATE LOAN LEDGERS ========================*/
logger.log("updating loan ledgers: ")
done = false;
while( !done ) 
{
    done = true;
    def qry = sqlCtx.createNamedQuery('ledger:get_unprocessed_list');
    
    qry.fetchHandler = new DataHandler({ item-> 
        done = false;
        println "processing ledger for $item.appno";
        def ledger = ObjectDeserializer.instance.read( item.ledger );
        
        def itmQry = sqlCtx.createNamedQuery('ledger:get_payments').setParameter(1, item.appno);
        
        itmQry.fetchHandler = new DataHandler({ payment ->
            println "  processing payment $payment.refno $payment.date";
            updateLedgerInfo( ledger, payment );
        });

        itmQry.resultList;
        
        //update ledger
        item.ledger = ObjectSerializer.instance.toString( ledger );
        sqlCtx.createNamedExecutor('ledger:update_processed').setParameters(item).execute();
        
        def p = [appid: item.objid, appno: item.appno];
        sqlCtx.createNamedExecutor('ledger:update_payments_appid').setParameters(p).execute();
    });
    
    qry.resultList;
}



/**============================================================= 
 * HELPER METHOD 
 *==============================================================*/

def buildContactInfo( item ) {
    def info = [
        objid:     'CONT' + new java.rmi.server.UID(),
        contactno: item.CLIENTCODE,
        name:      item.NAME,
        firstname: '',
        lastname:  '',
        address:   [address1: item.RESADDRESS],
        gender:    item.SEX,
        birthdate: item.BIRTHDATE,
        phone:     item.TELNO,
    ];
        
    return [
        objid:      info.objid,
        contactno:  info.contactno,
        clientcode: item.CLIENTCODE,
        schemaname: 'contact:contact',
        version:    '1.0',
        info:       info,
        state:      'PENDING',
    ];
}

def buildLoanInfo( item ) {
    def cust = sqlCtx.createNamedQuery('contact:get_customer').setParameter(1,item.CLIENTCODE).singleResult;
    if( !cust ) throw new Exception("Customer $item.CLIENTCODE not found.");
    
    cust = ObjectDeserializer.instance.read( cust.info );
    def loanInfo = [
        route: [:],
        loanamount: item.PRINCIPAL,
        producttype:[
            code:          "DAILY",
            term:          item.TERM,
            interestrate:  item.RATE,
            pastduerate:   6.00,
            surchargerate: 3.00,
            absentpenalty: 3.00,
            underpaymentpenalty:3.00
        ],
        loanpurpose: '',
        amountapproved: item.PRINCIPAL
    ];
    
    def ledger = buildLedgerInfo( item, loanInfo );
    
    def extended = [
        jointBorrowerList:[],
	coMakerList: [],
	connection:  [:],
	hasBusiness: 0,
	employed:    0,
	employment:  [:],
	business:    [:]        
    ];
    
    return [
        objid:            'LAPP-' + new java.rmi.server.UID(),
        appno:            item.LOANNOS,
        loanno:           item.LOANNOS,
        borrowername:     cust.name,
        fullborrowername: cust.name,
        state:            'MIGRATED',
        loancount:        1,
        routecode:        null,
        routedescription: null,
        dtfiled:          item.RECDATE,
        dtreleased:       null,
        approvedate:      null,
        mode:             'CAPTURE',
        branchid:         System.getProperty('branch.code'),
        branchcode:       System.getProperty('branch.code'),
        cirecommendation: '[:]',
        loaninfo:         ObjectSerializer.instance.toString(loanInfo),
        borrower:         ObjectSerializer.instance.toString(cust),
        propertylist:     '[]',
        vehiclelist:      '[]',
        appliancelist:    '[]',
        comments:         '[]',
        ledger:           ObjectSerializer.instance.toString(ledger),
        extended:         ObjectSerializer.instance.toString(extended)
    ];
}

def buildLedgerInfo( item, info ) {
    def principal = info.amountapproved;
    def start_date = DateUtil.add(item.LOANDATE, '1d');
    def prodType = info.producttype;
    def int_rate = fixDecimal( prodType.interestrate / 100, '0.0000' );
    
    return [
        loanAmount:        principal,
        principalBalance:  principal,
        dtcreated:         item.LOANDATE,
        startDate:         start_date,
        maturityDate:      item.DUEDATE,
        nextBillDate:      start_date,
        lastDatePaid:	   null,

        dailyPayment:      fixDecimal( ((principal * int_rate * (prodType.term/30)) + principal) / prodType.term ),
        dailyInt:          roundDecimal( fixDecimal( principal * int_rate / 30 ) ),
        dailyPastdue:       0.00,

        productType:       prodType.code,
        term:              prodType.term,
        interestrate:      int_rate,
        absentrate:        fixDecimal( prodType.absentpenalty/100, '0.0000' ),
        underpaymentrate:  fixDecimal( prodType.underpaymentpenalty/100, '0.0000' ),
        pastduerate :      fixDecimal( prodType.pastduerate/100, '0.0000' ),

        fullyPaidAmt:      0.00, //last payment that fully pays the total balance
        dateFullyPaid:     null,
        totalPaid:         0.00,
        totalPrincipalPaid:0.00,
        totalInterestPaid: 0.00,
        totalPenaltyPaid:  0.00,

        totalPenalty:      0.00,
        totalPenalty_wcup: 0.00, // w/ cover-up (absent_penalty)
        totalPenalty_wocup:0.00, // w/o cover-up (absent_penalty + underpayment_penalty)
        totalAbsent:       0,
        totalUnderpayment: 0,
    ];
}

void updateLedgerInfo( ledger, payment ) {
    ledger.principalBalance -= payment.principal;
    ledger.totalPrincipalPaid += payment.principal;
    ledger.totalInterestPaid += payment.interest;
    
    if( payment.absent_penalty )
        ledger.totalPenaltyPaid += payment.absent_penalty;
    if( payment.underpayment_penalty )
        ledger.totalPenaltyPaid += payment.underpayment_penalty;
    
    ledger.totalPaid += payment.payment;
    
    ledger.lastDatePaid = payment.date;
    ledger.nextBillDate = DateUtil.add( parseDate(payment.date), '1d' );
}

def parseDate( date ) {
    def dt_formatter = new java.text.SimpleDateFormat('yyyy-MM-dd');
    def strDate = date instanceof String? date : dt_formatter.format( date );
    return dt_formatter.parse( strDate );
}

def roundDecimal( amount ) {
    def dec = amount - (int) amount;
    def offset = dec - fixDecimal(dec, '0.0')
    return amount + ((offset>0)? 0.05 - offset : -(offset));
}

def fixDecimal( amount, format='##0.00' ) {
    return NumberUtil.formatDecimal(amount, format);
}



