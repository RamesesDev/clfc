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
       .gray { color: gray; font-size: 9px; font-weight: bold;}
    </style>
    <body>
        
        <% if(!data.jointBorrowerList) { %>
            <h3>No Joint Borrower specified</h3>
        <%}else{%>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <!--
                            <%if( data.jointBorrowerList.hasPhoto ){%>
                                <table>
                                    <tr>
                                        <td valign="top"><b>Photo:</b></td>
                                        <tr>
                                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                                        </tr>
                                    </tr>
                                </table>
                            <%}%>
                            <%if( !data.jointBorrowerList.hasPhoto ){%>
                                <hr>
                            <%}%>
                            -->
                            <table>
                                <tr><td><b>Customer No. :</b></td><td> <b class="navy">${jb.contactno}</b></td></tr><br><br>
                                <tr><td><b>Name :</b></td><td> ${jb.lastname}, ${jb.firstname} 
                                    <%if(!jb.middlename){%>
                                        <b class="gray">( ${jb.middlename? jb.middlename: '-'} )</b></td>
                                    <%}%>
                                    <%if(jb.middlename){%>
                                        <b class="gray">( ${jb.middlename.toUpperCase()} )</b></td>
                                    <%}%>
                                </tr>
                                <%if(!jb.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                                <%}%>
                                <%if(jb.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(jb.birthdate)}</td></tr>
                                <%}%>
                                <%if(!jb.age){%>
                                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                                <%}%>
                                <%if(jb.age){%>
                                    <tr><td><b>Age :</b></td><td> ${jb.age}</td></tr>
                                <%}%>
                                <%if(jb.civilstat){%>
                                    <tr><td><b>Marital Status :</b></td><td> ${jb.civilstat}</td></tr>
                                <%}%>
                                <tr><td><b>Citizenship :</b></td><td> ${jb.citizenship? jb.citizenship: '-'}</td></tr>     
                                <tr><td><b>Address :</b></td>
                                    <td>
                                        ${jb.currentaddress.address1}, 
                                        ${jb.currentaddress.address2? jb.currentaddress.address2: ' '},
                                        ${jb.currentaddress.city} 
                                        ${jb.currentaddress.province} 
                                        ${jb.currentaddress.zipcode} 
                                        ${jb.currentaddress.country}
                                    </td>
                                </tr>
                                <%if(jb.provincialaddress.address1){%>
                                    <tr><td><b>Provincial Address :</b></td>
                                        <td>
                                            ${jb.provincialaddress.address1}, 
                                            ${jb.provincialaddress.address2? jb.provincialaddress.address2: ' '},
                                            ${jb.provincialaddress.city} 
                                            ${jb.provincialaddress.province} 
                                            ${jb.provincialaddress.zipcode} 
                                            ${jb.provincialaddress.country}
                                        </td>
                                    </tr>
                                <%}%>
                                <tr>
                                    <td valign="top"><b>Residency/House</b></td>
                                    <tr>
                                        <%if(jb.residency.type=="OWNED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${jb.residency.type? jb.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${jb.residency.remarks? jb.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!jb.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(jb.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(jb.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${jb.residency.yrsofstay? jb.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                        <%if(jb.residency.type=="RENTED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${jb.residency.type? jb.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rent Type :</b> <b class="info">${jb.residency.renttype? jb.residency.renttype: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rental Fee :</b> <b class="info">${jb.residency.rentamount? 'Php' +' '+ jb.residency.rentamount: ' '}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${jb.residency.remarks? jb.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!jb.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(jb.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(jb.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${jb.residency.yrsofstay? jb.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                    </tr>   
                                </tr>   
                                <tr>
                                    <td valign="top"><b>Lot Occupancy</b></td>
                                    <%if(jb.lotoccupancy.type=="OWNED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${jb.lotoccupancy.type? jb.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${jb.lotoccupancy.remarks? jb.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!jb.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(jb.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(jb.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${jb.lotoccupancy.yrsofstay? jb.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>
                                    <%if(jb.lotoccupancy.type=="RENTED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${jb.lotoccupancy.type? jb.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rent Type :</b> <b class="info">${jb.lotoccupancy.renttype? jb.lotoccupancy.renttype: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rental Fee :</b> <b class="info">${jb.lotoccupancy.rentamount? 'Php' +' '+ jb.lotoccupancy.rentamount: ' '}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${jb.lotoccupancy.remarks? jb.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!jb.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(jb.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(jb.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${jb.lotoccupancy.yrsofstay? jb.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>
                                </tr>
                                <tr><td><b>Phone/Mobile No. :</b></td><td> ${jb.phone? jb.phone: '-'}</td></tr>
                                <tr><td><b>CTC No. :</b></td><td> ${jb.ctcno? jb.ctcno: '-'}</td></tr>
                             </table>
                             <table>
                                <tr>
                                    <td><b>Relationship to the Principal Borrower :</b> <b class="fuchsia">${jb.relation.toLowerCase()? jb.relation: '-'}</b> 
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
                             <%if(data.jointBorrowerList.size() > 0){%>
                                <hr>
                             <%}else{%> 
                                <%if(!data.jointBorrowerList){%>

                                <%}%>
                             <%}%>
                             <!--  Additional Info   -->
                             <%if(jb.mainBusinessList || jb.otherSourcesOfIncomeList){%>
                                <table width="100%">
                                    <%if (!jb.employmentList && !jb.mainBusinessList && !jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr>
                                            <th bgcolor="gray"><h3 class="white">
                                               <b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    BANK ACCOUNTS &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;OTHER INFO
                                               </b></h3>
                                            </th>
                                            <tr>
                                                <i class="red2">
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    Not Specified at present
                                                </i>
                                            </tr>
                                        </tr>
                                    <%}%>
                                    <%if (jb.employmentList && !jb.mainBusinessList && !jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                    <%}%>
                                    <%if (jb.employmentList && jb.mainBusinessList && !jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                    <%}%>
                                    <%if (jb.employmentList && jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                    <%}%>
                                    <%if (jb.employmentList && !jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                    <%}%>
                                    <%if (!jb.employmentList && jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && !jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr><th bgcolor="gray"><h3 class="white"><b>SOURCE(S) OF INCOME</b></h3></th></tr>
                                    <%}%>
                                    <%if (!jb.employmentList && jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && jb.savingsAccountsList && !jb.checkingAccountsList) {%>
                                        <tr>
                                            <th bgcolor="gray"><h3 class="white">
                                                <b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   BANK ACCOUNTS
                                                </b></h3>
                                            </th>
                                        </tr>
                                    <%}%>
                                    <%if (!jb.employmentList && jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && !jb.profBackgroundList && jb.savingsAccountsList && jb.checkingAccountsList) {%>
                                        <tr>
                                            <th bgcolor="gray"><h3 class="white">
                                                   <b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   BANK ACCOUNTS
                                                </b></h3>
                                            </th>
                                        </tr>
                                    <%}%>
                                    <%if (jb.employmentList && jb.mainBusinessList && jb.otherSourcesOfIncomeList
                                            && jb.profBackgroundList && jb.savingsAccountsList && jb.checkingAccountsList) {%>
                                        <tr>
                                            <th bgcolor="gray"><h3 class="white">
                                                <b>SOURCE(S) OF INCOME &nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   BANK ACCOUNTS &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                   &nbsp;&nbsp;&nbsp;OTHER INFO
                                                </b></h3>
                                            </th>
                                        </tr>
                                    <%}%>
                                 </table>
                                 <br>
                                 <div>
                                    <%
                                        def links = [];
                                        if( jb.employmentList ) links << '<a href="viewJBEmployment">Employment</a>';
                                        if( jb.mainBusinessList ) links << '<a href="viewJBBusiness">Business</a>';
                                        if( jb.otherSourcesOfIncomeList ) links << '<a href="viewJBOtherIncome">Other Income</a>';
                                        if( jb.savingsAccountsList ) links << '<a href="viewJBSavings">Savings</a>';
                                        if( jb.checkingAccountsList ) links << '<a href="viewJBChecking">Checking</a>';
                                        if( jb.profBackgroundList ) links << '<a href="viewJBProfBGround">Professional Background</a>';
                                        if( jb.spouse ) links << '<a href="viewJBSpouse">Spouse Info</a>';
                                        if( jb.siblingsList ) links << '<a href="viewJBSiblings">Other Info</a>';
                                        if( links )
                                            println links.join(' | ');
                                    %>
                                 </div>
                                 <hr>
                             <%}%>
                             <!-- End -->
                        <%}%>
                    </td>
                 </tr>
             </table>
        <% } %>
    </body>
</html>