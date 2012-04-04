package loan.ui;

import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.common.*;
import com.rameses.util.*;
import java.lang.*;
import java.io.*;

public class ApplicationReportModel extends ReportModel {

    def printOut;
    def entity;
    
    static def dt_formatter = new java.text.SimpleDateFormat('MMMM dd, yyyy');
        
    def getCustName( cust ){
        return cust.lastname + ', ' + cust.firstname + (cust.middlename? " ( $cust.middlename )" : "")
    }
    
    def getCustAddress( cust ){
        def addr = [];
        if( !cust.currentaddress ) return '';
        
        if( cust.currentaddress.address1 )  addr << cust.currentaddress.address1;
        //if( cust.currentaddress.address2?  cust.currentaddress.address2: ' ' )  addr << cust.currentaddress.address2;
        if( cust.currentaddress.city )      addr << cust.currentaddress.city;
        if( cust.currentaddress.province )  addr << cust.currentaddress.province;
        if( cust.currentaddress.zipcode )   addr << cust.currentaddress.zipcode;
        
        return addr.join(', ');
    }
    
    def getCustProvAddress( cust ){
        def addr = [];
        if( !cust.provincialaddress ) return '';
        
        if( cust.provincialaddress.address1 )  addr << cust.provincialaddress.address1;
        if( cust.provincialaddress.address2? cust.provincialaddress.address2: ' ' )  addr << cust.provincialaddress.address2;
        if( cust.provincialaddress.city )      addr << cust.provincialaddress.city;
        if( cust.provincialaddress.province )  addr << cust.provincialaddress.province;
        if( cust.provincialaddress.zipcode )   addr << cust.provincialaddress.zipcode;
        
        return addr.join(', ');
    }
    
    public String getReportName(){
        /*
        if( printOut=='Client Information Sheet' )
            return "loan/printout/ClientInformationReport.jasper"; 
        else if( printOut=='Loan Application' )    
            return "loan/printout/loanApplicationReport.jasper";
        else if( printOut=='Disclosure' )  
            return "loan/printout/LoanDisclosureStatement.jasper";
        else if( printOut=='Loan Release Voucher' )
            return "loan/printout/LoanReleaseVoucher.jasper";
        else
            return "loan/printout/CashVoucher.jasper";
        */
        if( printOut=='Client Information Sheet' )
            return "loan/printout/ClientInformationReport.jasper"; 
        else if( printOut=='Loan Application' )    
            return "loan/printout/loanApplicationReport.jasper";
        else if( printOut=='Loan Disclosure' )  
            return "loan/loandocs/LoanDisclosureStatement.jasper";
        else
            return "loan/loandocs/LoanReleaseVoucher.jasper";
        //else if( printOut=='Loan Release Voucher' )
            //return "loan/loandocs/LoanReleaseVoucher.jasper";
        //else
            //return "loan/loandocs/CashVoucher.jasper";
    }
    
