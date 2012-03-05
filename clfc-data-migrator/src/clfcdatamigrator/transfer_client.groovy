/**
 * Simple migration groovy script for CLFC dbf client data
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


/*================ MIGRATE CONTACT INFORMATION ========================*/
logger.log("migrating client information: ");

//-- helper that gracefully handles exceptions -----
def safeExecute = { id, closure ->
    try {
        closure();
    }
    catch(e) {
        logger.log("  error saving $id: $e.message");

        if((e.message+'').toLowerCase().contains('duplicate entry') ) {}//do nothing
        else {
            throw e;
        }
    }
};

//-- process items -----
int row = rowPointer.getLastRow('client')+1;
def qry = sqlCtx.createNamedQuery('temp:get_client_list').setFirstResult(row);

qry.fetchHandler = new DataHandler({ item ->

    println "  processing row: $row";

    def data = buildContactInfo(item);
    def info = data.info;

    data.info = ObjectSerializer.instance.toString(info);
    
    safeExecute("client : $item.CLIENTCODE", {
        sqlCtx.createNamedExecutor('contact:insert').setParameters(data).execute();
        logger.log("  client $item.CLIENTCODE saved.", false);
    });
    
    safeExecute("client name index: $item.CLIENTCODE", {
        sqlCtx.createNamedExecutor('contact:insert_name_idx').setParameters(info).execute();
        logger.log("  client name index $item.CLIENTCODE saved.", false);
    });    
    
    rowPointer.updateLastRow('client', row);
    row++;
});

qry.resultList;



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
        dtfiled:    item.RECDATE,
        schemaname: 'contact:contact',
        version:    '1.0',
        info:       info,
        state:      'PENDING',
    ];
}
