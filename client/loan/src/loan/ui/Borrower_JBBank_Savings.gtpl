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
        <% if(!data.jointBorrowerList) { %>
            <h3>No Bank Account specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Savings Account Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.savingsAccountsList.each { savings -> %>
                                <h2>${savings.bankName}</h2>
                                <table>
                                    <tr><td><b>Branch :</b></td><td> ${savings.branch}</td></tr>
                                    <tr><td><b>Type :</b></td><td> ${savings.type? savings.type: '-'}</td></tr>
                                    <tr><td><b>Status :</b></td><td> ${savings.status}</td></tr>
                                    <tr><td><b>Remarks :</b></td><td> ${savings.othersSpecs.toUpperCase()}</td></tr>
                                </table>
                                <%if(!jb.savingsAccountsList){%>
                                    
                                <%}%>
                                <%if(jb.savingsAccountsList.size() > 0){%>
                                    <hr><br>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
           </table> 
       <% } %>
    </body>
</html>