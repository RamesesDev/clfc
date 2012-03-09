package loan.reports;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

/**
 * @author jaycverg
 */
public class AbstractReportController 
{
    def report;
    
    boolean printView;
    
    def formActions = [
        new Action(name:'_close', caption:'Close',   mnemonic:'c', immediate:true),
        new Action(name:'doBack', caption:'Back',    mnemonic:'b', visibleWhen:'#{printView}'),
        new Action(name:'view',   caption:'Preview', mnemonic:'v', visibleWhen:'#{!printView}'),
    ];
    
    def getCustName( cust ){
        return cust.lastname + ', ' + cust.firstname + (cust.middlename? " ($cust.middlename)" : "")
    }
    
    //override these methods if needed
    def getData() {
        def data=[:];
        def entity;
        data.putAll(entity);
        if ( data.loancount == 1 )
            data.appType = "NEW";
        else
            data.appType = "RENEWAL" + ' ' + entity.loancount;

        data.borrowerName2 = getCustName( data.borrower );
        if(data.routedescription != null)
                data.routedescription = data.routedescription;
        //if(data.marketedby!=null)
          //      data.marketedby = data.marketedby;
        
        if( entity.marketedby !=null )
            data.marketedby = data.marketedby;
        else data.marketedby = '';
        if( entity.marketedby2 !=null )
            data.marketedby2 = data.marketedby2;
        else data.marketedby2 = '';
        data.marketed = data.marketedby +" and "+ data.marketedby2;
    }
    def getParameters() {}
    def getReportName() {}
    def getSubReports() {}
    def back() { return 'default'; }
    
    def view() {
        report = [
            getReportData: { getData() },
            getParameters: { getParameters() },
            getReportName: { getReportName() },
            getSubReports: { getSubReports() }
        ] as ReportModel;
        
        report.viewReport();
        printView = true;
        
        return 'print';
    }
    
    final def doBack() {
        printView = false;
        return back();
    }
    
}

