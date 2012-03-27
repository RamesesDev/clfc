package loan.ui;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.common.*;
import java.io.*;
import java.text.*;
import java.math.*;
import com.rameses.io.*;


public abstract class AbstractLoanAppController {

    def report;
    def itemReport=["Client Information Sheet", "Loan Application", "Loan Disclosure", "Loan Release Voucher"];
    //def itemReport=["Client Information Sheet", "Loan Application" ];
    def _printOut;
    def entity;
    def mode;
    
    def _user;
    
    def getUser() {
        if( _user ) return _user;
        
        return (_user = ScriptProvider.instance.create('User'));
    }

    abstract def getService();
    
    
    def getApplicationType() {
        def lc = (entity.loancount)? entity.loancount : 1;
        if( mode == "renew" ) lc++;
        
        return lc == 1 ? 'New Application' : 'Renewal ' + (lc-1);
    }

    void reportView() {
        report = new ApplicationReportModel(printOut: printOut, entity: entity);
    }

    def printData(){
        printOut="Client Information Sheet";
        report.viewReport();
        return "page2";
    }
    
    void setPrintOut(e){
        _printOut=e;
        if (!printOut) return;
        reportView();
        report.viewReport();
    }

    def getPrintOut(){
        return _printOut;
    }  

    def closeReport(){
        return "_close";
    }

    /*------ APPLICATION WORKFLOW SUPPORT ----------*/
    def forInspection(){
        return confirm({ message ->
            def p = [comment: message, objid: entity.objid];
            entity.putAll( service.submitForInspection(p) ); 
            if( selectHandler ) selectHandler(entity);
                binding.refresh();
        });
    }
 
    def forFLA(){
        return confirm({ message ->    
            def p = [comment: message, objid: entity.objid];
            entity.putAll( service.submitForFLA(p) ); 
            if( selectHandler ) selectHandler(entity);
                binding.refresh();
        });
    }
    
    def returnPending(){
        if(MsgBox.confirm("You are about to return this application to pending, proceed?")){
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.returnApp(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }
    }
    
    def returnForCI(){
        if(MsgBox.confirm("You are about to return this application for CI, proceed?")){
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.submitForInspection(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }    
    }

    def returnForFLA(){
        if(MsgBox.confirm("You are about to return this application for FOR FLA, proceed?")){
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.submitForFLA(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }    
    }
    
    def forApproval(){
        //validation if other recommedation is null before submitting for Final Loan Approval
        if( !entity.coMakerList ){
            if( !entity.vehiclelist && !entity.appliancelist && !entity.propertylist ){
                throw new Exception('Pls. specify a Co-Maker before submitting for \nFinal Loan Approval,since no collateral specified at present.');
            }else if( entity.vehiclelist && !entity.appliancelist || !entity.propertylist ){
                if(MsgBox.confirm("You are about to submit this application for Final Loan Approval,\nwithout a Co-Maker.proceed?")){
                    return confirm({ message ->
                        def p = [comment: message, objid: entity.objid];
                        entity.putAll( service.submitForApproval(p) ); 
                        if( selectHandler ) selectHandler(entity);
                            binding.refresh();
                    });
                }
            }
        }else{
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.submitForApproval(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }
    }

    def approvedApplication(){
         return InvokerUtil.lookupOpener("loan:approve", [
            selectHandler: { amount, msg ->
                entity.loaninfo.amountapproved = amount;
                def p = [entity: entity, comment: msg];
                entity.putAll( service.approveApp(p) );
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            }
        ]);
    }
    
    def approveMigratedApplication(){
        if(MsgBox.confirm("You are about to approve this application,\nmake sure all data(s) are correct. proceed?")) 
            entity.putAll( service.approvedMigratedApplication );
            binding.refresh();
    }

    def cancelApplication(){
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.cancelApp(p) );
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
    }

    def backOutApplication(){
        if(MsgBox.confirm("You are about to backout/cancel this loan application, proceed?")){ 
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.backOutApp(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }
    }

    def disqualifyApplication(){
        if(MsgBox.confirm("You are about to disqualify this loan application, proceed?")){ 
            return confirm({ message ->
                def p = [comment: message, objid: entity.objid];
                entity.putAll( service.disqualifyApp(p) ); 
                if( selectHandler ) selectHandler(entity);
                    binding.refresh();
            });
        }     
    }

    def confirm(def handler){
         return InvokerUtil.lookupOpener("message:popup", [
            label: 'Please specify remarks.',
            actionHandler: handler
        ]);
    }

    void initEntity(){
       entity=[
            loaninfo:[route:[:],loanamount:0.00],
            connection:[:],
            loancount:1,
            propertylist:[],
            vehiclelist:[],
            appliancelist:[],
            otherslist:[],   
            attachmentslist:[],   
            comments:[],
            jointBorrowerList:[],
            coMakerList:[],
        ];
    }
}