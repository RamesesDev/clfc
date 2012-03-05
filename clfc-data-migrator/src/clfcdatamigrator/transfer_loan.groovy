/**
 * Simple migration groovy script for CLFC dbf loan data
 *
 * @author jaycverg
 *
 * injected variables:
 * - sqlCtx
 * - logger
 * - codeFormatter
 * - rowPointer
 */

import com.rameses.util.*;
import com.rameses.sql.*;
import java.io.*;
import java.sql.ResultSet;
import clfcdatamigrator.DataHandler;


/*================ MIGRATE LOAN INFORMATION ========================*/
logger.log("migrating loan information: ");

//-- helper that gracefully handles exceptions -----
def safeExecute = { id, closure ->
    try {
        closure();
    }
    catch(e) {
        logger.log("  error saving $id: $e.message");

        def msg = (e.message+'').toLowerCase();
        if( msg.contains('duplicate entry') ) {}//do nothing
        else if( msg.contains('100:') ) {} //customer not found. just by pass
        else {
            throw e;
        }
    }
};

int row = rowPointer.getLastRow("loan")+1;
def qry = sqlCtx.createNamedQuery('temp:get_loan_list').setFirstResult(row);

qry.fetchHandler = new DataHandler({ item ->
    println "  processing row: $row";

    safeExecute("loan $item.LOANNOS", {
        def data = buildLoanInfo(item);
        def docNo = data.remove('docNo');
        
        sqlCtx.createNamedExecutor('loan:insert').setParameters(data).execute();
        sqlCtx.createNamedExecutor('loan:update_docno').setParameter(1, docNo).execute();
        logger.log("  loan $item.LOANNOS saved.", false);
    });

    rowPointer.updateLastRow('loan', row);
    row++;
});

qry.resultList;



/**============================================================= 
 * HELPER METHOD 
 *==============================================================*/

def buildLoanInfo( item ) {
    def cust = sqlCtx.createNamedQuery('contact:get_customer').setParameter(1,item.CLIENTCODE).singleResult;
    if( !cust ) throw new Exception("100: Customer $item.CLIENTCODE not found.");
    
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
	business:    [:],
        isMigrated:  true,
    ];

    def docNo = sqlCtx.createNamedQuery('loan:get_docno').singleResult;
    docNo = docNo? docNo.value+1 : 1;
    
    return [
        objid:            'LAPP-' + new java.rmi.server.UID(),
        appno:            System.getProperty('branch.code') + codeFormatter.format(docNo),
        docNo:            docNo,
        clientcode:       item.CLIENTCODE,
        loanno:           item.LOANNOS,
        borrowername:     cust.name,
        fullborrowername: cust.name,
        state:            'MIGRATED',
        loancount:        1,
        routecode:        null,
        routedescription: null,
        dtfiled:          item.RECDATE,
        dtreleased:       item.LOANDATE,
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

def roundDecimal( amount ) {
    def dec = amount - (int) amount;
    def offset = dec - fixDecimal(dec, '0.0')
    return amount + ((offset>0)? 0.05 - offset : -(offset));
}

def fixDecimal( amount, format='##0.00' ) {
    return NumberUtil.formatDecimal(amount, format);
}



