<%
    def nameCollector = {
        return '<u>' + it.lastname + ', ' + it.firstname + (it.middlename? " ( $it.middlename )" : '') + '</u>';
    };
    
    def dec_formatter = new java.text.DecimalFormat('#,###,##0.00');

    def format = {amt-> 
        return dec_formatter.format(amt); 
    };

    def dt_parser = new java.text.SimpleDateFormat('yyyy-MM-dd');
    def dt_formatter = new java.text.SimpleDateFormat('MMMM dd, yyyy');
    def dt_formatter2 = new java.text.SimpleDateFormat('MMMM dd, yyyy @ HH:mm:ss');
    
    def formatDate = { date->
        if( date instanceof String ) date = dt_parser.parse( date );
        return dt_formatter.format( date );
    };

    def formatDate2 = { date->
        if( date instanceof String ) date = dt_parser.parse( date );
        return dt_formatter2.format( date );
    };
%>
<html>
    <style>
        body{ font-family:arial;font-size:9px; }
        th { text-align: left; }
        .box {
            background: gray; color: white;
            border-color: #C1CDCD;
            border-width: 1px;
            border-style: solid;
            padding: 2px 5px 2px 5px; margin: 3px 0px 3px 0px; 
        }
        .name{ color:red; font-weight:bold; }
        .sender { color: gray; font-size: 8px; }
        .like { color: blue; font-size:7px; }
        .odd { color: black; font-size: 9px; }
        .navy { color: blue; font-size: 9px; font-weight:bold;}
        .red { color: red; font-size: 9px; font-weight:bold;}
        .red2 { color: red; font-size: 9px;}
        .black { color: black; font-size: 9px; }
        .green { color: green; font-size: 9px; }
        .maroon { color: maroon; font-size: 9px; }
        .gray { color: gray; font-size: 9px; }
        .fuchsia { color: fuchsia; font-size: 9px; }
        .teal { color: teal; font-size: 9px; font-weight:bold;}
        .olive { color: olive; font-size: 9px; font-weight:bold;}
        pre { padding-left: 5px; }
        .grid { width: 100%; }
        .grid th { background: #C6DEFF; }
        .grid .even {
            border-color: #E3E4FA;
            border-width: 1px;
            border-style: solid;
        }
        .grid .odd {
            background: #E3E4FA;
        }
        .info, .grid { margin-left: 10px; }
        
        .source {
            pre {padding-left: 9px;}
        }
    </style>
    <body>
        <%if(!data.borrower){%>
            <h3>No selected item</h3>
        <% return;}%>
        <div class="box">
            <b>Client Application Information<b>
        </div>
        <div class="info">
            <table>
                <tr>
                    <th>Application No. :</th>
                    <td class="red">${data.appno}</td>
                </tr>
                <tr>
                    <th>Application Type : </th>
                    <td>
                        <%if( data.appType ){%>
                            ${data.appType}
                        <%} else {%>
                            <%if( data.loancount==1 ){%>
                                New Application
                            <%}%>
                            <%if( data.loancount > 1 ){%>
                                RENEWAL ${data.loancount-1}
                            <%}%>
                        <%}%>
                    </td>
                </tr>
                <%if(data.clientType){%>
                    <tr>
                        <th>Client Type : </th> 
                        <%if(data.clientType=='WALK-IN'){%>
                            <td>${data.clientType}</td>
                        <%}%>    
                        <%if(data.clientType=='MARKETED'){%>
                            <td>${data.clientType} by ${data.marketedby? data.marketedby: '-' }</td>
                        <%}%>        
                    </tr>
                <%}%>
                <br>
                <%if(data.borrower){%>
                    <tr>
                        <th>Principal Borrower : </th>
                        <a href="viewPBInfo">${data.borrower.lastname}, ${data.borrower.firstname} ( ${data.borrower.middlename? data.borrower.middlename: '-'} )</a>
                    </tr>
                    <%if(!data.borrower){%>
                        <tr>
                            <th class="name">No Name Specified.</th>
                        </tr>
                    <%}%>
                <%}%>
                <%if(data.spouse){%>
                    <tr>
                        <th><b>Spouse : </b></th>
                        <a href="viewSpouseInfo">${data.spouse.lastname}, ${data.spouse.firstname} ( ${data.spouse.middlename? data.spouse.middlename: '-'} )</a>    
                    </tr>
                <%}%>
                <%if(data.jointBorrowerList.size() > 0){%>
                    <tr>
                        <th>Joint Borrower(s) : </th>
                        <td>
                            <a href="viewJBInfo">${data.jointBorrowerList.collect( nameCollector ).join(' and ')}</a>
                        </td>
                    </tr>    
                <%}%>
                <%if(data.coMakerList.size() >0){%>
                    <tr>
                        <th>CoMaker(s) : </th>
                        <td>
                            <a href="viewCMInfo">${data.coMakerList.collect( nameCollector ).join(' and ')}</a>   
                        </td>
                    </tr>
                <%}%>
                <tr>
                    <th>Route Code : </th> 
                    <td>${data.routecode? data.routecode: '-'} - ${data.routedescription? data.routedescription: '-'}</td>
                </tr>
                <br>
                <tr>
                    <th>Date Applied: </th>
                    <td class="teal">${formatDate2(data.dtfiled)}</td>
                </tr> 
                <%if ( data.state=="PENDING" ) {%>
                    <%if ( !data.returneddate ) {%>
                        <th>Status : </th>
                        <td><b class="gray">${data.state}</b></td>
                    <%}%>
                    <%if ( data.returneddate ) {%>
                        <tr>
                            <th>Status :</th>
                            <td><b class="gray">${data.state}</b>
                                ( <b class="red">Returned Application </b> ) on 
                                <b class="gray">${formatDate2( data.returneddate )}</b>
                            </td>
                        </tr>
                    <%}%>
                <%}%>
                <%if ( data.state=="FOR_INSPECTION" ) {%>
                    <tr>
                        <th>Status : </th>
                        <td><b class="gray">${data.state}</b></td>
                    </tr>
                <%}%>
                <%if ( data.state=="FOR_FLA" ) {%>
                    <%if ( !data.resenddate ) {%>
                        <th>Status : </th>
                        <td><b class="gray">${data.state}</b></td>
                    <%}%>
                    <%if ( data.resenddate ) {%>
                        <tr>
                            <th>Status :</th>
                            <td><b class="gray">${data.state}</b>
                                ( <b class="red">Resend/Send Back Application </b> ) on 
                                <b class="gray">${formatDate2( data.resenddate )}</b>
                            </td>
                        </tr>
                    <%}%>
                <%}%>
                <%if ( data.state=="FOR_APPROVAL" ) {%>
                    <tr>
                        <th>Status : </th>
                        <td>
                            <b class="olive">${data.state}</b>
                            on <b class="teal">${formatDate2(data.dtsubmittedforapproval)}</b>
                        </td>
                    </tr>
                <%}%>
                <%if ( data.state=="APPROVED" ) {%>
                    <tr>
                        <th>Status : </th>
                        <td>
                            <b class="navy">${data.state}</b>
                                on <b class="gray">${formatDate2(data.approvedate)}</b>
                        </td>
                    </tr>
                <%}%>
                <%if ( data.state=="FOR_RELEASE" ) {%>
                    <tr>
                        <th>Status : </th>
                        <td>
                            <b class="navy">${data.state}</b>
                        </td>
                    </tr>
                <%}%>
                <%if ( data.state=="RELEASED" ) {%>
                    <tr>
                        <th>Status : </th>
                        <td><b class="green">${data.state}</b></td>
                    </tr>    
                <%}%>
                <%if ( data.state=="DISAPPROVED") {%>
                    <%if ( data.disapprovedate ) {%>
                        <tr>
                            <th>Status : </th>
                            <td>
                                <b class="red">${data.state}</b> 
                                on <b class="gray">${formatDate2( data.disapprovedate )}</b>
                            </td>
                        </tr>
                    <%}%>    
                <%}%>
                <%if ( data.state=="CANCELED_OUT" ) {%>
                    <%if ( data.canceleddate ) {%>
                        <tr>
                            <th>Status :</th>
                            <td>
                                <b class="red">${data.state}</b> 
                                on <b class="gray">${formatDate2( data.canceleddate )}</b>
                            </td>
                        </tr>
                    <%}%>    
                <%}%>
                <%if ( data.state=="DISQUALIFIED_OUT" ) {%>
                    <%if ( data.disqualifieddate ) {%>
                        <tr>
                            <th>Status :</th>
                            <td>
                                <b class="red">${data.state}</b> 
                                on <b class="gray">${formatDate2( data.disqualifieddate )}</b>
                            </td>
                        </tr>
                    <%}%>    
                <%}%>
                <%if ( data.state=="BACK_OUT" ) {%>
                    <%if ( !data.backoutdate ) {%>
                        <th>Status : </th>
                        <td><b class="red">${data.state}</b></td>
                    <%}%>
                    <%if ( data.backoutdate ) {%>
                        <tr>
                            <th>Status :</th>
                            <td>
                                <b class="gray">${data.state}</b> 
                                on <b class="gray">${formatDate2( data.backoutdate )}</b>
                            </td>
                        </tr>
                    <%}%>
                <%}%>
            </table>
            <br>
            <table>
                <%if (!data.principalEmploymentList && !data.principalMainBusinessList 
                        && !data.principalSavingsAccountsList && !data.principalCheckingAccountsList && !data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp; BANK ACCOUNT(S) &nbsp;&nbsp;&nbsp; OTHER INFO</b></th>
                        <tr><i class="red2">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Not Specified at present
                            </i>
                        </tr>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && !data.principalMainBusinessList 
                        && !data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList && !data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (!data.principalEmploymentList && data.principalMainBusinessList 
                       && !data.principalOtherSourcesOfIncomeList  && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList && !data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList 
                        && !data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList && data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; OTHER INFO</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && !data.principalMainBusinessList 
                        && data.principalOtherSourcesOfIncomeList && data.principalSavingsAccountsList 
                            && data.principalCheckingAccountsList && data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BANIK ACCOUNTS
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OTHER INFO
                            </b>
                        </th>
                    </tr>
                <%}%>
                <%if (!data.principalEmploymentList && data.principalMainBusinessList 
                        && data.principalOtherSourcesOfIncomeList && data.principalSavingsAccountsList 
                            && data.principalCheckingAccountsList && data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BANIK ACCOUNTS
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OTHER INFO
                            </b>
                        </th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && !data.principalMainBusinessList 
                        && !data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList && data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; OTHER INFO</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList  
                        && data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList) {%>
                    <tr>
                        <th><b> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (!data.principalEmploymentList && data.principalMainBusinessList  
                        && data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList  
                        && !data.principalOtherSourcesOfIncomeList && data.principalSavingsAccountsList 
                            && !data.principalCheckingAccountsList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp &nbsp; BANK ACCOUNT(S)</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList  
                        && !data.principalOtherSourcesOfIncomeList && !data.principalSavingsAccountsList 
                            && data.principalCheckingAccountsList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp &nbsp; BANK ACCOUNT(S)</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList  
                        && data.principalOtherSourcesOfIncomeList && data.principalSavingsAccountsList 
                            && data.principalCheckingAccountsList && !data.childrenList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME 
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp &nbsp; BANK ACCOUNT(S)
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp &nbsp; OTHER INFO
                            </b>
                        </th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList  
                        && data.principalOtherSourcesOfIncomeList && data.principalSavingsAccountsList 
                            && data.principalCheckingAccountsList && data.childrenList) {%>
                    <tr>
                        <th><b>&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SOURCE(S) OF INCOME 
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               BANK ACCOUNT(S)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               &nbsp;&nbsp;&nbsp &nbsp; OTHER INFO
                            </b>
                        </th>
                    </tr>
                <%}%>
            </table>
            <div>
                <%
                    def links = [];
                    if( data.principalEmploymentList )   links << '<a href="viewEmployment">Employment</a>';
                    if( data.principalMainBusinessList ) links << '<a href="viewBusiness">Business</a>';
                    if( data.principalOtherSourcesOfIncomeList ) links << '<a href="viewOtherIncome">Other Income</a>';
                    if( data.principalSavingsAccountsList )  links << '<a href="viewSavingsAcct">Savings</a>';
                    if( data.principalCheckingAccountsList ) links << '<a href="viewCheckingAcct">Checking</a>';
                    if( data.childrenList ) links << '<a href="viewFamilyInfo">Family Info</a>';
                    if( data.principalProfessionalBackgroundList ) links << '<a href="viewProfBGround">Professional Background</a>';
                    if( links )
                        println links.join(' | ');
                %>
            </div>
            <br>
            <table>
                <%if ( data.encodedBy ) {%>
                    <tr>
                        <th>Application Encoded By : </th>
                        <td><b class="fuchsia">${data.encodedBy}</b></td>
                    </tr>    
                <%}%>
                <%if ( data.updatedBy ) {%>
                    <tr>
                        <th>Updated/Edited By : </th>
                        <td><b class="olive">${data.updatedBy}</b></td>
                    </tr>
                <%}%>
            </table>
        </div>
        <div class="box">
            <b>Loan Information<b>
        </div>
        <div class="info">
            <table>
                <%if( data.loaninfo.loanpurpose ) {%>
                    <tr><th valign="top">Purpose of Loan :</th><td> ${data.loaninfo.loanpurpose.toUpperCase()}</td></tr>
                <%}%>
                <%if( !data.loaninfo.loanpurpose ) {%>
                    <tr><th valign="top">Purpose of Loan :</th><td class="red">Not specified.</td></tr>
                <%}%>
                <tr><th valign="top">Amount Applied :</th><td> Php <b class="teal">${format(data.loaninfo.loanamount)}</b></td></tr>
                <% if ( data.approvedate ) {%>
                    <tr>
                    <th>Date Approved :</th>
                        <td class="navy"> ${ formatDate2( data.approvedate ) }</td>
                    </tr>
                <%}%>
                <!--<%if ( data.loaninfo.amountapproved ) {%>
                    <%if ( data.loaninfo.approvalType=="conditional" ) {%>
                        <tr>
                            <th valign="top">Amount Approved :</th>
                            <td> Php <b class="red">${format(data.loaninfo.amountapproved)}</b> 
                                 (<b class="black"> Conditionally Approved </b>)
                            </td>
                        </tr>
                    <%}%>
                    <%if ( data.loaninfo.approvalType=="fixed" ) {%>
                        <tr>
                            <th valign="top">Amount Approved :</th>
                            <td> Php <b class="green">${format(data.loaninfo.amountapproved)}</b> 
                                    (<b class="black"> Fixed Approval </b>)
                            </td>
                        </tr>
                    <%}%>
                <%}%>-->
                <%if ( data.loaninfo.amountapproved ) {%>
                    <%if ( data.loaninfo.offeredamount ) {%>
                        <tr>
                            <th valign="top">Amount Approved :</th>
                            <td> Php <b class="red">${format(data.loaninfo.amountapproved)}</b> 
                                 (<b class="green"> Loan Approved with Offer </b>)
                            </td>
                        </tr>
                    <%}%>
                    <%if ( !data.loaninfo.offeredamount ) {%>
                        <tr>
                            <th valign="top">Amount Approved :</th>
                            <td> Php <b class="green">${format(data.loaninfo.amountapproved)}</b> 
                                    (<b class="teal"> Loan Approved without Offer </b>)
                            </td>
                        </tr>
                    <%}%>
                <%}%>
                <%if ( data.dtreleased ) {%>
                    <tr>
                        <th>Date Granted :</th>
                        <td class="navy"><b> ${formatDate( data.dtreleased )}</b></td>
                    </tr>
                <%}%> 
                <%if ( data.state=="RELEASED" ) {%>
                    <%if ( data.ledger.maturityDate ) {%>
                        <tr>
                            <th>Maturity Date :</th>
                            <td class="red"><b> ${formatDate( data.ledger.maturityDate )}</b></td>
                        </tr>
                    <%}%>    
                <%}%>    
                <%if ( data.loaninfo.producttype ) { %>
                    <tr><th valign="top">Product Type :</th><td>${data.loaninfo.producttype.code}</td></tr>
                    <tr><th valign="top">Term :</th><td> ${data.loaninfo.producttype.term} days</td></tr>
                    <tr><th valign="top">Interest Rate (%) :</th><td> ${format(data.loaninfo.producttype.interestrate)}</td></tr>
                    <tr><th valign="top">Surcharge Rate (%):</th><td> ${format(data.loaninfo.producttype.surchargerate)}</td></tr>
                    <tr><th valign="top">Past Due Rate (%) :</th><td> ${format(data.loaninfo.producttype.pastduerate)}</td></tr>
                <%}%>
            </table>
        </div>    
        <br>
        <%if(data.propertylist || data.appliancelist || data.vehiclelist || data.otherslist){%>
            <%if(data.propertylist.size()>0){%>
                <div class="box">
                    <b>COLLATERAL (Real Property)</b>
                </div>
                <table class="grid" >
                    <tr>
                        <th width="25%">Land/Building</th>
                        <th width="30%">Location</th>
                        <th width="15%">Area</th>
                        <th width="15%">Zonal Value</th>
                        <th width="15%">Market/Appraised Value</th>
                    </tr>
                    <%data.propertylist.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <a href="viewAsset?property,${idx}">${o.rpu}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.location}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}" align="center">${format(o.area)} ${o.uom}</td>
                            <%if( o.zonalvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                   Php <b class="teal">${format(o.zonalvalue)}</b>
                                </td>
                            <%}%>
                            <%if( !o.zonalvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="maroon">0.00</b>
                                </td>
                            <%}%>
                            <%if( o.appraisedvalue ){%>
                                <td valign="top" align="right" class="${idx%2==0? 'even' : 'odd'}">
                                   Php <b class="teal">${format(o.appraisedvalue)}</b>
                                </td>
                            <%}%>
                            <%if( !o.appraisedvalue ){%>
                                <td valign="top" align="right" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="maroon">0.00</b>
                                </td>
                            <%}%>
                        </tr>
                    <%}%>
                </table>
                <div align="right">
                    <%if( !data.totalCAV ){%>
                        Total Property(s/ies) CAV :
                        Php <b class="maroon">0.00</b>
                    <%}%>
                    <%if( data.totalCAV ){%>
                        Total Property(s/ies) CAV :
                        Php <b class="green">${format(data.totalCAV)}</b>
                    <%}%>
                </div><br>
                <!--  Other related sites   -->
                <!--<div width="220px" style="padding-left: 5px;">
                   <b class="green"> Click the following related sites</b>
                    <ul> 
                         <li>
                            <span="50px>
                                <a href="http://www.bir.gov.ph/" target="_blank">Go to BIR Zonal Value</a>
                            </span>
                         </li>
                         <li>
                            <span="50px">
                                <a href="http://maps.google.com/" target="_blank">Go to Google Map / Search Boundary Limit</a>
                            </span>
                         </li>
                    </ul>
                </div>-->
                <!-------------------->
            <%}%>
            <%if(data.appliancelist.size()>0){%>
                <div class="box">
                    <b>COLLATERAL (Appliances)</b>
                </div>
                <table class="grid" >
                    <tr>
                        <th width="30%">Appliance</th>
                        <th width="30%">Brand/Serial#/Model#</th>
                        <th width="15%">Market/Appraised Value</th>
                        <th width="15%">Date Acquired</th>
                        <th width="30%">Remarks</th>
                    </tr>
                    <%data.appliancelist.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <a href="viewAsset?appliance,${idx}">${o.type}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.brand}/${o.serial}/${o.model}</td>
                            <%if( o.appraisedvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="teal">${format(o.appraisedvalue)}</b>
                                </td>
                            <%}%>
                            <%if( !o.appraisedvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="maroon">0.00</b>
                                </td>
                            <%}%>
                            <%if( o.dateAcquired ){%>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <b class="green">${formatDate(o.dateAcquired)}</b></td>
                            <%}%>
                            <%if( !o.dateAcquired ){%>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}" align="center"><b class="maroon">-</b></td>
                            <%}%>

                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                        </tr>
                    <%}%><br>
                </table>
                <div align="center">
                    <%if( !data.totalCAV ){%>
                        Total Appliance(s) CAV :
                        Php <b class="maroon">0.00</b>
                    <%}%>
                    <%if( data.totalCAV ){%>
                        Total Appliance(s) CAV :
                        Php <b class="green">${format(data.totalCAV)}</b>
                    <%}%>
                </div><br>
            <%}%>
            <%if(data.vehiclelist.size()>0){%>
                <div class="box">
                    <b>COLLATERAL (Vehicles)</b>
                </div>
                <table class="grid" >
                    <tr>
                        <th width="15%">Make</th>
                        <th width="15%">Model</th>
                        <th width="35%">Type / Engine# / Chassis# / Plate# / Registration#</th>
                        <th width="15%">Market/Appraised Value</th>
                        <th width="20%">Remarks</th>
                    </tr>
                    <%data.vehiclelist.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <a href="viewAsset?vehicle,${idx}">${o.make}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.model}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                ${o.type} / ${o.engineno} / ${o.chassisno} / ${o.plateno} / ${o.regno}
                            </td>                        
                            <%if( !o.appraisedvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="maroon">0.00</b>
                                </td>
                            <%}%>
                            <%if( o.appraisedvalue ){%>
                                <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="teal">${format(o.appraisedvalue)}</b>
                                </td>
                            <%}%>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                        </tr>
                    <%}%>
                </table>
                <div align="center">
                    <%if( !data.totalCAV ){%>
                        Total Vehicle(s) CAV :
                        Php <b class="maroon">0.00</b>
                    <%}%>
                    <%if( data.totalCAV ){%>
                        Total Vehicle(s) CAV :
                        Php <b class="green">${format(data.totalCAV)}</b>
                    <%}%>
                </div><br>
            <%}%>
            <%if(data.otherslist){%>    
                <%if(data.otherslist.size()>0){%>
                    <div class="box">
                        <b>COLLATERAL (Others)</b>
                    </div>
                    <table class="grid" >
                        <tr>
                            <th width="25%">Name</th>
                            <th width="15%">Mode of Acquisition</th>
                            <th width="15%">Use</th>
                            <th width="15%">Market/Appraised Value</th>
                            <th width="10%">Date Acquired</th>
                            <th width="20%">Remarks</th>
                        </tr>
                        <%data.otherslist.eachWithIndex{o,idx-> %>
                            <tr>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                    <a href="viewAsset?others,${idx}">${o.subject}</a>
                                </td>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.modeOfAcquisition}</td>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.use}</td>
                                <%if( !o.appraisedvalue ){%>
                                    <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="maroon">0.00</b></td>
                                <%}%>
                                <%if( o.appraisedvalue ){%>
                                    <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">
                                    Php <b class="teal">${format(o.appraisedvalue)}</b></td>
                                <%}%>
                                <%if( o.dateAcquired ){%>
                                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                        <b class="green">${formatDate(o.dateAcquired)}</b>
                                    </td>
                                <%}%>
                                <%if( !o.dateAcquired ){%>
                                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}" align="center">
                                        <b class="maroon">${o.dateAcquired? o.dateAcquired: '-'}
                                    </td>
                                <%}%>
                                <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                            </tr>
                        <%}%>
                    </table>
                    <div align="center">
                        <%if( !data.totalCAV ){%>
                            Total Other CAV :
                            Php <b class="maroon">0.00</b>
                        <%}%>
                        <%if( data.totalCAV ){%>
                            Total Other CAV :
                            Php <b class="green">${format(data.totalCAV)}</b>
                        <%}%>
                    </div><br>
                <%}%>
            <%}%>
            <%if(data.propertylist || !data.appliancelist || !data.vehiclelist || !data.otherslist){%>
                <div align="left">
                    <%if( !data.totalCAV ){%>
                        <font color="blue">Total Collateral(s) Appraised Value</font> :
                        <b>Php</b> <b class="teal">0.00</b>
                    <%}%>
                    <%if( data.totalCAV ){%>
                        <font color="blue">Total Collateral(s) Appraised Value</font> :
                        <b>Php</b> <b class="green">${format(data.totalCAV)}</b>
                    <%}%>
                </div><br>
            <%}%>
            <%if(!data.propertylist || !data.appliancelist || !data.vehiclelist || !data.otherslist){%>
                <div align="left">
                    <%if( !data.totalCAV ){%>

                    <%}%>
                </div><br>
            <%}%>
        <%}%>
        <%if(data.attachmentslist){%>    
            <%if(data.attachmentslist.size()>0){%>
                <div class="box">
                    <b>OTHER LOAN ATTACHMENTS</b>
                </div>
                <table class="grid" >
                    <tr>
                        <th width="30%">Name</th>
                        <th width="30%">Type</th>
                        <th width="40%">Remarks</th>
                    </tr>
                    <%data.attachmentslist.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <a href="viewAsset?attachments,${idx}">${o.subject}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.type}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                        </tr>
                    <%}%>
                </table><br>
            <%}%>
        <%}%>
        <%if(data.otherLendingList){%>
            <%if(data.otherLendingList.size()>0){%>
                <div class="box">
                    <b>OTHER LENDING INFORMATION</b>
                </div>
                <table class="grid">
                    <tr>
                        <th width="20%">Company</th>
                        <th width="25%">Address</th>
                        <th width="10%">Amount Loaned</th>
                        <th width="11%">Date Granted</th>
                        <th width="12%">Maturity Date</th>
                        <th width="23%">Term/Int.Rate</th>
                        <th width="10%">Payment</th>
                        <th width="20%">Remarks</th>
                    </tr>
                    <%data.otherLendingList.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <!--<a href="viewOtherLending?lending,${idx}">${o.company}</a>-->
                                <a href="viewOtherLending">${o.company}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.address}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                Php <b class="teal">${format(o.loanAmount)}</b>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <b class="green">${formatDate(o.dateGranted)}</b>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <b class="maroon">${formatDate(o.maturityDate)}</b>
                            </td>    
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}"><b class="navy">${o.term}</b> days /
                                <b class="maroon">${format(o.interestRate)} %
                            </td>
                            <%if( o.lendingPayment ) {%>
                                <td valign="top" align="center" 
                                    class="${idx%2==0? 'even' : 'odd'}">Php <b class="teal">${format(o.lendingPayment)}</b>
                                </td>
                            <%}%>
                            <%if( !o.lendingPayment ) {%>
                                <td valign="top" align="center" 
                                    class="${idx%2==0? 'even' : 'odd'}">Php <b class="maroon">${o.lendingPayment? o.lendingPayment: '-'}</b>
                                </td>
                            <%}%>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks? o.remarks: '-'}</td>
                        </tr>
                    <%}%>
                </table><br>
            <%}%>
        <%}%>    
        <div class="box">
            <b>CI RECOMMENDATION</b>
        </div>
        <div class="info">
            <%if( data.state!="FOR_INSPECTION" ) {%>
               <%if( !data.cirecommendation ) {%>
                    <br>
                    <i class="red2">No CI Recommendation(s) yet.</i>
                <%}%>    
            <% } %>
            <% if( data.state=="FOR_INSPECTION" ) {%>
                 <%if( !data.cirecommendation ) {%>
                    <a href="recommendation"> Add CI Recommendation</a>
                 <%}%>
                 <%if( data.cirecommendation ) {%>
                    <a href="recommendation"> Edit CI Recommendation</a>
                 <%}%> 
            <%}%>
            <br>
            <%if( data.cirecommendation ) {%>
                <div class="sender">
                    posted by ${data.cirecommendation.author} @ ${data.cirecommendation.date}
                </div>
                <div>
                    <pre>${data.cirecommendation.remarks}</pre>
                </div>
            <%}%>
        </div>
        <div class="box">
            <b>CRECOM RECOMMENDATION</b>
        </div> 
        <div class="info">
            <%if( data.state!="FOR_FLA" ) {%>
               <%if( !data.crecomrecommendation ) {%>
                    <br>
                    <i class="red2">No Crecom Recommendation(s) yet.</i>
                <%}%>     
            <%}%>
            <%if( data.state=="FOR_FLA" ) {%>
                <%if( !data.crecomrecommendation ) {%>
                    <a href="crecomrecommendation"> Add Crecom Recommendation</a>
                    <br>
                <%}%>
                <%if( data.crecomrecommendation ) {%>
                    <a href="crecomrecommendation"> Edit Crecom Recommendation</a>
                    <br>
                <%}%>    
            <%}%><br> 
            <%if( data.crecomrecommendation ) {%>
                <div class="sender">
                    posted by ${data.crecomrecommendation.author} @ ${data.crecomrecommendation.date}
                </div><br>
                <%if( data.marketerRecomAmt || data.ciRecomAmt || data.fcaRecomAmt || data.caoRecomAmt || data.bcohRecomAmt ) {%>    
                    <div>
                        <table>
                            <tr><td><b class="gray">Other Recommendations</b></td></tr>
                            <tr>
                                <td>
                                    <b>Marketer:</b><b class="teal"> <u>${format(data.marketerRecomAmt)}</u></b>
                                    <b class="red">|| </b><b>CI:</b> <b class="teal"><u>${format(data.ciRecomAmt)}</u></b>
                                    <b class="red">|| </b><b>FCA:</b> <b class="teal"><u>${format(data.fcaRecomAmt)}</u></b>
                                    <b class="red">|| </b><b>CAO:</b> <b class="teal"><u>${format(data.caoRecomAmt)}</u></b>
                                    <b class="red">|| </b><b>BCOH:</b> <b class="teal"><u>${format(data.bcohRecomAmt)}</u></b>
                                </td>
                            </tr>
                        </table>
                    </div><br>
                <%}%>
                <div>
                    <pre>${data.crecomrecommendation.remarks}</pre>
                </div>
            <%}%>
        </div>
        <div class="box">
            <b>FLA</b>
        </div>
        <div class="info">
            <%if( data.state=="PENDING" ) {%>
                <%if( data.loaninfo.amountapproved || data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
            <%}%>
            <%if( data.state=="FOR_INSPECTION" ) {%>
                <%if( data.loaninfo.amountapproved || data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
                <%if( !data.loaninfo.amountapproved || !data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
            <%}%>
            <%if( data.state=="FOR_FLA" ) {%>
                <%if( data.loaninfo.amountapproved || data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
                <%if( !data.loaninfo.amountapproved || !data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
            <%}%>
            <%if( data.state=="FOR_APPROVAL" ) {%>
                <%if( data.loaninfo.amountapproved || data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
                <%if( !data.loaninfo.amountapproved || !data.loaninfo.offeredamount ) {%>
                    <br>
                    <i class="red2">No FLA yet.</i>
                <%}%>
            <%}%>
            <%if( data.state=="APPROVED" ) {%>
                <div class="info">
                    <fieldset>
                        <%if( data.loaninfo.amountapproved ) {%>
                            <legend>
                                <b class="green">LOAN APPROVAL</b> 
                                ( <b>Amount Applied</b> : Php <b class="teal">${format(data.loaninfo.loanamount)}</b> )
                            </legend>
                            <table>
                                <%if( data.loaninfo.amountapproved ) {%>
                                    <tr>
                                        <th>Amount Approved:</th>
                                        <td>Php <b class="green">${format(data.loaninfo.amountapproved)}</td>
                                    </tr>
                                <%}%>
                            </table>
                            <table>
                                <%if( data.loaninfo.policy ) {%>    
                                    <tr>
                                        <td valign="top"><b>Absences</b></td>
                                        <tr>
                                            <td valign="top"><td>
                                                <b>Policy :</b> ${data.loaninfo.policy} Abs</b>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <b>Provisions :</b> ${data.loaninfo.provisions} Abs</b>
                                            </td>
                                        </tr>
                                    </tr>
                                <%}%>
                            </table>
                            <table>
                                <%if( data.loaninfo.creditLimit ) {%>
                                    <tr>
                                        <th>Credit Limit:</th>
                                        <td>Php <b class="maroon">${format(data.loaninfo.creditLimit)}</b></td>
                                    </tr>
                                <%}%>
                                <%if( data.loaninfo.increase ) {%>    
                                    <tr>
                                        <th>Increase:</th>
                                        <td>Php <b class="maroon">${format(data.loaninfo.increase)}</b></td>
                                    </tr>
                                <%}%>
                            </table>
                            <table>
                                <!-- validation if must collateral(s) is present -->
                                    <tr>
                                        <td valign="top">
                                            <b class="black">Must/Required Collatera(s)</b>
                                        </td>
                                        <td>
                                            <!-- Sample Data only -->
                                            <b class="green">1. TV - Sanyo </b><br>
                                            <b class="green">2. TV - GOLDSTAR </b><br>
                                            <b class="green">3. REF - LG </b><br>
                                        </td>
                                    </tr>
                                <!------------------------------------------------>    
                            </table>
                            <table>
                                <tr>
                                    <td valign="top">
                                        <b class="black">Additional Collateral(s)</b>
                                    </td>
                                    </td>
                                        <!-- Sample Data only -->
                                        <b class="teal">1. DVD COMPONENT - Sony </b><br>
                                        <b class="teal">2. STAND FAN - ASAHI </b><br>
                                        <b class="teal">3. OR/CR HONDA TMX</b>
                                    </td>
                                </tr>
                            </table>   
                            <br><br>
                            <div>
                                <b class="black">Annotation</b><br>
                                <%if( data.loaninfo.approvalAnnotation ) {%>
                                    <div class="sender">
                                        posted by ${data.approvalAnnotation.author} @ ${data.approvalAnnotation.date}
                                    </div>
                                    <div>
                                        <pre>${data.loaninfo.approvalAnnotation.toUpperCase()}</pre>
                                    </div><br>
                                <%}%>
                            </div>
                        <%}%>
                    </fieldset>
                    <%if( data.loaninfo.offeredamount ) {%>
                        <hr><br>
                    <%}%>
                    <%if( data.loaninfo.offeredamount ) {%>
                        <fieldset>
                            <%if( data.loaninfo.offeredamount ) {%>
                                <legend>
                                    <b class="green">LOAN OFFER</b> 
                                    ( <b>Amount Applied</b> : Php <b class="teal">${format(data.loaninfo.loanamount)}</b> )
                                </legend>
                                <table>
                                    <%if( data.loaninfo.offeredamount ) {%>
                                        <tr>
                                            <th>Amount Approved:</th>
                                            <td>Php <b class="green">${format(data.loaninfo.offeredamount)}</td>
                                        </tr>
                                    <%}%>
                                </table>
                                <table>
                                    <%if( data.loaninfo.policy ) {%>    
                                        <tr>
                                            <td valign="top"><b>Absences</b></td>
                                            <tr>
                                                <td valign="top"><td>
                                                    <b>Policy :</b> ${data.loaninfo.offeredpolicy} Abs</b>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <b>Provisions :</b> ${data.loaninfo.offeredprovisions} Abs</b>
                                                </td>
                                            </tr>
                                        </tr>
                                    <%}%>
                                </table>
                                <table>
                                    <%if( data.loaninfo.offeredcreditLimit ) {%>
                                        <tr>
                                            <th>Credit Limit:</th>
                                            <td>Php <b class="maroon">${format(data.loaninfo.offeredcreditLimit)}</b></td>
                                        </tr>
                                    <%}%>
                                    <%if( data.loaninfo.offeredincrease ) {%>    
                                        <tr>
                                            <th>Increase:</th>
                                            <td>Php <b class="maroon">${format(data.loaninfo.offeredincrease)}</b></td>
                                        </tr>
                                    <%}%>
                                </table>
                                <br>
                                <div>
                                    <b class="black">Annotation</b><br>
                                    <%if( data.loaninfo.offeralAnnotation ) {%>
                                        <div class="sender">
                                            posted by ${data.offeralAnnotation.author} @ ${data.offeralAnnotation.date}
                                        </div>
                                        <div>
                                            <pre>${data.loaninfo.offeralAnnotation.toUpperCase()}</pre>
                                        </div>
                                    <%}%>
                                </div>
                            <%}%>
                        </fieldset>
                    <%}%>
                    <%if ( !data.loaninfo.amountapproved  && !data.loaninfo.offeredamount && !data.loaninfo.approvalAnnotation ) {%>
                        <br><i class="red2">No FLA yet.</i><br>
                    <%}%>
                </div>
            <%}%>
        </div>
        <div class="box">
            <b>COMMENTS</b>
        </div>
        <div class="info">
            <%if ( data.state!="FOR_APPROVAL" ) {%>
                <a href="comment"> Add Comments</a>
            <% } %>
            <%if ( data.state=="FOR_APPROVAL" ) {%>

            <% } %>
            <br><br>
            <%data.comments.each(){o->%>
                <div class="sender">
                    posted by ${o.author} @ ${o.date} <!--<b class="like">Like</b>-->
                </div>
                <div>
                    <pre>${o.remarks}</pre>
                    <br>
                </div>
            <%}%>
        </div>    
    </body>
</html>