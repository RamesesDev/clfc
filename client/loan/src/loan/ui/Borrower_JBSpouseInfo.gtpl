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
        <% if(!data.jointBorrowerList.spouse) { %>
            <h3>No Joint Borrower Spouse specified</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.spouse { spouse -> %>                
                            <table>
                                <tr><td><b>Customer No. :</b></td><td> <b class="navy">${spouse.contactno}</b></td></tr><br><br>
                                <tr><td><b>Name :</b></td><td> ${spouse.lastname}, ${spouse.firstname}
                                    <%if(spouse.middlename){%>
                                        <b class="gray">( ${spouse.middlename.toUpperCase()} )</b></td>
                                    <%}%>
                                    <%if(spouse.middlename){%>
                                        <b class="gray">( ${spouse.middlename? spouse.middlename: '-'} )</b></td>
                                    <%}%>
                                </tr>
                                <%if(spouse.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(spouse.birthdate)}</td></tr>
                                <%}%>
                                <%if(!spouse.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                                <%}%>
                                <%if(spouse.age){%>
                                    <tr><td><b>Age :</b></td><td> ${spouse.age}</td></tr>
                                <%}%>    
                                <%if(!spouse.age){%>
                                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                                <%}%>
                                <%if(spouse.civilstat){%>
                                    <tr><td><b>Marital Status :</b></td><td> ${spouse.civilstat}</td></tr>
                                <%}%>
                                <tr><td><b>Citizenship :</b></td><td> ${spouse.citizenship? spouse.citizenship: '-'}</td></tr>     
                                <tr><td><b>Address :</b></td>
                                    <td>
                                        ${spouse.currentaddress.address1}, 
                                        ${spouse.currentaddress.address2? spouse.currentaddress.address2: ' '},
                                        ${spouse.currentaddress.city} 
                                        ${spouse.currentaddress.province} 
                                        ${spouse.currentaddress.zipcode} 
                                        ${spouse.currentaddress.country}
                                    </td>
                                </tr>
                                <%if(spouse.provincialaddress.address1){%>
                                    <tr><td><b>Provincial Address :</b></td>
                                        <td>
                                            ${spouse.provincialaddress.address1}, 
                                            ${spouse.provincialaddress.address2? spouse.provincialaddress.address2: ' '},
                                            ${spouse.provincialaddress.city} 
                                            ${spouse.provincialaddress.province} 
                                            ${spouse.provincialaddress.zipcode} 
                                            ${spouse.provincialaddress.country}
                                        </td>
                                    </tr>
                                <%}%>
                                <tr>                                   
                                    <td valign="top"><b>Residency/House</b></td>
                                    <tr>
                                        <%if(spouse.residency.type=="OWNED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${spouse.residency.type? spouse.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${spouse.residency.remarks? spouse.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(spouse.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${spouse.residency.yrsofstay? spouse.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                        <%if(spouse.residency.type=="RENTED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${spouse.residency.type? spouse.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rent Type :</b> <b class="info">${spouse.residency.renttype? spouse.residency.renttype: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rental Fee :</b> <b class="info">${spouse.residency.rentamount? 'Php' +' '+ spouse.residency.rentamount: ' '}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${spouse.residency.remarks? spouse.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(spouse.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${spouse.residency.yrsofstay? spouse.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                    </tr>
                                </tr>   
                                <tr>
                                    <%if(spouse.lotoccupancy.type=="OWNED"){%>
                                        <td valign="top"><b>Lot Occupancy</b></td>
                                        <td>
                                            <b class="listInfo">Type :</b> <b class="info">${spouse.lotoccupancy.type? spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${spouse.lotoccupancy.remarks? spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(spouse.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${spouse.lotoccupancy.yrsofstay? spouse.lotoccupancy.yrsofstay: ' '}</b>
                                         </td>
                                    <%}%>
                                    <%if(spouse.lotoccupancy.type=="RENTED"){%>
                                        <td valign="top"><b>Lot Occupancy</b></td>
                                        <td>
                                            <b class="listInfo">Type :</b> <b class="info">${spouse.lotoccupancy.type? spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rent Type :</b> <b class="info">${spouse.lotoccupancy.renttype? spouse.lotoccupancy.renttype: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rental Fee :</b> <b class="info">${spouse.lotoccupancy.rentamount? 'Php' +' '+ spouse.lotoccupancy.rentamount: ' '}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${spouse.lotoccupancy.remarks? spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(spouse.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${spouse.lotoccupancy.yrsofstay? spouse.lotoccupancy.yrsofstay: ' '}</b>
                                         </td>
                                    <%}%>    
                                </tr>
                                <tr><td><b>Phone/Mobile No. :</b></td><td> ${spouse.phone? spouse.phone: '-'}</td></tr>
                                <tr><td><b>CTC No. :</b></td><td> ${spouse.ctcno? spouse.ctcno: '-'}</td></tr>
                             </table>
                             <%if( !data.jointBorrowerList.spouse.hasPhoto ){%>
                                <hr>
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
                        <%}%>
                    </td>
                 </tr>
             </table>
        <%}%>
    </body>
</html>