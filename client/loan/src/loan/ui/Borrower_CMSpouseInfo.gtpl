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
        <% if(!data.coMakerList.spouse) { %>
            <h3>No Co-Maker specified</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.coMakerList.each { cm -> %>
                            <!--<%if( data.coMakerList.hasPhoto ){%>-->
                                <table>
                                    <tr>
                                        <td valign="top"><b>Photo:</b></td>
                                        <tr>
                                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                                        </tr>
                                    </tr>
                                </table>
                             <!--<%}%>-->
                            <table>
                                <tr><td><b>Customer No. :</b></td><td> <b class="navy">${cm.spouse.contactno}</b></td></tr><br><br>
                                <tr><td><b>Name :</b></td><td> ${cm.spouse.lastname}, ${cm.spouse.firstname} 
                                    <%if(cm.spouse.middlename){%>
                                        <b class="gray">( ${cm.spouse.middlename.toUpperCase()} )</b></td>
                                    <%}%>
                                    <%if(cm.spouse.middlename){%>
                                        <b class="gray">( ${cm.spouse.middlename? cm.spouse.middlename: '-'} )</b></td>
                                    <%}%>
                                </tr>
                                <%if(cm.spouse.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(cm.spouse.birthdate)}</td></tr>
                                <%}%>
                                <%if(!cm.spouse.birthdate){%>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                                <%}%>
                                <%if(cm.spouse.age){%>
                                    <tr><td><b>Age :</b></td><td> ${cm.spouse.age}</td></tr>
                                <%}%>    
                                <%if(!cm.spouse.age){%>
                                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                                <%}%>
                                <%if(cm.spouse.civilstat){%>
                                    <tr><td><b>Marital Status :</b></td><td> ${cm.spouse.civilstat}</td></tr>
                                <%}%>
                                <tr><td><b>Citizenship :</b></td><td> ${cm.spouse.citizenship? cm.spouse.citizenship: '-'}</td></tr>     
                                <tr><td><b>Address :</b></td>
                                    <td>
                                        ${cm.spouse.currentaddress.address1}, 
                                        ${cm.spouse.currentaddress.address2? cm.spouse.currentaddress.address2: ' '},
                                        ${cm.spouse.currentaddress.city} 
                                        ${cm.spouse.currentaddress.province} 
                                        ${cm.spouse.currentaddress.zipcode} 
                                        ${cm.spouse.currentaddress.country}
                                    </td>
                                </tr>
                                <%if(cm.spouse.provincialaddress.address1){%>
                                    <tr><td><b>Provincial Address :</b></td>
                                        <td>
                                            ${cm.spouse.provincialaddress.address1}, 
                                            ${cm.spouse.provincialaddress.address2? cm.spouse.provincialaddress.address2: ' '},
                                            ${cm.spouse.provincialaddress.city} 
                                            ${cm.spouse.provincialaddress.province} 
                                            ${cm.spouse.provincialaddress.zipcode} 
                                            ${cm.spouse.provincialaddress.country}
                                        </td>
                                    </tr>
                                <%}%>
                                <tr>
                                    <td valign="top"><b>Residency/House</b></td>
                                    <tr>
                                        <%if(cm.spouse.residency.type=="OWNED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${cm.spouse.residency.type? cm.spouse.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.spouse.residency.remarks? cm.spouse.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!cm.spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(cm.spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.spouse.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.spouse.residency.yrsofstay? cm.spouse.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                        <%if(cm.spouse.residency.type=="RENTED"){%>
                                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${cm.spouse.residency.type? cm.spouse.residency.type: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rent Type :</b> <b class="info">${cm.spouse.residency.renttype? cm.spouse.residency.renttype: 'Not specified.'}</b><br>
                                                <b class="listInfo">Rental Fee :</b> <b class="info">${cm.spouse.residency.rentamount? 'Php' +' '+ cm.spouse.residency.rentamount: ' '}</b><br>
                                                <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.spouse.residency.remarks? cm.spouse.residency.remarks: 'Not specified.'}</b><br>
                                                <%if(!cm.spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                                <%}%>
                                                <%if(cm.spouse.residency.since){%>
                                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.spouse.residency.since)}</b><br>
                                                <%}%>
                                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.spouse.residency.yrsofstay? cm.spouse.residency.yrsofstay: ' '}</b>
                                            </td>
                                        <%}%>
                                    </tr>   
                                </tr>   
                                <tr>
                                    <td valign="top"><b>Lot Occupancy</b></td>
                                    <%if(cm.spouse.lotoccupancy.type=="OWNED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${cm.spouse.lotoccupancy.type? cm.spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.spouse.lotoccupancy.remarks? cm.spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!cm.spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(cm.spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.spouse.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.spouse.lotoccupancy.yrsofstay? cm.spouse.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>
                                    <%if(cm.spouse.lotoccupancy.type=="RENTED"){%>
                                        <td><b class="listInfo">Type :</b> <b class="info">${cm.spouse.lotoccupancy.type? cm.spouse.lotoccupancy.type: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rent Type :</b> <b class="info">${cm.spouse.lotoccupancy.renttype? cm.spouse.lotoccupancy.renttype: 'Not specified.'}</b><br>
                                            <b class="listInfo">Rental Fee :</b> <b class="info">${cm.spouse.lotoccupancy.rentamount? 'Php' +' '+ cm.spouse.lotoccupancy.rentamount: ' '}</b><br>
                                            <b class="listInfo">Description/Remarks :</b> <b class="info">${cm.spouse.lotoccupancy.remarks? cm.spouse.lotoccupancy.remarks: 'Not specified.'}</b><br>
                                            <%if(!cm.spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                            <%}%>
                                            <%if(cm.spouse.lotoccupancy.since){%>
                                                <b class="listInfo">Since :</b> <b class="info">${formatDate(cm.spouse.lotoccupancy.since)}</b><br>
                                            <%}%>
                                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${cm.spouse.lotoccupancy.yrsofstay? cm.spouse.lotoccupancy.yrsofstay: ' '}</b>
                                        </td>
                                    <%}%>
                                </tr>   
                                <tr><td><b>Phone/Mobile No. :</b></td><td> ${cm.spouse.phone? cm.spouse.phone: '-'}</td></tr>
                                <tr><td><b>CTC No. :</b></td><td> ${cm.spouse.ctcno? cm.spouse.ctcno: '-'}</td></tr>
                             </table>
                        <%}%>
                    </td>
                 </tr>
             </table>
        <% } %>
    </body>
</html>