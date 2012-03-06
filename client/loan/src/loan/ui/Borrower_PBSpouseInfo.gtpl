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
       .navy { color: navy; font-size: 9px; }
       .gray { color: gray; font-size: 9px; font-weight: bold; }
       .fuchsia { color: fuchsia; font-size: 9px; font-weight: bold; }
    </style>
    <body>
        <% if(!data.spouse) { %>
            <h3>No Spouse specified</h3>
        <%}else{%>
            <%if( !data.spouse.hasPhoto ){%>
               <table>     
                    <tr>
                        <td valign="top"><b>Photo:</b></td>
                        <tr>
                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                        </tr>
                    </tr>
               </table>
            <%}%>
            <%if( data.spouse.hasPhoto ){%>
               <table>     
                    <tr>
                        <td valign="top"><b>Photo:</b></td>
                        <tr>
                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                        </tr>
                    </tr>
               </table>
            <%}%>
            <table>
                <tr><td><b>Customer No. :</b></td><td> <b class="navy">${data.spouse.contactno}</b></td></tr><br><br>
                <tr><td><b>Name :</b></td><td> ${data.spouse.lastname}, ${data.spouse.firstname} 
                    <%if(data.spouse.middlename){%>
                        <b class="gray">( ${data.spouse.middlename.toUpperCase()} )</b></td>
                    <%}%>
                    <%if(!data.spouse.middlename){%>    
                        <b class="gray">( ${data.spouse.middlename? data.spouse.middlename: '-'} )</b></td>
                    <%}%>
                </tr>
                <%if(data.spouse.birthdate){%>
                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(data.spouse.birthdate)}</td></tr>
                <%}%>
                <%if(!data.spouse.birthdate){%>
                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                <%}%>
                <%if(data.spouse.age){%>
                    <tr><td><b>Age :</b></td><td> ${data.spouse.age}</td></tr>
                <%}%>    
                <%if(!data.spouse.age){%>
                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                <%}%>
                <%if(data.spouse.civilstat){%>
                    <tr><td><b>Marital Status :</b></td><td> ${data.spouse.civilstat}</td></tr>
                <%}%>
                <tr><td><b>Citizenship :</b></td><td> ${data.spouse.citizenship? data.spouse.citizenship: '-'}</td></tr>     
                <tr><td><b>Address :</b></td>
                    <td>
                        ${data.spouse.currentaddress.address1}, 
                        ${data.spouse.currentaddress.address2? data.spouse.currentaddress.address2: ' '},
                        ${data.spouse.currentaddress.city} 
                        ${data.spouse.currentaddress.province} 
                        ${data.spouse.currentaddress.zipcode} 
                        ${data.spouse.currentaddress.country}
                    </td>
                </tr>
                <%if(data.spouse.provincialaddress.address1){%>
                    <tr><td><b>Provincial Address :</b></td>
                        <td>
                            ${data.spouse.provincialaddress.address1}, 
                            ${data.spouse.provincialaddress.address2? data.spouse.provincialaddress.address2: ' '},
                            ${data.spouse.provincialaddress.city} 
                            ${data.spouse.provincialaddress.province} 
                            ${data.spouse.provincialaddress.zipcode} 
                            ${data.spouse.provincialaddress.country}
                        </td>
                    </tr>
                <%}%>
                <tr>
                    <td valign="top"><b>Residency/House</b></td>
                    <tr>
                        <%if(data.spouse.residency.type=="OWNED"){%>
                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${data.spouse.residency.type? data.spouse.residency.type: 'Not specified.'}</b><br>
                                <b class="listInfo">Description/Remarks :</b> <b class="info">${data.spouse.residency.remarks? data.spouse.residency.remarks: 'Not specified.'}</b><br>
                                <%if(!data.spouse.residency.since){%>
                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                <%}%>
                                <%if(data.spouse.residency.since){%>
                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(data.spouse.residency.since)}</b><br>
                                <%}%>
                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.spouse.residency.yrsofstay? data.spouse.residency.yrsofstay: ' '}</b>
                            </td>
                        <%}%>
                        <%if(data.spouse.residency.type=="RENTED"){%>
                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${data.spouse.residency.type? data.spouse.residency.type: 'Not specified.'}</b><br>
                                <b class="listInfo">Rent Type :</b> <b class="info">${data.spouse.residency.renttype? data.spouse.residency.renttype: 'Not specified.'}</b><br>
                                <b class="listInfo">Rental Fee :</b> <b class="info">${data.spouse.residency.rentamount? 'Php' +' '+ data.spouse.residency.rentamount: ' '}</b><br>
                                <b class="listInfo">Description/Remarks :</b> <b class="info">${data.spouse.residency.remarks? data.spouse.residency.remarks: 'Not specified.'}</b><br>
                                <%if(!data.spouse.residency.since){%>
                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                <%}%>
                                <%if(data.spouse.residency.since){%>
                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(data.spouse.residency.since)}</b><br>
                                <%}%>
                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.spouse.residency.yrsofstay? data.spouse.residency.yrsofstay: ' '}</b>
                            </td>
                        <%}%>
                    </tr>   
                </tr>   
                <tr>
                    <td valign="top"><b>Lot Occupancy</b></td>
                    <%if(data.spouse.lotoccupancy.type=="OWNED"){%>
                        <td><b class="listInfo">Type :</b> <b class="info">${data.spouse.lotoccupancy.type? data.spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                            <b class="listInfo">Description/Remarks :</b> <b class="info">${data.spouse.lotoccupancy.remarks? data.spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                            <%if(!data.spouse.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                            <%}%>
                            <%if(data.spouse.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <b class="info">${formatDate(data.spouse.lotoccupancy.since)}</b><br>
                            <%}%>
                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.spouse.lotoccupancy.yrsofstay? data.spouse.lotoccupancy.yrsofstay: ' '}</b>
                        </td>
                    <%}%>
                    <%if(data.spouse.lotoccupancy.type=="RENTED"){%>
                        <td><b class="listInfo">Type :</b> <b class="info">${data.spouse.lotoccupancy.type? data.spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                            <b class="listInfo">Rent Type :</b> <b class="info">${data.spouse.lotoccupancy.renttype? data.spouse.lotoccupancy.renttype: 'Not specified.'}</b><br>
                            <b class="listInfo">Rental Fee :</b> <b class="info">${data.spouse.lotoccupancy.rentamount? 'Php' +' '+ data.spouse.lotoccupancy.rentamount: ' '}</b><br>
                            <b class="listInfo">Description/Remarks :</b> <b class="info">${data.spouse.lotoccupancy.remarks? data.spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                            <%if(!data.spouse.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                            <%}%>
                            <%if(data.spouse.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <b class="info">${formatDate(data.spouse.lotoccupancy.since)}</b><br>
                            <%}%>
                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.spouse.lotoccupancy.yrsofstay? data.spouse.lotoccupancy.yrsofstay: ' '}</b>
                        </td>
                    <%}%>
                </tr>   
                <tr><td><b>Phone/Mobile No. :</b></td><td> ${data.spouse.phone? data.spouse.phone: '-'}</td></tr>
                <tr><td><b>CTC No. :</b></td><td> ${data.spouse.ctcno? data.spouse.ctcno: '-'}</td></tr>
            </table>
            <table>
                <tr>
                    <td valign="top">
                        <b>Relationship to the Principal Borrower :</b> <b class="fuchsia">${data.spouse.relation? data.spouse.relation: 'Spouse'}</b> 
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
        <%}%>
        <%if(data.borrower.principalSpouseFathersName){%>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Parents Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width="50%" valign="top">
                        <table>
                            <tr>
                                <td><b>Father's Name :</b></td>
                                <td>${data.borrower.principalSpouseFathersName? data.borrower.principalSpouseFathersName: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Birthdate :</b></td>
                                <td>${data.borrower.principalSpouseFathersBDate? data.borrower.principalSpouseFathersBDate: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Age :</b></td>
                                <td>${data.borrower.principalSpouseFathersAge? data.borrower.principalSpouseFathersAge: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Mother's Name :</b></td>
                                <td>${data.borrower.principalSpouseMothersName? data.borrower.principalSpouseMothersName: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Birthdate :</b></td>
                                <td>${data.borrower.principalSpouseMothersBDate? data.borrower.principalSpouseMothersBDate: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Age :</b></td>
                                <td>${data.borrower.principalSpouseMothersAge? data.borrower.principalSpouseMothersAge: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Address :</b></td>
                                <td>${data.borrower.principalSpouseAddress? data.borrower.principalSpouseAddress: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Remarks :</b></td>
                                <td>${data.borrower.principalSpouseOthersSpecs? data.borrower.principalSpouseOthersSpecs: '-'}</td>
                            </tr>
                        </td>
                    </table>
                </div>
            </table>
        <%}%>
        <%if(!data.principalSpouseSiblingsList) { %>
            <hr>
            <h3>No Spouse Siblings Information</h3>
         <%}else{%>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                    <h3 class="white"><b>Siblings Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.principalSpouseSiblingsList.each { ssl -> %>
                            <table>
                                <tr><td><b>Name :</b></td><td> ${ssl.name}</td></tr>
                                <%if(!ssl.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(ssl.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <j class="green">${formatDate(ssl.Birthdate)}</j></td></tr>
                                <%}%>
                                <%if(!ssl.age) { %>
                                    <tr><td><b>Age :</b></td><td><b class="red2">-</b></td></tr>
                                <%}%>
                                <%if(ssl.age) { %>
                                    <tr><td><b>Age :</b></td><td> ${ssl.age}</td></tr>
                                <%}%>
                                <%if(!ssl.address) { %>
                                    <tr><td><b>Address :</b></td><td><i class="red2">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(ssl.address) { %>
                                    <tr><td><b>Address :</b></td><td> ${ssl.address}</td></tr>
                                <%}%>
                            </table><hr>
                       <%}%><br>
                       <table>
                            <%if (data.principalSpouseSiblingsList.employmentList && data.principalSpouseSiblingsList.otherSourcesOfIncomeList) {%>
                                <tr>
                                    <th><b>SOURCE(S) OF INCOME</b></th>
                                </tr>
                            <%}%>
                            <%if (!data.principalSpouseSiblingsList.employmentList && !data.principalSpouseSiblingsList.otherSourcesOfIncomeList) {%>
                                <tr>
                                    <th><b>SOURCE(S) OF INCOME</b></th>
                                    <tr><i class="red2">Not Specified at present</i></tr>
                                </tr>
                            <%}%>
                        </table>
                        <div>
                            <%
                                def links = [];
                                if( data.principalSpouseSiblingsList.employmentList )   links << '<a href="viewSiblingsEmployment">Employment</a>';
                                if( data.principalSpouseSiblingsList.otherSourcesOfIncomeList ) links << '<a href="viewSiblingsOtherIncome">Other Income</a>';
                                if( links )
                                    println links.join(' | ');
                            %>
                        </div><!--<hr>-->
                    </td>
                </tr>
             </table>
         <%}%>
         <!--<hr>
         <table width="100%">
            <tr>
                <td bgcolor="gray">
                    <h3 class="white"><b>Other Related Information</b></h3>
                </td>
            </tr><hr>
            <table>
                <%if (data.principalSpouseSiblingsList.employmentList && data.principalSpouseSiblingsList.otherSourcesOfIncomeList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME</b></th>
                    </tr>
                <%}%>
                <%if (!data.principalSpouseSiblingsList.employmentList && !data.principalSpouseSiblingsList.otherSourcesOfIncomeList) {%>
                    <tr>
                        <th><b>SOURCE(S) OF INCOME</b></th>
                        <tr><i class="red2">Not Specified at present</i></tr>
                    </tr>
                <%}%>
            </table>
            <div>
                <%
                    def links = [];
                    if( data.principalSpouseSiblingsList.employmentList )   links << '<a href="viewSiblingsEmployment">Employment</a>';
                    if( data.principalSpouseSiblingsList.otherSourcesOfIncomeList ) links << '<a href="viewSiblingsOtherIncome">Other Income</a>';
                    if( links )
                        println links.join(' | ');
                %>
            </div><hr>
         </table>-->
    </body>
</html>