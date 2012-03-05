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
            <h3>No other income specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.siblingsList.each { sl -> %>
                                <table>
                                    <tr><td><b>Name :</b></td><td> ${sl.name? ssl.name: '-'}</td></tr>
                                    <tr><td><b>Birthdate :</b></td><td> ${sl.Birthdate}</td></tr>
                                    <tr><td><b>Age :</b></td><td> ${sl.age}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${sl.address}</td></tr>
                                </table>
                                <%if(!jb.sblingsList){%>
                                    
                                <%}%>
                                <%if(jb.sblingsList.size() > 0){%>
                                    <hr><br>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.spouseSiblingsList.each { ssl -> %>
                                <table>
                                    <tr><td><b>Name :</b></td><td> ${ssl.name? ssl.name: '-'}</td></tr>
                                    <tr><td><b>Birthdate :</b></td><td> ${ssl.Birthdate}</td></tr>
                                    <tr><td><b>Age :</b></td><td> ${ssl.age}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${ssl.address}</td></tr>
                                </table>
                                <%if(!jb.spouseSiblingsList){%>
                                    
                                <%}%>
                                <%if(jb.spouseSiblingsList.size() > 0){%>
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