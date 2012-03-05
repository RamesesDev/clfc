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
            <b>Client Application Information(s)<b>
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
                        <%if( data.loancount==1 ){%>
                            New Application
                        <%}%>
                        <%if( data.loancount > 1 ){%>
                            RENEWAL ${data.loancount-1}
                        <%}%>
                    </td>
                </tr>
                <%if(data.clientType){%>
                    <tr>
                        <th>Client Type : </th> 
                        <%if(data.clientType=='WALK-IN'){%>
                            <td>${data.clientType}
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
                    <%if(data.jointBorrowerList.spouse.size() > 0){%>
                        <tr>
                            <th>Spouse(s) : </th>
                            <td>
                                <a href="viewJBSpouseInfo">${data.jointBorrowerList.spouse.collect( nameCollector ).join(' and ')}</a>
                            </td>
                        </tr>
                    <%}%>    
                <%}%>
                
                <%if(data.coMakerList.size() >0){%>
                    <tr>
                        <th>CoMaker(s) : </th>
                        <td>
                            <a href="viewCMInfo">${data.coMakerList.collect( nameCollector ).join(' and ')}</a>   
                        </td>
                    </tr>
                <%}%>
                <%if(data.coMakerList.spouse.size() > 0){%>
                    <tr>
                        <th>Spouse(s) : </th>
                        <td>
                            <a href="viewCMSpouseInfo">${data.coMakerList.spouse.collect( nameCollector ).join(' and ')}</a>
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
                <%if (!data.principalEmploymentList && !data.principalMainBusinessList) {%>
                    <tr>
                        <th><b>MAIN SOURCE(S) OF INCOME</b></th>
                        <tr><i class="red2">Not Specified at present</i></tr>
                    </tr>
                <%}%>
                <%if (!data.principalEmploymentList && data.principalMainBusinessList) {%>
                    <tr>
                        <th><b>MAIN SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && !data.principalMainBusinessList) {%>
                    <tr>
                        <th><b>MAIN SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (data.principalEmploymentList && data.principalMainBusinessList) {%>
                    <tr>
                        <th><b>MAIN SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
            </table>
            <div>
                <%
                    def links = [];
                    if( data.principalEmploymentList )   links << '<a href="viewEmployment">Employment</a>';
                    if( data.principalMainBusinessList ) links << '<a href="viewBusiness">Business</a>';
                    if( links )
                        println links.join(' | ');        
                %>
            </div>

            <table>
                <%if (!data.principalOtherSourcesOfIncomeList ) {%>
                    <tr>
                        <th><b>OTHER SOURCE(S) OF INCOME</b></th>
                        <tr><i class="red2">Not Specified at present</i></tr>
                    </tr>
                <%}%>
                <%if (data.principalOtherSourcesOfIncomeList ) {%>
                    <tr>
                        <th><b>OTHER SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
            </table>
            <div>
                <%
                    def links = [];
                    if( data.principalOtherSourcesOfIncomeList )   links << '<a href="viewOtherIncome">Employment</a>';
                        <!--println links.join(' | ');-->
                %>
                <!--&nbsp;&nbsp;&nbsp;-->
            </div>

            <br>
            <table>
                <%if ( data.encodedBy ) {%>
                    <tr>
                        <th>Application Encoded By : </th>
                        <td><b class="fuchsia">${data.encodedBy}</b></td>
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
                <%if ( data.loaninfo.amountapproved ) {%>
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
        <%if(data.propertylist.size()>0){%>
            <div class="box">
                <b>COLLATERAL (Real Property)</b>
            </div>
            <table class="grid" >
                <tr>
                    <th width="30%">Land/Building</th>
                    <th width="40%">Location</th>
                    <th width="15%">Area</th>
                    <th width="15%">Appraised Value</th>
                </tr>
                <%data.propertylist.eachWithIndex{o,idx-> %>
                    <tr>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                            <a href="viewAsset?property,${idx}">${o.rpu}</a>
                        </td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.location}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${format(o.area)} ${o.uom}</td>
                        <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">${o.appraisedvalue? o.appraisedvalue: '-'}</td>
                    </tr>
                <%}%>
            </table>
            <br>
            <!--  Othe Sites   -->
            <div width="220px" style="padding-left: 5px;">
               <h4 class="green"> Click the following related sites</h4>
                <ul> 
                     <li>
                        <span="50px>
                            <a href="http://www.bir.gov.ph/zonalvalues/zonalvalues.htm" target="_blank">Go to BIR Zonal Value</a>
                        </span>
                     </li>
                     <li>
                        <span="50px">
                            <a href="http://maps.google.com/" target="_blank">Go to Google Map / Search Boundary Limit</a>
                        </span>
                     </li><br>
                </ul>
            </div>
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
                    <th width="15%">Date Acquired</th>
                    <th width="15%">Appraised Value</th>
                    <th width="30%">Remarks</th>
                </tr>
                <%data.appliancelist.eachWithIndex{o,idx-> %>
                    <tr>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                            <a href="viewAsset?appliance,${idx}">${o.type}</a>
                        </td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.brand}/${o.serial}/${o.model}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.dateAcquired? o.dateAcquired: '-'}</td>
                        <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">${o.appraisedvalue? o.appraisedvalue: '-'}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                    </tr>
                <%}%>
                    <b class="black">Total CAV :</b>
            </table>
        <%}%>
        <%if(data.vehiclelist.size()>0){%>
            <div class="box">
                <b>COLLATERAL (Vehicles)</b>
            </div>
            <table class="grid" >
                <tr>
                    <th width="20%">Make</th>
                    <th width="15%">Model</th>
                    <th width="30%">Type/Engine#/Chassis#</th>
                    <th width="10%">Plate#</th>
                    <th width="15%">Registration#</th>
                    <th width="15%">Appraised Value</th>
                    <th width="20%">Remarks</th>
                </tr>
                <%data.vehiclelist.eachWithIndex{o,idx-> %>
                    <tr>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                            <a href="viewAsset?vehicle,${idx}">${o.make}</a>
                        </td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.model}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.type}/${o.engineno}/${o.chassisno}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.plateno}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.regno}</td>
                        <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">${o.appraisedvalue? o.appraisedvalue: '-'}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                    </tr>
                <%}%>
            </table>
            <br>
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
                        <th width="10%">Date Acquired</th>
                        <th width="15%">Market/Appraised Value</th>
                        <th width="20%">Remarks</th>
                    </tr>
                    <%data.otherslist.eachWithIndex{o,idx-> %>
                        <tr>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                                <a href="viewAsset?others,${idx}">${o.subject}</a>
                            </td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.modeOfAcquisition}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.use}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.dateAcquired}</td>
                            <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">${o.appraisedvalue? o.appraisedvalue: '-'}</td>
                            <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                        </tr>
                    <%}%>
                </table>
                <br>
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
                </table>
                <br>
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
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${format(o.loanAmount)}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${formatDate(o.dateGranted)}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${formatDate(o.maturityDate)}</td>    
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.term} days /${format(o.interestRate)}</td>
                        <td valign="top" align="center" class="${idx%2==0? 'even' : 'odd'}">${format(o.lendingPayment)}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks? o.remarks: '-'}</td>
                    </tr>
                    <%}%>
                </table>
                <br>
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
            <%} %>
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
            <%if( data.loaninfo.amountapproved ) {%>
                <table>
                    <td valign="top">
                        <legend><b class="green">LOAN APPROVAL</b> ( <b>Amount Applied</b> : Php <b class="teal">${format(data.loaninfo.loanamount)}</b> )</legend>
                        <%if( data.loaninfo.amountapproved ) {%>
                            <tr>
                                <th>Amount Approved:</th>
                                <td>Php <b class="green">${format(data.loaninfo.amountapproved)}</td>
                            </tr>
                        <%}%>
                        <%if( data.loaninfo.policy ) {%>    
                            <tr>
                                <td valign="top"><b>Absences</b></td>
                                <tr>
                                    <td valign="top"><td>
                                        <b>Policy :</b> ${data.loaninfo.policy} Abs</b>
                                        <b>Provisions :</b> ${data.loaninfo.provisions} Abs</b>
                                    </td>
                                </tr>
                            </tr>
                        <%}%>
                        <%if( data.loaninfo.creditLimit ) {%>
                            <tr>
                                <th>Credit Limit:</th>
                                <td>Php <b class="maroon">${data.loaninfo.creditLimit}</b></td>
                            </tr>
                        <%}%>
                        <%if( data.loaninfo.creditLimit ) {%>    
                            <tr>
                                <th>Increase:</th>
                                <td>Php <b class="maroon">${data.loaninfo.increase}</b></td>
                            </tr>
                        <%}%>
                        <tr>
                            <th>Must/Required Collatera(s)
                                <td>

                                </td>
                            </th>
                        </tr>
                        <tr>
                            <th>Additional Collateral(s)
                                <td>

                                </td>
                            </th>
                        </tr>
                        <br><br>
                        <%if( data.loaninfo.approvalAnnotation ) {%>
                            <tr>
                                <th>Annotation</th><br>
                                <td>${data.loaninfo.approvalAnnotation}</td>
                            </tr>
                        <%}%>
                    </td>
                </table>
            <%}%>
            <%if( data.loaninfo.offeredamount ) {%>
                <hr>
            <%}%>
            <%if( data.loaninfo.offeredamount ) {%>
                <table>
                    <td valign="top">
                        <legend><b class="navy">LOAN OFFER</b> ( <b>Amount Applied</b> : Php <b class="teal">${format(data.loaninfo.loanamount)}</b> )</legend>
                        <%if( data.loaninfo.offeredamount ) {%>
                            <tr>
                                <th>Amount Offered:</th>
                                <td>Php <b class="green">${format(data.loaninfo.offeredamount)}</td>
                            </tr>
                        <%}%>
                        <%if( data.loaninfo.offeredpolicy ) {%>
                            <%if( data.loaninfo.offeredprovisions ) {%>
                                <tr>
                                    <td valign="top"><b>Absences</b></td>
                                    <tr>
                                        <td valign="top"><td>
                                            <b>Policy : </b>${data.loaninfo.offeredpolicy} Abs</b>
                                            <b>Provisions : </b>${data.loaninfo.offeredprovisions} Abs</b>
                                        </td>
                                    </tr>
                                </tr>
                             <%}%>    
                        <%}%>
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
                        <br><br>
                        <%if( data.loaninfo.offeralAnnotation ) {%>
                            <tr>
                                <th>Annotation</th><br>
                                <td>${data.loaninfo.offeralAnnotation}</td>
                            </tr>
                        <%}%>
                    </td>
                </table>
            <%}%>
            <%if ( !data.loaninfo.approvedamount  && !data.loaninfo.offeredamount && !data.loaninfo.approvalAnnotation ) {%>
                <br>
                <i class="red2">No FLA yet.</i>
            <%}%>
        </div>
        <!--
        <div class="info">
            <%if ( data.loaninfo.approvalType!="conditional" && data.loaninfo.approvalType!="fixed" && !data.annotation ) {%>
                <br>
                <i class="red2">No FLA yet.</i>
            <%}%>
            <%if ( data.loaninfo.approvalType=="conditional" ) {%>
                <div>
                    Approval Type : (<b class="black"> Conditionally Approved </b>)
                    Amount Approved : (<b class="red"> Fixed Approval </b>)
                </div>
                <br>
                <table class="grid" >
                    <tr>
                        <th width="100px">Amount</th>
                        <th>Remarks</th>
                    </tr>
                    <%data.loaninfo.amountapprovedOptions.eachWithIndex{o,idx-> %>
                        <tr class="${idx%2==0? 'even' : 'odd' }">
                            <td valign="top" >${format(o.amount)}</td>
                            <td valign="top" >${o.remarks}</td>
                        </tr>
                    <%}%>
                </table>
            <% } %> 
            <% if ( data.loaninfo.approvalType=="fixed" ) { %>
                <div>
                    Amount Approved : Php <b>
                        <b class="green">${format(data.loaninfo.amountapproved)}</b>
                        (<b class="black"> Fixed Approval </b>)
                </div>
            <% } %> 
            <br>
            <% if ( data.annotation ) { %>
                <div class="sender">
                    posted by ${data.annotation.author} @ ${data.annotation.date}
                </div>   
                <div>
                    <pre>${data.annotation.remarks}</pre>
                </div>
            <% } %>   
        </div>
        -->
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
                </div>
                <br>
            <%}%>
        </div>    
    </body>
</html>