<html>
    <style>
       body{font-family:arial;font-size:9px;}
       h3.white { color: #ffffff; }

       .info{ color: blue; font-size: 8px;}
       .inv{ color: red; font-size:9px; font-weight:bold;}
       .inv2{ color: blue; font-size:9px;}
       .green{ color: green; font-size:9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}
       .listInfo { color: green; font-size: 9px; }
       .info2 { color: black; font-size: 9px; }
    </style>
    <body>
        <%if(!data.principalSavingsAccountsList) { %>
            <h3>No Savings Account for this application</h3>
        <%}else{ %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Savings Account Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.principalSavingsAccountsList.each { savings -> %>
                            <h2>${savings.bankName}</h2>
                            <table>
                                <tr><td><b>Branch :</b></td><td> ${savings.branch}</td></tr>
                                <tr><td><b>Type :</b></td><td> ${savings.type? savings.type: '-'}</td></tr>
                                <tr><td><b>Status :</b></td><td> ${savings.status}</td></tr>
                                <tr><td><b>Remarks :</b></td><td> ${savings.othersSpecs.toUpperCase()}</td></tr>
                            </table>
                            <%if(!data.principalSavingsAccountsList){%>
                                
                            <%}%>
                            <%if(data.principalSavingsAccountsList.size() > 0){%>
                                <br><hr><br>
                            <%}%>
                        <%}%>
                     </td>
                 </tr>
             </table>
         <%}%>
    </body>
</html>