    public Object getReportData(){ 
        def data=[:];
        //def accts;
        if ( entity.loancount == 1 )
            data.apptype = "NEW APPLICATION";
        else
            data.apptype = "RENEWAL" + ' ' + ( entity.loancount -1 );
        
        /*--Principal Borrower Info--*/
        data.putAll(entity);
        if( entity.clientType=='MARKETED' ){
            if( entity.marketedby !=null )
                data.marketedby = data.marketedby;
            else data.marketedby = '';
            if( entity.marketedby2 !=null )
                data.marketedby2 = data.marketedby2;
            else data.marketedby2 = '';
            data.marketed = data.marketedby +" and "+ data.marketedby2;
        }
        if( entity.clientType=='WALK-IN' ){
            data.marketed = '-';
        }
        data.borrowerName = getCustName( data.borrower );
        data.borrower.contactno = data.borrower.contactno+'';
        if( data.borrower.otherAccnts !=null ){
            data.otherAccts = "OTHER ACCOUNTS";
        }
        if( data.borrower.otherAccnts ==null ){
            data.borrower.otherAccnts = "No other accounts specified.";
            data.otherAccts = " ";
        }
        
        data.mainBorrowerAge = data.borrower.age;
        data.borrowerAddress = getCustAddress( data.borrower );
        data.borrowerProvAddress = getCustProvAddress( data.borrower );    
        if( data.borrower.residency!=null ){
            if( data.borrower.residency.type =="OWNED" )
                data.borrower.residency.renttype='';
            if( !data.borrower.residency.rentamount )
                data.borrower.residency.rentamount = new BigDecimal( 0.00 );
            if( data.borrower.residency.rentamount==null )
                data.borrower.residency.rentamount = new BigDecimal( 0.00 );    
            else data.borrower.residency.rentamount = data.borrower.residency.rentamount as BigDecimal;
        }
        else{
            data.borrower.residency.type==null;
            data.borrower.residency.renttype ='';
        }    
        
        if( data.borrower.lotoccupancy!=null ){
            if( data.borrower.lotoccupancy.type =="OWNED" )
                data.borrower.lotoccupancy.renttype ='';
            if( !data.borrower.lotoccupancy.rentamount )
                data.borrower.lotoccupancy.rentamount = new BigDecimal( 0.00 );
            if( data.borrower.lotoccupancy.rentamount ==null )
                data.borrower.lotoccupancy.rentamount = new BigDecimal( 0.00 );
            else data.borrower.lotoccupancy.rentamount = data.borrower.lotoccupancy.rentamount as BigDecimal;
        }
        else{
            data.borrower.lotoccupancy.type ==null;
            data.borrower.lotoccupancy.renttype='';
        }
        
        if ( data.spouse!=null ){
            if( data.spouse.residency.type =="OWNED" )
                data.spouse.residency.renttype ='';
            if( data.spouse.residency.rentamount==null )
                data.spouse.residency.rentamount = new BigDecimal( 0.00 );
            else if( data.spouse.residency.rentamount!= null ) 
                data.spouse.residency.rentamount = data.spouse.residency.rentamount as BigDecimal;
        
            if( data.spouse.lotoccupancy.type =="OWNED" )
                data.spouse.lotoccupancy.renttype='';
            if( data.spouse.lotoccupancy.rentamount ==null )
                data.spouse.lotoccupancy.rentamount = new BigDecimal( 0.00 );    
            else if( data.spouse.lotoccupancy.rentamount!= null ) 
                data.spouse.lotoccupancy.rentamount = data.spouse.lotoccupancy.rentamount as BigDecimal;
            
            def addr = data.spouse.currentaddress;
            if( data.spouse.currentaddress ){
                if( addr ){
                    data.spouseAddress = addr.address1 + (addr.address2? ' ' + addr.address2 : '') +
                                   (addr.city? ' ' + addr.city : '') +
                                   (addr.zipcode? ' ' + addr.zipcode : '') +
                                   (addr.province? ' ' + addr.province : '') +
                                   (addr.country? ' ' + addr.country : '');
                }
                else {
                    data.spouseAddress = '';
                }
            }
        }
        
        if( data.loaninfo.loanamount !=null ) data.loaninfo.loanamount = data.loaninfo.loanamount as BigDecimal;
        if( data.loaninfo.amountapproved !=null ) data.loaninfo.amountapproved = data.loaninfo.amountapproved as BigDecimal;
        if( data.loaninfo.producttype.interestrate !=null ) data.loaninfo.producttype.interestrate = data.loaninfo.producttype.interestrate as BigDecimal;
        if( data.loaninfo.producttype.underpaymentpenalty !=null ) data.loaninfo.producttype.underpaymentpenalty = data.loaninfo.producttype.underpaymentpenalty as BigDecimal;
        if( data.loaninfo.producttype.pastduerate !=null ) data.loaninfo.producttype.pastduerate = data.loaninfo.producttype.pastduerate as BigDecimal;
        if( data.loaninfo.producttype.term !=null ) data.loaninfo.producttype.term = data.loaninfo.producttype.term as BigDecimal;
        if( data.cicashCountAmount !=null ) data.cicashCountAmount = data.cicashCountAmount as BigDecimal;
        if( data.totalCAV !=null ) data.totalCAV = data.totalCAV as BigDecimal;
        
        if( data.routecode ==null )
            data.routecode = '';
        else data.routecode = data.routecode;
        if( data.routedescription ==null )
            data.routedescription = '';
        else data.routedescription = data.routedescription;
        
        data.route = data.routecode + " - " + data.routedescription;
        
        /*     CI Recommendation     */
        if( data.cirecommendation !=null ){
            data.ciHeadTitle="CI Recommendation";
            data.ciPostedBy = "posted by " + data.cirecommendation.author + " @ " + data.cirecommendation.date;
        }    
        else{
            data.ciHeadTitle="";
            data.ciPostedBy = "";
            //data.ciPostedBy = "No CI Recommendations posted";
        }
        
        /*    Crecom Recommendation   */
        if( data.crecomrecommendation !=null )
            data.crecomPostedBy = "posted by " + data.crecomrecommendation.author + " @ " + data.crecomrecommendation.date;
        else
            data.crecomPostedBy = "No Crecom Recommendations posted";
        
        if( data.marketerRecomAmt !=null && data.ciRecomAmt !=null &&
            data.fcaRecomAmt !=null && data.caoRecomAmt !=null && data.bcohRecomAmt !=null ){
            
            data.crecomOtherRecom = "Other Recommendations";
        }
        else if( data.marketerRecomAmt !=null && data.ciRecomAmt !=null &&
            data.fcaRecomAmt !=null && data.caoRecomAmt !=null && data.bcohRecomAmt !=null ){    
         
            data.crecomOtherRecom = "No Other Recommendations";
        }
        
        if( data.marketerRecomAmt !=null ) data.marketerRecomAmt = data.marketerRecomAmt as BigDecimal;
        if( data.ciRecomAmt !=null ) data.ciRecomAmt = data.ciRecomAmt as BigDecimal;
        if( data.fcaRecomAmt !=null ) data.fcaRecomAmt = data.fcaRecomAmt as BigDecimal;
        if( data.caoRecomAmt !=null ) data.caoRecomAmt = data.caoRecomAmt as BigDecimal;
        if( data.bcohRecomAmt !=null ) data.bcohRecomAmt = data.bcohRecomAmt as BigDecimal;
        if( data.annotation !=null )
            data.annotationPostedby = "posted by " + data.annotation.author + "@ " + data.annotation.date;
        else
            data.annotationPostedBy = "No Annotations posted";
            
        /*    FLA Results   */
                    /*  For Loan Approval  Data*/
        if( data.loaninfo.amountapproved !=null ) data.loaninfo.amountapproved = data.loaninfo.amountapproved as BigDecimal;
        if( data.loaninfo.creditLimit !=null ) data.loaninfo.creditLimit = data.loaninfo.creditLimit as BigDecimal;
        if( data.loaninfo.increase !=null ) data.loaninfo.increase = data.loaninfo.increase as BigDecimal;
        if( data.loaninfo.approvalAnnotation !=null )
            data.approvalAnnotationPostedby = "posted by " + data.approvalAnnotation.author + "@ " + data.approvalAnnotation.date;
        else
            data.approvalAnnotationPostedBy = "No Annotations posted";
        
                   /*  For Loan Offer  Data*/
        if( data.loaninfo.offeredamount !=null ) data.loaninfo.offeredamount = data.loaninfo.offeredamount as BigDecimal;
        if( data.loaninfo.offeredcreditLimit !=null ) data.loaninfo.offeredcreditLimit = data.loaninfo.offeredcreditLimit as BigDecimal;
        if( data.loaninfo.offeredincrease !=null ) data.loaninfo.offeredincrease = data.loaninfo.offeredincrease as BigDecimal;
        if( data.offeralAnnotation !=null )
            data.offeralAnnotationPostedby = "posted by " + data.offeralAnnotation.author + "@ " + data.offeralAnnotation.date;
        else
            data.offeralAnnotationPostedBy = "No Annotations posted";
            
        data.principalMainBusinessList.each{
            //if( it.invested ==null )
              //  it.invested = new BigDecimal(0.00);
            //else it.invested = new BigDecimal( it.invested );

            //if( it.cicashCountAmount ==null )
                //it.cicashCountAmount = new BigDecimal(0.00);
            //else it.cicashCountAmount = new BigDecimal( it.cicashCountAmount );
            
            if ( it.occupancy.rentamount ==null )
                it.occupancy.rentamount = new BigDecimal( 0.00 );
            else it.occupancy.rentamount = new BigDecimal( it.occupancy.rentamount );
            
            if ( it.businesstype.boundaryfee ==null )
                it.businesstype.boundaryfee = new BigDecimal( 0.00 );
            else it.businesstype.boundaryfee = new BigDecimal( it.businesstype.boundaryfee );
        };
        
        data.otherLendingList.each{
            if( it.lendingPayment ==null )
                it.lendingPayment = new BigDecimal( 0.00 );
            else it.lendingPayment = new BigDecimal( it.lendingPayment );
            
            if( it.loanAmount ==null )    
                it.loanAmount = new BigDecimal( 0.00 );
            else it.loanAmount = new BigDecimal( it.loanAmount );
            
            if( it.interestRate ==null )
                it.interestRate = new BigDecimal( 0.00 );
            else it.interestRate = new BigDecimal( it.interestRate );
        };

        data.childrenList.each{ cl ->
            cl.employmentList.each{ el -> 
                //if( el.salary==null )
                   // el.salary = new BigDecimal(0.00);  
               // else el.salary = new BigDecimal( el.salary );
            }; 
            cl.otherSourcesOfIncomeList.each{ osi -> 
                if ( osi.grossNetIncome==null )
                    osi.grossNetIncome = new BigDecimal( 0.00 );
                else osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
            };
        };
        
        data.principalEmploymentList.each{
            //if ( it.salary ==null )
              //  it.salary = new BigDecimal(0.00);
            //else it.salary = new BigDecimal(it.salary);
        };
        
        data.principalOtherSourcesOfIncomeList.each{
            if ( it.grossNetIncome==null )
                it.grossNetIncome = new BigDecimal( 0.00 );
            else it.grossNetIncome = new BigDecimal( it.grossNetIncome );
        };
        
        data.principalsiblingsList.each{ psl ->
            psl.employmentList.each{ el -> 
                //if( el.salary==null )
                    //el.salary = new BigDecimal(0.00);  
                //else el.salary = new BigDecimal( el.salary );
            }; 
            psl.otherSourcesOfIncomeList.each{ osi -> 
                if ( osi.grossNetIncome==null )
                    osi.grossNetIncome = new BigDecimal( 0.00 );
                else osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
            };
        };
        
        data.principalSpouseSiblingsList.each{ ssl -> 
            ssl.employmentList.each{ el -> 
                //if( el.salary==null )
                    //el.salary = new BigDecimal(0.00);
                //else el.salary = new BigDecimal( el.salary );
            };
            ssl.otherSourcesOfIncomeList.each{ osi -> 
                if ( osi.grossNetIncome==null )
                    osi.grossNetIncome = new BigDecimal( 0.00 );
                else osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
            };
        };
        
        if( data.jointBorrowerList ){
            data.jointBorrowerNames = data.jointBorrowerList.collect { getCustName(it) }.join(' and ');
            data.jointBorrowerAddress = getCustAddress( data.jointBorrowerList[0] );
            data.jointBorrowerBirthDate = data.jointBorrowerList.collect{ it.birthdate? it.birthdate : ' - ' }.join('/');
            data.jointBorrowerAge = data.jointBorrowerList.collect{ it.age? it.age: ' - ' }.join('/');
            data.jointBorrowerRelationship = data.jointBorrowerList.collect{ it.relation? it.relation: ' - ' }.join(' / ');
            data.jointBorrowerCivilStat = data.jointBorrowerList.collect{ it.civilstat? it.civilstat: ' - '}.join(' / ');
        }
                
        data.jointBorrowerList.each{ jbl ->     
            if( jbl.residency.type=="RENTED" ){
                if( !jbl.residency.rentamount )
                    jbl.residency.rentamount = new BigDecimal( 0.00 );
                else
                    jbl.residency.rentamount = new BigDecimal( jbl.residency.rentamount );
            }
        
            if( jbl.lotoccupancy.type=="RENTED" ){
                if(jbl.lotoccupancy.rentamount==null)
                    jbl.lotoccupancy.rentamount = new BigDecimal( 0.00 );
                else
                    jbl.lotoccupancy.rentamount = new BigDecimal( jbl.lotoccupancy.rentamount );
            }
        
            if( jbl.spouse!=null ){    
                if(jbl.spouse.residency.type=="RENTED")
                    jbl.spouse.residency.rentamount = new BigDecimal( jbl.spouse.residency.rentamount );
            }    
            
            if( jbl.spouse!=null ){
                if( jbl.spouse.lotoccupancy.type=="RENTED" )
                    jbl.spouse.lotoccupancy.rentamount = new BigDecimal( jbl.spouse.lotoccupancy.rentamount );
            }
                
            jbl.mainBusinessList.each{ mbl ->
                //if( mbl.invested==null )
                    //mbl.invested = new BigDecimal(0.00);
               // else
                    //mbl.invested = new BigDecimal( mbl.invested );
            
                if( mbl.businesstype.boundaryfee==null )
                    mbl.businesstype.boundaryfee = new BigDecimal( 0.00 );
                else mbl.businesstype.boundaryfee = new BigDecimal( mbl.businesstype.boundaryfee );
                
                if( mbl.occupancy.type=="RENTED" ){
                    if( !mbl.occupancy.rentamount )
                       mbl.occupancy.rentamount = new BigDecimal(0.00);
                    else    
                        mbl.occupancy.rentamount = new BigDecimal( mbl.occupancy.rentamount );
                }
            };

            jbl.otherSourcesOfIncomeList.each{ osi ->
                if( osi.grossNetIncome==null)
                    osi.grossNetIncome = new BigDecimal( 0.00 );
                else
                    osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
            };

            jbl.employmentList.each{ el ->
                //if( el.salary==null )
                    //el.salary = new BigDecimal(0.00);
               // else
                    //el.salary = new BigDecimal( el.salary );
            };
            
            jbl.siblingsList.each{ sl -> 
                sl.employmentList.each{ el -> 
                    //if( el.salary==null )
                        //el.salary = new BigDecimal(0.00);  
                   // else
                        //el.salary = new BigDecimal( el.salary );
                };
                sl.otherSourcesOfIncomeList.each{ osi -> 
                    if( osi.grossNetIncome==null )
                         osi.grossNetIncome = new BigDecimal( 0.00 );
                    else 
                        osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
                };
            };
            
            jbl.spouseSiblingsList.each{ ssl -> 
                ssl.employmentList.each{ el -> 
                    //if( el.salary==null )
                        //el.salary = new BigDecimal(0.00);  
                   // else
                        //el.salary = new BigDecimal( el.salary );
                };
                ssl.otherSourcesOfIncomeList.each{ osi -> 
                    if( osi.grossNetIncome==null )
                         osi.grossNetIncome = new BigDecimal( 0.00 );
                    else 
                        osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
                };
            };
        };

        if( data.coMakerList ){
            data.coMakerNames = data.coMakerList.collect { getCustName(it) }.join(' and ');
            data.coMakerAddress = getCustAddress( data.coMakerList[0] );
            data.coMakerBirthDate = data.coMakerList.collect{ it.birthdate? it.birthdate : ' - ' }.join('/');
            data.coMakerAge = data.coMakerList.collect{ it.age? it.age: ' - ' }.join('/');
            data.coMakerRelationship = data.coMakerList.collect{ it.relation? it.relation: ' - ' }.join(' / ');
            data.coMakerCivilStat = data.coMakerList.collect{ it.civilstat? it.civilstat: ' - '}.join(' / ');
        } 
        
        data.coMakerList.each{ cml -> 
            if( cml.residency.type=="RENTED" )
                cml.residency.rentamount = new BigDecimal( cml.residency.rentamount );
            if ( cml.lotoccupancy.type=="RENTED" )
                cml.lotoccupancy.rentamount = new BigDecimal( cml.lotoccupancy.rentamount );

            if( cml.spouse!=null ){     
                if( cml.spouse.residency.type=="RENTED" )
                    cml.spouse.residency.rentamount = new BigDecimal( cml.spouse.residency.rentamount );
            }    
                
            if( cml.spouse!=null ){
                if( cml.spouse.lotoccupancy.type=="RENTED" )
                    cml.spouse.lotoccupancy.rentamount = new BigDecimal( cml.spouse.lotoccupancy.rentamount  );
            }
        
            cml.mainBusinessList.each{ mbl ->
                //if( mbl.invested==null )
                    //mbl.invested = new BigDecimal(0.00);
                //else
                    //mbl.invested = new BigDecimal( mbl.invested ); 
            
                if( mbl.businesstype.boundaryfee==null )
                    mbl.businesstype.boundaryfee = new BigDecimal( 0.00 );
                else mbl.businesstype.boundaryfee = new BigDecimal( mbl.businesstype.boundaryfee );
            
                if( mbl.occupancy.type=="RENTED" ){
                    if( !mbl.occupancy.rentamount )
                        mbl.occupancy.rentamount = new BigDecimal( 0.00 );
                    else    
                        mbl.occupancy.rentamount = new BigDecimal( mbl.occupancy.rentamount );
                }
            };

            cml.otherSourcesOfIncomeList.each{ osi ->
                if( osi.grossNetIncome==null)
                    osi.grossNetIncome = new BigDecimal( 0.00 );
                else
                    osi.grossNetIncome = new BigDecimal( osi.grossNetIncome );
            };

            cml.employmentList.each{ el ->
                //if( el.salary==null )
                    //el.salary = new BigDecimal(0.00);
                //else
                    //el.salary = new BigDecimal( el.salary );
            };
        };
        
        data.propertylist.each{
            if( it.area==null )
                it.area = new BigDecimal( 0.00 );
            else it.area = new BigDecimal( it.area );
            
            if( it.appraisedvalue==null )
                it.appraisedvalue = new BigDecimal( 0.00 );
            else it.appraisedvalue = new BigDecimal( it.appraisedvalue );
            
            if( it.zonalvalue==null )
                it.zonalvalue = new BigDecimal( 0.00 );
            else it.zonalvalue = new BigDecimal( it.zonalvalue );
        };
        
        data.vehiclelist.each{
            if( it.appraisedvalue==null )
                it.appraisedvalue = new BigDecimal( 0.00 );
            else it.appraisedvalue = new BigDecimal( it.appraisedvalue );   
        };
        
        data.appliancelist.each{
            if( it.appraisedvalue==null ){
                it.appraisedvalue = new BigDecimal( 0.00 );    
                //it.totalCav = new BigDecimal(0.00);
            }else it.appraisedvalue = new BigDecimal( it.appraisedvalue );
                //it.totalCav = it.appraisedvalue(it.appraisedvalue);
        };
        
        /*-----For Loan Releasing-----*/
        data.appno = data.appno;
        data.contactno = data.borrower.contactno+'';
        data.name = [data.borrower.firstname + ' ' + data.borrower.lastname];
        data.name2 = [data.borrower.firstname + ' ' + data.borrower.lastname];
        data.jointBorrowerList?.each {
            data.name << it.firstname + ' ' + it.lastname;
            data.name2 << it.firstname + ' ' + it.lastname;
        }
        data.name = data.name.join(' and ');
        data.address = getCustAddress( data.borrower );
        
        //*** Ledger Data  ***//
        if( data.ledger ){
            if( data.ledger.dailyPayment !=null ) data.ledger.dailyPayment = data.ledger.dailyPayment as BigDecimal;
                else data.ledger.dailyPayment = new BigDecimal( 0.00 );
            if( data.ledger.term !=null ) data.ledger.term = data.ledger.term as BigDecimal;
                else data.ledger.term = new BigDecimal( 0.00 );    
            data.lenTerm = data.ledger.term / 30.00;
          
            
            if ( data.ledger.startDate ==null )
                data.ledger.startDate = '';
            else{ data.ledger.startDate = data.ledger.startDate;
                //data.ledger.startDate = data.ledger.startDate.parse("dd-MM-yyyy", "31-12-2012");
                //data.ledger.startDate = dt_formatter.format(data.ledger.startDate);
            }
            
            if ( data.ledger.maturityDate ==null )    
                data.ledger.maturityDate = '';
            else data.ledger.maturityDate = data.ledger.maturityDate;
            if ( data.ledger.dailyPayment ==null )
                data.ledger.dailyPayment = new BigDecimal(0.00);
            else data.dailyPayment = new BigDecimal(data.ledger.dailyPayment);
        }
        
        //if( p.checkdate instanceof String )
            //p.checkdate = dt_formatter.parse( p.checkdate );
        //data.bankname = data.ledger.bankname;
        //data.checkno = data.ledger.checkno;
        //data.bankname = application.bankname;
        //data.checkno = application.checkno;
        //data.charges = accts;
        //data.insurance = '';
        //data.notarial = accts.find{ it.title == 'NOTARIAL FEE' }?.amount;
        //data.docstamp = accts.find{ it.title == 'DOCUMENTARY STAMP' }?.amount;
        //data.chatrealreg = accts.find{ it.title == 'CHAT/REAL REGISTRATION' }?.amount;
        //data.affidavit = accts.find{ it.title == 'AFFIDAVIT' }?.amount;
        //data.totalCharges =  data.notarial + data.docstamp + data.chatrealreg + data.affidavit;
        /*----------------*/
        
        return data;
    }
    
