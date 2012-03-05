<%
    def nameCollector = {
        return '<u>' + it.lastname + ', ' + it.firstname + (it.middlename? ' ' + it.middlename : '') + '</u>';
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
        .sender { color: gray; font-size: 8px; }
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
            <b>Application Information<b>
        </div>
        <div class="info">
            <table>
                <tr>
                    <th>Application No. :</th>
                    <td><font color="blue"><b>${data.appno}</b></font></td>
                </tr>
                <tr>
                    <th>Borrower : </th> 
                    <td>${data.borrowername}</td>
                </tr>
                <%if(data.connection.size()>0){%>
                    <tr>
                        <th>Spouse : </th> 
                        <td>${data.connection.firstname} ${data.connection.lastname}</td>
                    </tr>
                <%}%>        
                <%if(data.jointBorrowerList.size() > 0){%>
                    <tr>
                        <th>Joint Borrower(s) : </th>
                        <td>
                            ${data.jointBorrowerList.collect( nameCollector ).join(' and ')}
                        </td>
                    </tr>
                <%}%>
                <%if(data.coMakerList.size() >0){%>
                    <tr>
                        <th>CoMaker(s) : </th>
                        <td>
                            ${data.coMakerList.collect( nameCollector ).join(' and ')}
                        </td>
                    </tr>
                <%}%>
                <tr>
                    <th>Route Code : </th> 
                    <td>${data.routecode} - ${data.routedescription}</td>
                </tr>
                <tr>
                    <th>Type : </th>
                    <td>
                        <%if( data.loancount==1 ){%>
                            New Application
                        <%}%>
                        <%if( data.loancount > 1 ){%>
                            RENEWAL ${data.loancount-1}
                        <%}%>
                    </td>
                </tr>
                <tr>
                    <th>Status : </th>
                    <td>${data.state}</td>
                </tr>    
            </table>
        </div>
        <br>
        
        <div class="box">
            <b>Loan Information<b>
        </div>

        <div class="info">
            <table>
                <tr><th valign="top">Purpose of Loan :</th><td> ${data.loaninfo.loanpurpose}</td></tr>
                <tr><th valign="top">Amount Applied :</th><td> ${data.loaninfo.loanamount}</td></tr>
                <% if (data.loaninfo.amountapproved) { %>
                    <tr><th valign="top">Amount Approved :</th><td> ${data.loaninfo.amountapproved}</td></tr>
                <% } %>
                <% if ( data.loaninfo.producttype ) { %>
                    <tr><th valign="top">Product Type :</th><td>${data.loaninfo.producttype.code}</td></tr>
                    <tr><th valign="top">Term :</th><td> ${data.loaninfo.producttype.term}</td></tr>
                    <tr><th valign="top">Interest Rate (%) :</th><td> ${data.loaninfo.producttype.interestrate}</td></tr>
                    <tr><th valign="top">Surcharge Rate (%):</th><td> ${data.loaninfo.producttype.surchargerate}</td></tr>
                    <tr><th valign="top">Past Due Rate (%) :</th><td> ${data.loaninfo.producttype.pastduerate}</td></tr>
                <% } %>
            </table>

            <div>
                <%

                    def links = [];
                    if( data.employed )    links << '<a href="viewEmployment">Employment</a>';
                    if( data.hasBusiness ) links << '<a href="viewBusiness">Business</a>';
                    if( links )
                        println links.join(' | ');
                %>
            </div>
         </div>   
         <br>
         <div class="source">
            <table valign="top">
                <tr>
                    <th>Other Source(s) of Income</th>
                </tr>    
                <tr>
                    <th>Business Description:</th>
                    <td>${data.kindOfBusiness}</td>
                </tr>           
                <tr>
                    <th>From:</th>
                    <td>${data.availTimeFrom}</td>
                </tr>
                <tr>
                    <th>To:</th>
                    <td>${data.availTimeTo}</td>
                </tr>
                <tr>
                    <th>Gross/Net Income:</th>
                    <td>${data.grossNetIncome}</td>
                </tr>
                <tr>
                    <th>Remarks:</th>
                    <td>${data.remarks}</td>
                </tr>
            </table>
        </div>
        <br>
        
        <%if(data.propertylist.size()>0){%>
            <div class="box">
                <b>COLLATERAL (Real Property)</b>
            </div>
            <table class="grid" >
                <tr>
                    <th>Land/Building</th>
                    <th>Location</th>
                    <th>Area</th>
                    <th>Appraised Value</th>
                </tr>
                <%data.propertylist.eachWithIndex{o,idx-> %>
                    <tr>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                            <a href="viewAsset?property,${idx}">${o.rpu}</a>
                        </td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.location}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.area} ${o.uom}</td>
                        <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.appraisedvalue}</td>
                    </tr>
                <%}%>
            </table>
            <br>
        <%}%>

        <%if(data.appliancelist.size()>0){%>
            <div class="box">
                <b>COLLATERAL (Appliances)</b>
            </div>
            <table class="grid" >
                <tr>
                    <th width="30%">Appliance</th>
                    <th width="30%">Brand/Serial#/Model#</th>
                    <th width="40%">Remarks</th>
                </tr>
                <%data.appliancelist.eachWithIndex{o,idx-> %>
                <tr>
                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}">
                        <a href="viewAsset?appliance,${idx}">${o.type}</a>
                    </td>
                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.brand}/${o.serial}/${o.model}</td>
                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                </tr>
                <%}%>
            </table>
            <br>
        <%}%>

        <%if(data.vehiclelist.size()>0){%>
            <div class="box">
                <b>COLLATERAL (Vehicles)</b>
            </div>
            <table class="grid" >
                <tr>
                    <th width="20%">Make</th>
                    <th width="20%">Model</th>
                    <th width="30%">Type/Engine#/Chassis#</th>
                    <th width="10%">Plate#</th>
                    <th width="20%">Registration#</th>
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
                    <td valign="top" class="${idx%2==0? 'even' : 'odd'}">${o.remarks}</td>
                </tr>
                <%}%>
            </table>
            <br>
        <%}%>
        
        <div class="box">
            <b>COMMENTS</b>
        </div>
        <div class="info">
            <a href="comment"> Add Comments</a>
            <br><br>
            <%data.comments.each(){o->%>
                <div class="sender">
                    posted by ${o.author} @ ${o.date}
                </div>
                <div>
                    <pre>${o.remarks}</pre>
                </div>
                <br>
            <%}%>
        </div>    

        <div class="box">
            <b>ANNOTATION</b>
        </div>        
        <div class="info">
            <a href="annotation"> Add Annotation</a>
            <br><br>
            <% if( data.annotations ) { %>
                <div class="sender">
                    posted by ${data.annotations.author} @ ${data.annotations.date}
                </div>
                <div>
                    <pre>${data.annotations.remarks}</pre>
                </div>
            <% } %>
        </div>

        <div class="box">
            <b>RECOMMENDATION</b>
        </div>
        <div class="info">
            <a href="recommendation"> Add Recommendation</a>
            <br><br>
            <% if( data.cirecommendation ) { %>
                <div class="sender">
                    posted by ${data.cirecommendation.author} @ ${data.cirecommendation.date}
                </div>
                <div>
                    <pre>${data.cirecommendation.remarks}</pre>
                </div>
            <% } %>
        </div>
    </body>
</html>