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

       .amount{ color: gray; font-size: 9px;}
       .date{ color: green; font-size:9px;}
       .date2{ color: red; font-size:9px;}
       .cash{ color: blue; font-size:9px;}
       .info, .grid { margin-left: 10px; }
       .sender { color: gray; font-size: 8px; }
    </style>
    <body>
        <% if(!data.otherLendingList) { %>
            <h3>No Other Lending</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td width="50%" valign="top">
                        <% data.otherLendingList.each { lending -> %>
                            <div align="center"><h2>${lending.company}</h2></div>
                            <table>
                                <tr><td><b>Tel. No. :</b></td><td> ${lending.kindofLoan}</td></tr>
                                <tr><td><b>Address :</b></td><td> ${lending.address}</td></tr>
                                <tr><td><b>Loan Amount :</b></td><td><b>Php</b> <b class="amount">${format(lending.loanAmount)}</td></tr>
                                <tr><td><b>Date Granted :</b></td><td><b class="date"> ${formatDate(lending.dateGranted)}</td></tr>
                                <tr><td><b>Matrity Date :</b></td><td> <b class="date2">${formatDate(lending.maturityDate)}</td></tr>
                                <tr><td><b>Term :</b></td><td> ${lending.term}</td></tr>
                                <tr><td><b>Interest Rate :</b></td><td> ${format(lending.interestRate)}</td></tr>
                                <tr><td><b>Mode of Payment :</b></td><td> ${lending.modeofPayment}</td></tr>
                                <tr><td><b>Payment :</b></td><td><b>Php</b> <b class="cash">${format(lending.lendingPayment)}</td></tr>
                                <tr><td><b>Collateral Offered :</b></td><td> ${lending.collateralOffered}</td></tr>
                                <tr><td><b>Remarks :</b></td><td> ${lending.remarks? lending.remarks: '-'}</td></tr>
                                <tr><td><b>Others Specifications :</b></td><td> ${lending.specs? lending.specs: '-'}</td></tr>    
                            </table>
                            <%if(data.otherLendingList.size() > 0){%>
                                <hr>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
           </table> 
       <% } %>
    </body>
</html>