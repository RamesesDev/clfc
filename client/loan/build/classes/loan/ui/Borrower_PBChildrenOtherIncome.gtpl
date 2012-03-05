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

       .info{ color: blue; font-size: 8px;}
       .inv{ color: red; font-size:9px; font-weight:bold;}
       .inv2{ color: blue; font-size:9px;}
       .green{ color: green; font-size:9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}

       .listInfo { color: green; font-size: 9px; }
       .info2 { color: black; font-size: 9px; }
    </style>
    <body>
        <% if(!data.childrenList.otherSourcesOfIncomeList) { %>
            <h3>No business specified for this application</h3>
        <%}else{%>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Other Source(s) of Income Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.childrenList.each { child -> %>
                            <% child.otherSourcesOfIncomeList.each { otherIncome -> %>
                                <div align="center"><h2>${otherIncome.kindOfBusiness}</h2></div>
                                <table>
                                    <tr><td><b>Gross Net/Income :</b></td><td>Php ${otherIncome.grossNetIncome? otherIncome.grossNetIncome: '-'}</td></tr>
                                    <tr><td><b>Remarks :</b></td><td> ${otherIncome.remarks? otherIncome.remarks: '-'}</td></tr>
                                </table>
                                <br><hr><br>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
             </table>
         <%}%>
    </body>
</html>