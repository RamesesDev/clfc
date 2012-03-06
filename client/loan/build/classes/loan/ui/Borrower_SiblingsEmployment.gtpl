<%
    def dec_formatter = new java.text.DecimalFormat('#,##0.00');

    def format = {amt-> 
        return dec_formatter.format(amt); 
    };
%>
<html>
    <style>
       body{font-family:arial;font-size:9px;}
       h3.white { color: #ffffff; }

       .teal { color: teal; font-size: 9px; font-weight:bold;}
    </style>
    <body>
        <% if(!data.principalSpouseSiblingsList) { %>
            <h3>No employment specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width="50%" valign="top">
                        <% data.principalSpouseSiblingsList.each { pssl -> %>
                            <% pssl.employmentList.each { employment -> %>    
                                <div align="center"><h2>${employment.employer}</h2></div>
                                <table>
                                    <tr><td><b>Tel. No. :</b></td><td> ${employment.telno}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${employment.address}</td></tr>
                                    <tr><td><b>Position :</b></td><td> ${employment.position}</td></tr>
                                    <tr><td><b>Monthly Salary :</b></td><td><b>Php</b> <b class="teal">${employment.salary}</b></td></tr>
                                    <tr><td><b>Length of service :</b></td><td> ${employment.years}</td></tr>
                                    <tr><td><b>Status :</b></td><td> ${employment.status}</td></tr>
                                    <br><br><br><br>
                                    <tr><td><b>Remarks/Others :</b></td><td> ${employment.others? employment.others: '-'}</td></tr>
                                </table>
                                <%if(pssl.employmentList.size() > 0){%>
                                    <hr>
                                    <br>
                                <%}%>
                            <%}%>
                       <%}%>
                    </td>  
                </tr>
           </table> 
       <% } %>
    </body>
</html>