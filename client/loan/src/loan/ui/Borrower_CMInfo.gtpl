<%
    def dec_formatter = new java.text.DecimalFormat('#,##0.00');

    def format = {amt-> 
        return dec_formatter.format(amt); 
    };

    def dt_parser = new java.text.SimpleDateFormat('yyyy-MM-dd');
    def dt_formatter = new java.text.SimpleDateFormat('MMMM dd, yyyy');

    def formatDate = { date->
        if( date instanceof String ) date = dt_parser.parse( date );
        return dt_formatter.format( date );
    };
%>
<html>
    <style>
       body{font-family:arial;font-size:9px;}
       h3.white { color: #ffffff; }

       .listInfo { color: green; font-size: 9px; }
       .info { color: black; font-size: 9px; }
       .red { color: red; font-size: 9px; font-weight: bold; }
       .red2 { color: red; font-size: 9px; }
       .fuchsia { color: fuchsia; font-size: 9px; font-weight: bold; }
       .navy { color: navy; font-size: 9px; }
       .gray { color: gray; font-size: 9px; font-weight: bold; }
    </style>
    <body>
        <% if(!data.coMakerList) { %>
            <h3>No Co-Maker specified</h3>
        <%} else {%>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.coMakerList.each { cm -> %>
                            <%if( data.coMakerList.hasPhoto ){%>
                                <table>     
                                    <tr>
                                        <td valign="top"><b>Photo:</b></td>
                                        <tr>
                                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                                        </tr>
                                    </tr>
                                    <hr>
                                </table>
                            <%}%>
                            <%if( !data.coMakerList.hasPhoto ){%>
                                <hr>
                            <%}%>
                            <table>
                                <tr><td><b>Customer No. :</b></td><td> <b class="navy">${cm.contactno}</b></td></tr><br><br>
                                <tr><td><b>Name :</b></td><td> ${cm.lastname}, ${cm.firstname} 
                                    <%if(cm.middlename){%>
                                        <b class="gray">( ${cm.middlename.toUpperCase()} )</b></td>
                                    <%}%>
                                    <%if(!cm.middlename){%>
                                        <b class="gray">( ${cm.middlename? cm.middlename: '-'} )</b></td>
                                    <%}%>
                                </tr>
                                <%if(!cm.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                                <%}%>
                                <%if(cm.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(cm.birthdate)}</td></tr>
                                <%}%>
                                <%if(!cm.age){%>
                                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                                <%}%>
                                <%if(cm.age){%>
                                    <tr><td><b>Age :</b></td><td> ${cm.age} yrs. old</td></tr>
                                <%}%>
                                <%if(cm.civilstat){%>
                                    <tr><td><b>Marital Status :</b></td><td> ${cm.civilstat}</td></tr>
                                <%}%>
                                <tr><td><b>Citizenship :</b></td><td> ${cm.citizenship? cm.citizenship: '-'}</td></tr>     
                                <tr><td><b>Address :</b></td>
                                    <td>
                                        ${cm.currentaddress.address1}, 
                                        ${cm.currentaddress.address2? cm.currentaddress.address2: ' '},
                                        ${cm.currentaddress.city} 
                                        ${cm.currentaddress.province} 
                                        ${cm.currentaddress.zipcode} 
                                        ${cm.currentaddress.country}
                                    </td>
                                </tr>
                                <%if(cm.provincialaddress.address1){%>
                                    <tr><td><b>Provincial Address :</b></td>
                                        <td>
                                            ${cm.provincialaddress.address1}, 
                                            ${cm.provincialaddress.address2? cm.provincialaddress.address2: ' '},
                                            ${cm.provincialaddress.city} 
                                            ${cm.provincialaddress.province} 
                                            ${cm.provincialaddress.zipcode} 
                                            ${cm.provincialaddress.country}
                                        </td>
                                    </tr>
                                <%}%>
                                <tr>
                                    <td valign="top"><b>Residency/House</b></td>
                                    <tr>
                                        <%if(cm.residency.type=="OWNED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${cm.residency.type? cm.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.residency.remarks? cm.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!cm.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(cm.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.residency.yrsofstay? cm.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                        <%if(cm.residency.type=="RENTED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${cm.residency.type? cm.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rent Type :</b> <b class="info">${cm.residency.renttype? cm.residency.renttype: 'Not specified.'}</b><br>
                                                <%if(!cm.residency.rentamount){%>
                                                    <b class="listInfo">Rental Fee :</b>Php <b class="red">0.00</b><br>
                                                <%}%>
                                                <%if(cm.residency.rentamount){%>
                                                    <b class="listInfo">Rental Fee :</b>Php <b class="info">${format(cm.residency.rentamount)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.residency.remarks? cm.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!cm.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(cm.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.residency.yrsofstay? cm.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                    </tr>
                                </tr>
                                <tr>
                                    <td valign="top"><b>Lot Occupancy</b></td>
                                    <%if(cm.lotoccupancy.type=="OWNED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${cm.lotoccupancy.type? cm.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.lotoccupancy.remarks? cm.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!cm.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(cm.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.lotoccupancy.yrsofstay? cm.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>
                                    <%if(cm.lotoccupancy.type=="RENTED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${cm.lotoccupancy.type? cm.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rent Type :</b> <b class="info">${cm.lotoccupancy.renttype? cm.lotoccupancy.renttype: 'Not specified.'}</b><br>
                                            <%if(!cm.lotoccupancy.rentamount){%>
                                                <b class="listInfo">Rental Fee :</b>Php <b class="red">0.00</b><br>
                                            <%}%>
                                            <%if(cm.lotoccupancy.rentamount){%>
                                                <b class="listInfo">Rental Fee :</b> <b class="info">${format(cm.lotoccupancy.rentamount)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.lotoccupancy.remarks? cm.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!cm.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(cm.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.lotoccupancy.yrsofstay? cm.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>    
                                </tr>   
                                <tr><td><b>Phone/Mobile No. :</b></td><td> ${cm.phone? cm.phone: '-'}</td></tr>
                                <tr><td><b>CTC No. :</b></td><td> ${cm.ctcno? cm.ctcno: '-'}</td></tr>
                             </table>
                             <table>
                                <tr>
                                    <td><b>Relationship to the Principal Borrower :</b> <b class="fuchsia">${cm.relation.toLowerCase()? cm.relation: '-'}</b> 
                                        of <b>${data.borrower.lastname}, ${data.borrower.firstname}</b>
                                        <%if(!data.borrower.middlename){%>
                                            ( <b class="gray"> - </b> )
                                        <%}%>
                                        <%if(data.borrower.middlename){%>   
                                            ( <b class="gray"> ${data.borrower.middlename.toLowerCase()? data.borrower.middlename: '-'} </b> )
                                        <%}%>
                                    </td>
                                </tr>    
                             </table>
                             <%if(data.coMakerList.size() > 0){%>
                                <hr>
                             <%}else{%> 
                                <%if(!data.coMakerList){%>

                                <%}%>    
                             <%}%>
                             <table width="100%">
                                    <!--  Additional Info   -->
                                 <%if(cm.mainBusinessList || cm.otherSourcesOfIncomeList){%>
                                    <hr>
                                    <table width="100%">
                                        <%if (!cm.employmentList && !cm.mainBusinessList && !cm.otherSourcesOfIncomeList
                                                && !cm.profBackgroundList) {%>
                                            <tr>
                                                <th bgcolor="gray">
                                                    <h3 class="white"><b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OTHER RELATED INFO
                                                    </b></h3>
                                                </th>
                                                <tr>
                                                    <i class="red2">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Not Specified at present
                                                    </i>
                                                </tr>
                                            </tr>
                                        <%}%>
                                        <%if (cm.employmentList && !cm.mainBusinessList && !cm.otherSourcesOfIncomeList 
                                                && !cm.profBackgroundList) {%>
                                            <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                        <%}%>
                                        <%if (cm.employmentList && cm.mainBusinessList && !cm.otherSourcesOfIncomeList
                                                && !cm.profBackgroundList ) {%>
                                            <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                        <%}%>
                                        <%if (cm.employmentList && cm.mainBusinessList && cm.otherSourcesOfIncomeList
                                                && !cm.profBackgroundList ) {%>
                                            <tr><<th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                        <%}%>
                                        <%if (cm.employmentList && !cm.mainBusinessList && cm.otherSourcesOfIncomeList
                                                && !cm.profBackgroundList ) {%>
                                            <tr<th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                        <%}%>
                                        <%if (!cm.employmentList && cm.mainBusinessList && cm.otherSourcesOfIncomeList
                                                && !cm.profBackgroundList ) {%>
                                            <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                        <%}%>
                                        <%if (cm.employmentList && cm.mainBusinessList && cm.otherSourcesOfIncomeList
                                                && cm.profBackgroundList ) {%>
                                            <tr>
                                                <th bgcolor="gray"><h3 class="white">
                                                       SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                       OTHER RELATED INFO
                                                    </b></h3>
                                                </th>
                                            </tr>
                                        <%}%>
                                     </table>
                                     <br>
                                     <div>
                                        <%
                                            def links = [];
                                            if( cm.employmentList ) links << '<a href="viewCMEmployment">Employment</a>';
                                            if( cm.mainBusinessList ) links << '<a href="viewCMBusiness">Business</a>';
                                            if( cm.otherSourcesOfIncomeList ) links << '<a href="viewCMOtherIncome">Other Income</a>';
                                            if( cm.profBackgroundList ) links << '<a href="viewCMProfBGround">Professional Background</a>';
                                            if( cm.spouse ) links << '<a href="viewCMSpouse">Spouse Info</a>';
                                            if( links )
                                                println links.join(' | ');
                                        %>
                                     </div>
                                     <hr>
                                <%}%>
                             </table>
                             <!-- End -->
                        <%}%>
                    </td>
                 </tr>
             </table>
        <% } %>
    </body>
</html>