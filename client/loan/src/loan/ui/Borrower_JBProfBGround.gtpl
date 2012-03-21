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
        <%} else {%>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.profBackgroundList.each { prof -> %>
                                <div align="center"><h2>${prof.schoolAttended? prof.schoolAttended: '-'}</h2></div>
                                <table>
                                    <tr><td><b>Profession :</b></td><td> ${prof.profession? prof.profession: '-'}</td></tr>
                                    <tr><td><b>Date Graduated :</b></td><td> ${prof.dateGraduated}</td></tr>
                                    <tr><td><b>Remarks :</b></td><td> ${prof.othersSpecs}</td></tr>
                                </table>
                                <%if(!jb.profBackgroundList){%>
                                    
                                <%}%>
                                <%if(jb.profBackgroundList.size() > 0){%>
                                    <hr><br>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
           </table> 
       <%}%>
    </body>
</html>