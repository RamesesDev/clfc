/**
 * Simple groovy script to update migrated loans' ledger info
 *
 * @author jaycverg
 *
 * injected variables:
 * - sqlCtx
 * - logger
 * - rowPointer
 */

import com.rameses.util.*;
import com.rameses.sql.*;
import java.io.*;
import java.sql.ResultSet;
import clfcdatamigrator.DataHandler;


/*================ UPDATE LOAN LEDGERS ========================*/
logger.log("updating loan ledgers: ")


int row = rowPointer.getLastRow('loan_ledger_build')+1;
def qry = sqlCtx.createNamedQuery('ledger:get_unprocessed_list').setFirstResult(row);

qry.fetchHandler = new DataHandler({ item-> 
    println "processing row: $row,  ledger for $item.appno";

    def ledger = ObjectDeserializer.instance.read( item.ledger );
    def info =   ObjectDeserializer.instance.read( item.loaninfo );
    resetLedgerInfo( item, ledger, info );

    def filter = ['date > $P{dtreleased}'];
    if( item.nextrelease ) filter << 'date < $P{nextrelease}';
    
    filter = [FILTER: ' and ' + filter.join(' and ')];
    def itmQry = sqlCtx.createNamedQuery('ledger:get_payments').setVars(filter).setParameters(item);
    def itemSE = sqlCtx.createNamedExecutor('ledger:update_payment_pbalance');
    
    itmQry.fetchHandler = new DataHandler({ payment ->
        println "  processing payment $payment.refno $payment.date";
    
        updateLedgerInfo( ledger, payment );
        payment.principal_balance = ledger.principalBalance;
        itemSE.setParameters(payment).addBatch();
    });
    itmQry.resultList;

    //batch execute ledger items update
    println "  updating running balances of items";
    itemSE.execute();
    
    //update ledger
    item.ledger = ObjectSerializer.instance.toString( ledger );
    sqlCtx.createNamedExecutor('ledger:update_processed').setParameters(item).execute();

    item.appid = item.objid;
    sqlCtx.createNamedExecutor('ledger:update_payments_appid').setVars(filter).setParameters(item).execute();
    
    rowPointer.updateLastRow('loan_ledger_build', row);
    row++;
});

qry.resultList;



/**============================================================= 
 * HELPER METHOD 
 *==============================================================*/

void resetLedgerInfo( item, ledger, info ) {
    def principal = info.amountapproved;
    def start_date = DateUtil.add(item.dtreleased, '1d');
    
    ledger.putAll([
        principalBalance:  principal,
        nextBillDate:      start_date,
        lastDatePaid:	   null,

        totalPaid:         0.00,
        totalPrincipalPaid:0.00,
        totalInterestPaid: 0.00,
        totalPenaltyPaid:  0.00,
        totalPenalty:      0.00
    ]);
}


void updateLedgerInfo( ledger, payment ) {
    ledger.principalBalance -= payment.principal;
    ledger.totalPrincipalPaid += payment.principal;
    ledger.totalInterestPaid += payment.interest;
    
    if( payment.absent_penalty ) {
        ledger.totalPenalty += payment.absent_penalty;
        ledger.totalPenaltyPaid += payment.absent_penalty;
    }
    if( payment.underpayment_penalty ) {
        ledger.totalPenalty += payment.underpayment_penalty;
        ledger.totalPenaltyPaid += payment.underpayment_penalty;
    }
    
    ledger.totalPaid += payment.payment;
    
    ledger.lastDatePaid = payment.date;
    ledger.nextBillDate = DateUtil.add( parseDate(payment.date), '1d' );
}

def parseDate( date ) {
    def dt_formatter = new java.text.SimpleDateFormat('yyyy-MM-dd');
    def strDate = date instanceof String? date : dt_formatter.format( date );
    return dt_formatter.parse( strDate );
}



