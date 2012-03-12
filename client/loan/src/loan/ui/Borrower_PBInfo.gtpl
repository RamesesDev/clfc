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
    </style>
    <body>
        <%
            import com.rameses.util.*;

            String tpl = "loan/ui/Borrower_PB_photo.gtpl";
            println TemplateProvider.instance.getResult( tpl, [data:data] );
        %>
        <hr><br>
        
        <% if(!data.borrower) { %>
            <h3>No Borrower specified</h3>
        <% } else { %>
            <!--
            <%if( !data.borrower.hasPhoto ){%>
               <table>     
                    <tr>
                        <td valign="top"><b>Photo:</b></td>
                        <tr>
                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                        </tr>
                    </tr>
               </table>
               <hr><br>
            <%}%>
            -->
            <!--
            <%if( data.borrower.hasPhoto ){%>   
                <table>
                    <tr>
                        <td valign="top"><b>Photo:</b></td>
                        <tr>
                            <td valign="top"><td><b class="red">No Photo(s) Attached</td>
                        </tr>
                    </tr>
               </table>
               
            <%}%>
            -->
            <table>
                <%if( data.borrower.contactno ){%>
                    <tr>
                        <td><b>Customer No. :</b></td><td> <b class="navy">${data.borrower.contactno}</b></td>
                    </tr>
                <%}%>
                <%if( data.borrower.lastname ){%>
                    <tr><td><b>Name :</b></td><td> ${data.borrower.lastname}, <%if( data.borrower.firstname ){%>${data.borrower.firstname}<%}%>
                        <%if(!data.borrower.middlename){%>
                            <b class="gray">( ${data.borrower.middlename? data.borrower.middlename: '-'} )</b></td>
                        <%}%>
                        <%if(data.borrower.middlename){%>
                            <b class="gray">( ${data.borrower.middlename.toUpperCase()} )</b></td>
                        <%}%>
                    </tr>
                <%}%>
                <%if(!data.borrower.birthdate){%>
                    <tr><td><b>Birthdate :</b></td><td> <i class="red2">Not specified.</i></td></tr>
                <%}%>
                <%if(data.borrower.birthdate){%>
                    <tr><td><b>Birthdate :</b></td><td> ${formatDate(data.borrower.birthdate)}</td></tr>
                <%}%>
                <%if(!data.borrower.age){%>
                    <tr><td><b>Age :</b></td><td> <i class="red2">No age specifed.</td></tr>
                <%}%>
                <%if(data.borrower.age){%>
                    <tr><td><b>Age :</b></td><td> ${data.borrower.age}</td></tr>
                <%}%>
                <%if(data.borrower.civilstat){%>
                    <tr><td><b>Marital Status :</b></td><td> ${data.borrower.civilstat}</td></tr>
                <%}%>
                <%if(data.borrower.civilstat){%>
                    <tr><td><b>Citizenship :</b></td><td> ${data.borrower.citizenship? data.borrower.citizenship: '-'}</td></tr>     
                <%}%>
                <tr><td><b>City Address :</b></td>
                    <td>
                        ${data.borrower.currentaddress.address1}, 
                        ${data.borrower.currentaddress.address2? data.borrower.currentaddress.address2: ' '},
                        ${data.borrower.currentaddress.city} 
                        ${data.borrower.currentaddress.province} 
                        ${data.borrower.currentaddress.zipcode} 
                        ${data.borrower.currentaddress.country}
                    </td>
                </tr>
                <%if(data.borrower.provincialaddress.address1){%>
                    <tr><td><b>Provincial Address :</b></td>
                        <td>
                            ${data.borrower.provincialaddress.address1}, 
                            ${data.borrower.provincialaddress.address2? data.borrower.provincialaddress.address2: ' '},
                            ${data.borrower.provincialaddress.city} 
                            ${data.borrower.provincialaddress.province} 
                            ${data.borrower.provincialaddress.zipcode} 
                            ${data.borrower.provincialaddress.country}
                        </td>
                    </tr>
                <%}%>
                <tr>
                    <td valign="top"><b>Residency/House</b></td>
                    <tr>
                         <%if(data.borrower.residency.type=="OWNED"){%>
                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${data.borrower.residency.type? data.borrower.residency.type: 'Not specified.'}</b><br>                                    
                                <b class="listInfo">Description/Remarks :</b> <b class="info">${data.borrower.residency.remarks? data.borrower.residency.remarks: 'Not specified.'}</b><br>
                                <%if(!data.borrower.residency.since){%>
                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                <%}%>
                                <%if(data.borrower.residency.since){%>
                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(data.borrower.residency.since)}</b><br>
                                <%}%>
                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.borrower.residency.yrsofstay? data.borrower.residency.yrsofstay: ' '}</b>
                            </td>
                         <%}%>
                         <%if(data.borrower.residency.type=="RENTED"){%>
                            <td valign="top"><td><b class="listInfo">Type :</b> <b class="info">${data.borrower.residency.type? data.borrower.residency.type: 'Not specified.'}</b><br>
                                <b class="listInfo">Rent Type :</b> <b class="info">${data.borrower.residency.renttype? data.borrower.residency.renttype: 'Not specified.'}</b><br>
                                <b class="listInfo">Rental Fee :</b> <b class="info">${data.borrower.residency.rentamount? 'Php' +' '+ data.borrower.residency.rentamount: ' '}</b><br>
                                <b class="listInfo">Description/Remarks :</b> <b class="info">${data.borrower.residency.remarks? data.borrower.residency.remarks: 'Not specified.'}</b><br>
                                <%if(!data.borrower.residency.since){%>
                                    <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                                <%}%>
                                <%if(data.borrower.residency.since){%>
                                    <b class="listInfo">Since :</b> <b class="info">${formatDate(data.borrower.residency.since)}</b><br>
                                <%}%>
                                <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.borrower.residency.yrsofstay? data.borrower.residency.yrsofstay: ' '}</b>
                            </td>
                         <%}%>
                    </tr>   
                </tr>
                <tr>
                    <%if(data.borrower.lotoccupancy.type=="OWNED"){%>
                        <td valign="top"><b>Lot Occupancy</b></td>
                        <td>
                            <b class="listInfo">Type :</b> <b class="info">${data.borrower.lotoccupancy.type? data.borrower.lotoccupancy.type: 'Not specified.'}</b><br>
                            <b class="listInfo">Description/Remarks :</b> <b class="info">${data.borrower.lotoccupancy.remarks? data.borrower.lotoccupancy.remarks: 'Not specified.'}</b><br>
                            <%if(!data.borrower.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                            <%}%>
                            <%if(data.borrower.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <b class="info">${formatDate(data.borrower.lotoccupancy.since)}</b><br>
                            <%}%>
                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.borrower.lotoccupancy.yrsofstay? data.borrower.lotoccupancy.yrsofstay: ' '}</b>
                        </td>
                    <%}%>
                    <%if(data.borrower.lotoccupancy.type=="RENTED"){%>
                        <td valign="top"><b>Lot Occupancy</b></td>
                        <td>
                            <b class="listInfo">Type :</b> <b class="info">${data.borrower.lotoccupancy.type? data.borrower.lotoccupancy.type: 'Not specified.'}</b><br>
                            <b class="listInfo">Rent Type :</b> <b class="info">${data.borrower.lotoccupancy.renttype? data.borrower.lotoccupancy.renttype: 'Not specified.'}</b><br>
                            <b class="listInfo">Rental Fee :</b> <b class="info">${data.borrower.lotoccupancy.rentamount? 'Php' +' '+ data.borrower.lotoccupancy.rentamount: ' '}</b><br>
                            <b class="listInfo">Description/Remarks :</b> <b class="info">${data.borrower.lotoccupancy.remarks? data.borrower.lotoccupancy.remarks: 'Not specified.'}</b><br>
                            <%if(!data.borrower.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <i class="red2">Not specified.</i><br>
                            <%}%>
                            <%if(data.borrower.lotoccupancy.since){%>
                                <b class="listInfo">Since :</b> <b class="info">${formatDate(data.borrower.lotoccupancy.since)}</b><br>
                            <%}%>
                            <b class="listInfo">Yrs. of stay :</b> <b class="info">${data.borrower.lotoccupancy.yrsofstay? data.borrower.lotoccupancy.yrsofstay: ' '}</b>
                        </td>
                    <%}%>
                </tr>
                <%if(data.borrower.phone){%>
                    <tr><td><b>Phone/Mobile No. :</b></td><td> ${data.borrower.phone? data.borrower.phone: '-'}</td></tr>
                <%}%>
                <%if(data.borrower.ctcno){%>
                    <tr><td><b>CTC No. :</b></td><td> ${data.borrower.ctcno? data.borrower.ctcno: '-'}</td></tr>
                <%}%>
           </table>
        <%}%>
    </body>
</html>