    public SubReport[] getSubReports(){
        if(printOut=='Loan Application'){
            return[
                new SubReport("COLLATERAL_PROPERTY", "loan/printout/subReportRealProperty.jasper"),    
                new SubReport("SUBREPORT_BUSINESS", "loan/printout/subReportBusiness.jasper"), 
                new SubReport("SUBREPORT_EMPLOYMENT", "loan/printout/subReportEmployment.jasper") 
            ];
        }
        else{
            return [
                /*Primary Loan Information*/
                new SubReport("BUSINESS", "loan/printout/subReportPrincipalBusiness.jasper"),
                new SubReport("OTHERLENDING_SUBREPORT", "loan/printout/subReportPrincipalOtherLending.jasper"),
                new SubReport("COLLATERAL_VEHICLES", "loan/printout/subReportVehicles.jasper"),
                new SubReport("COLLATERAL_PROPERTY", "loan/printout/subReportRealProperty.jasper"),
                new SubReport("COLLATERAL_APPLIANCE", "loan/printout/subReportAppliance.jasper"),
                new SubReport("COLLATERAL_OTHERS", "loan/printout/subReportOtherCollateral.jasper"),
                
                /*Principal Borrower Information*/
                new SubReport("SPOUSE", "loan/printout/subReportPrincipalSpouseInfo.jasper"),
                new SubReport("FAMILY_INFO", "loan/printout/subReportPrincipalFamilyInfo.jasper"),
                new SubReport("CHILDREN_EMPLOYMENT", "loan/printout/subReportPrincipalChildrenEmployment.jasper"),
                new SubReport("CHILDREN_OTHERSOURCESOFINCOME", "loan/printout/subReportPrincipalChildrenOtherSourcesOfIncome.jasper"),
                new SubReport("PRINCIPAL_PROFESSIONALBACKGROUND", "loan/printout/subReportPrincipalProfessionalBackground.jasper"),
                new SubReport("PRINCIPAL_EMPLOYMENT", "loan/printout/subReportPrincipalEmploymentInfo.jasper"),
                new SubReport("PRINCIPAL_OTHERSOURCESOFINCOME", "loan/printout/subReportPrincipalOtherSourcesOfIncome.jasper"),    
                new SubReport("PRINCIPALSIBLINGSLIST_SUBREPORT", "loan/printout/subReportPrincipalsiblingsList.jasper"),    
                new SubReport("PRINCIPALSIBLINGS_EMPLOYMENT", "loan/printout/subReportPrincipalSiblingsEmployment.jasper"),
                new SubReport("PRINCIPALSIBLINGS_OTHERSOURCESOFINCOME", "loan/printout/subReportPrincipalSiblingsOtherSourcesOfIncome.jasper"),
                new SubReport("PRINCIPALSPOUSESIBLINGSLIST_SUBREPORT", "loan/printout/subReportPrincipalSpouseSiblingsList.jasper"),    
                new SubReport("PRINCIPALSPOUSESIBLINGS_EMPLOYMENT", "loan/printout/subReportPrincipalSpouseSiblingsEmployment.jasper"),
                new SubReport("PRINCIPALSPOUSESIBLINGS_OTHERSOURCESOFINCOME", "loan/printout/subReportPrincipalSpouseSiblingsOtherSourcesOfIncome.jasper"),
                new SubReport("PRINCIPALSAVINGSACCOUNTSLIST_SUBREPORT", "loan/printout/subReportPrincipalSavingsAccountsList.jasper"),    
                new SubReport("PRINCIPALCHECKINGACCOUNTSLIST_SUBREPORT", "loan/printout/subReportPrincipalCheckingAccountsList.jasper"),
                new SubReport("PRINCIPALCIBUSINESS_SUBREPORT", "loan/printout/subReportPrincipalCIBusiness.jasper"),
                //new SubReport("BUSINESS", "loan/printout/subReportPrincipalCIBusiness.jasper"),    
                    
                /*Joint Borrower Information*/
                new SubReport("JOINTBORROWERLIST_SUBREPORT", "loan/printout/subReportJointBorrowerInfo.jasper"),    
                new SubReport("JOINTBORROWER_SPOUSEINFO", "loan/printout/subReportJointBorrowerSpouseInfo.jasper"),
                new SubReport("JOINTBORROWER_MAINBUSINESS", "loan/printout/subReportJointBorrowerMainBusiness.jasper"),
                new SubReport("JOINTBORROWER_OTHERSOURCESOFINCOME", "loan/printout/subReportJointBorrowerOtherSourcesOfIncome.jasper"),
                new SubReport("JOINTBORROWER_PROFESSIONALBACKGROUND", "loan/printout/subReportJointBorrowerProfessionalBackground.jasper"),
                new SubReport("JOINTBORROWER_EMPLOYMENT", "loan/printout/subReportJointBorrowerEmploymentInfo.jasper"),
                new SubReport("JOINTBORROWER_SIBLINGSINFO", "loan/printout/subReportJointBorrowerSiblingsInfo.jasper"),
                new SubReport("JOINTBORROWER_SPOUSESIBLINGSINFO", "loan/printout/subReportJointBorrowerSpouseSiblingsInfo.jasper"),
                new SubReport("JOINTBORROWER_CHECKINGACCOUNT", "loan/printout/subReportJointBorrowerCheckingAccountsList.jasper"),
                new SubReport("JOINTBORROWER_SAVINGSACCOUNT", "loan/printout/subReportJointBorrowerSavingsAccountsList.jasper"),
                
                /*Co-Maker Information*/
                new SubReport("COMAKERLIST_SUBREPORT", "loan/printout/subReportCoMakerInfo.jasper"),
                new SubReport("COMAKER", "loan/printout/subReportCoMaker.jasper"),
                new SubReport("COMAKER_SPOUSEINFO", "loan/printout/subReportCoMakerSpouseInfo.jasper"),
                new SubReport("COMAKER_MAINBUSINESS", "loan/printout/subReportCoMakerMainBusiness.jasper"),
                new SubReport("COMAKER_OTHERSOURCEOFINCOME", "loan/printout/subReportCoMakerOtherSourcesOfIncome.jasper"),
                new SubReport("COMAKER_PROFESSIONALBACKGROUND", "loan/printout/subReportCoMakerProfessionalBackground.jasper"),    
                new SubReport("COMAKER_EMPLOYMENT", "loan/printout/subReportCoMakerEmploymentInfo.jasper"),
                
                /*Other Loan Information*/
                new SubReport("ANNOTATIONS_SUBREPORT", "loan/printout/subReportAnnotation.jasper"),
                new SubReport("AMOUNTAPPROVED_SUBREPORT", "loan/printout/subReportAmountApproved.jasper"),
                new SubReport("LOANHISTORY_SUBREPORT", "loan/printout/subReportLoanHistory.jasper"),
                new SubReport("LEDGEREVALUATION_SUBREPORT", "loan/printout/subReportLedgerEvaluation.jasper")
            ];
        }
    }
    public Map getParameters(){ 
        return [ 
            BRANCH_NAME: OsirisContext.env.CLIENT.name ,
            PRINTDATE: new Date(), 
            User: OsirisContext.env.USERINFO.firstname+ ' '+ OsirisContext.env.USERINFO.lastname   
        ]
    }
}