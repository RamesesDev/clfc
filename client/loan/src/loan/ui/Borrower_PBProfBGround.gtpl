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

       .info, .grid { margin-left: 10px; }
       .inv{ color: red; font-size:9px; font-weight:bold;}
       .inv2{ color: blue; font-size:9px;}
       .cash{ color: gray; font-size:9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}

       .listInfo { color: green; font-size: 9px; }
       .info2 { color: black; font-size: 9px; }
    </style>
    <body>
        <%if(!data.principalProfessionalBackgroundList) {%>
            <h3>No Other Information</h3>
        <%}else{%>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.principalProfessionalBackgroundList.each { prof -> %>
                            <div align="center"><h2>${prof.schoolAttended}</h2></div>
                            <table>
                                <tr><td><b>Profession :</b></td><td> ${prof.profession}</td></tr>
                                <tr><td><b>Date Graduated :</b></td><td> ${prof.dateGraduated}</td></tr>
                                <tr><td><b>Remarks :</b></td><td> ${prof.othersSpecs}</td></tr>
                            </table>
                            <%if(!data.principalProfessionalBackgroundList) {%>
                                
                            <%}%>
                            <%if(data.principalProfessionalBackgroundList.size() > 0) {%>
                                <br><hr><br>
                            <%}%>
                       <%}%>
                    </td>
                </tr>
             </table>
        <%}%>
    </body>
</html>