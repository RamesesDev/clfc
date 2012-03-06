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
        <% if(!data.principalSpouseSiblingsList.otherSourcesOfIncomeList) { %>
            <h3>No other income specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width ="50%" valign="top">
                        <% data.principalSpouseSiblingsList.each { pssl -> %>
                            <% pssl.otherSourcesOfIncomeList.each { otherIncome -> %>
                                <div align="center"><h2>${otherIncome.kindOfBusiness}</h2></div>
                                <table>
                                    <tr><td><b>Gross Net/Income :</b></td><td>Php ${otherIncome.grossNetIncome? otherIncome.grossNetIncome: '-'}</td></tr>
                                    <tr><td><b>Remarks :</b></td><td> ${otherIncome.remarks? otherIncome.remarks: '-'}</td></tr>
                                </table>
                                <%if(pssl.otherSourcesOfIncomeList.size() > 0){%>
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