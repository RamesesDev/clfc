/**
 * Simple migration groovy script for CLFC dbf loan data
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


/*================ MIGRATE LOAN INFORMATION ========================*/
logger.log("migrating loan payment information: ");

//-- helper that gracefully handles exceptions -----
def safeExecute = { id, closure ->
    try {
        closure();
    }
    catch(e) {
        logger.log("  error saving $id: $e.message");

        def msg = (e.message+'').toLowerCase();
        if( msg.contains('duplicate entry') ) {}//do nothing
        else {
            throw e;
        }
    }
};

int row = rowPointer.getLastRow("loan_payment")+1;
def qry = sqlCtx.createNamedQuery('payment:get_applications').setFirstResult(row);

qry.fetchHandler = new DataHandler({ item ->
    println "  processing row: $row";

    safeExecute("loan payment for appno[$item.appno], loanno: [$item.loanno]", {
        sqlCtx.createNamedExecutor('payment:migrate').setParameter('loanno', item.loanno).execute();
        logger.log("  loan payment for appno[$item.appno], loanno: [$item.loanno] saved.", false);
    });

    rowPointer.updateLastRow('loan_payment', row);
    row++;
});

qry.resultList;


