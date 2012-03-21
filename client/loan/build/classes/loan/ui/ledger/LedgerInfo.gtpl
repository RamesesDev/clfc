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
        body{ font-family:arial;font-size:9px; background: #e3e3e3; }
        th { text-align: left; padding-right: 5px; }

        .container {
            background: white;
            border-color: #d4d4d4;
            border-width: 1px;
            border-style: solid;
            padding-bottom: 30px;
        }
        .box { 
            background: gray; color: white;
            border-color: #C1CDCD;
            border-width: 1px;
            border-style: solid;
            padding: 2px 5px 2px 5px; margin: 3px 0px 3px 0px; 
        }

        .amount { color: navy; }
        .bal { font-weight: bold; color: maroon; }
        .red { color: red; font-size: 9px; font-weight:bold;}
        .green { color: green; font-size: 9px; font-weight:bold;}
        .navy { color: navy; font-size: 9px; font-weight:bold;}
    </style>
    <body>
        <table width="98%" align="center">
            <tr>
                <td valign="top">

                    <div class="container">
                        <div class="box">
                            <b>&nbsp;Loan Information</b>
                        </div>
                        <table width="95%" align="center">
                            <tr>
                                <th>Loan Amount:</th>
                                <td class="navy">${format(ledger.loanAmount)}</td>
                            </tr>
                            <tr>
                                <th>Date Granted:</th>
                                <td class="green">${formatDate(ledger.dtcreated)}</td>
                            </tr>
                            <tr>
                                <th>Maturity Date:</th>
                                <td class="red">${formatDate(ledger.maturityDate)}</td>
                            </tr>
                            <tr>
                                <th>Daily Payment:</th>
                                <td>${ledger.dailyPayment}</td>
                            </tr>
                            <tr>
                                <th>Term:</th>
                                <td>${ledger.term} Days</td>
                            </tr>
                            <tr>
                                <th>Interest Rate:</th>
                                <td>${format( ledger.interestrate*100.00 )} %</td>
                            </tr>
                            <tr>
                                <th>Last Payment Schedule Paid:</th>
                                <td>${ledger.lastDatePaid? formatDate(ledger.lastDatePaid) : ' - '}</td>
                            </tr>
                            <% if( ledger.fullyPaidAmt ) { %>
                                <tr>
                                    <th>Amount Fully Paid:</th>
                                    <td>${ledger.fullyPaidAmt}</td>
                                </tr>
                                <tr>
                                    <th>Date Fully Paid:</th>
                                    <td>${formatDate(ledger.dateFullyPaid)}</td>
                                </tr>
                            <% } %>
                            <tr>
                                <th>Total Absences:</th>
                                <td>${ledger.totalAbsent? ledger.totalAbsent : 0}</td>
                            </tr>
                        </table>
                    </div>

                </td>
                <td width="5">&nbsp;</td>
                <td valign="top">
                    
                    <div class="container">
                        <div class="box">
                            <b>&nbsp;Balances</b>
                        </div>
                        <table width="95%" align="center">
                            <tr>
                                <th>Actual/Principal Balance:</th>
                                <td align="right">
                                    <font color="red"><b class="amount">${format(ledger.principalBalance)}</b></font>
                                    <!-- Actual/principal balance=no.of days(120)- 
                                         no. of total days paid * interest + outstanding balance -->
                                    
                                    <!--<font color="red"><b class="amount">${(format(ledger.term) - (ledger.maturityDate)) * (ledger.interestrate*100.00) + (ledger.principalBalance)}</b></font>-->
                                </td>
                            </tr>
                            <br>
                            </br>
                            <tr>
                                <th>Outstanding Balance:</th>
                                <td align="right">
                                    <b class="bal">${format(ledger.principalBalance)}</b>
                                </td>
                            </tr>
                            <tr>
                                <th>Total Principal Payment:</th>
                                <td align="right">
                                    <b class="amount">${format(ledger.totalPrincipalPaid)}</b>
                                </td>
                            </tr>
                            <%if(!ledger.addedInterest || ledger.addedInterest==null){%>
                                <tr>
                                    <th>Additional Interest Payment:</th>
                                    <td align="right">
                                        <b class="amount">0.00</b>
                                    </td>
                                </tr>
                            <%}%>
                            <%if(ledger.addedInterest){%>
                                <tr>
                                    <th>Additional Interest Payment:</th>
                                    <td align="right">
                                        <b class="amount">${format(ledger.addedInterest)}</b>
                                    </td>
                                </tr>
                            <%}%>
                            <tr>
                                <th>Total Interest Payment:</th>
                                <td align="right">
                                    <b class="amount">${format(ledger.totalInterestPaid)}</b>
                                </td>
                            </tr>
                            <tr>
                                <th>Total Penalty Payment:</th>
                                <td align="right">
                                    <b class="amount">${format(ledger.totalPenaltyPaid)}</b>
                                </td>
                            </tr>
                            <tr>
                                <th>Total Payment:</th>
                                <td align="right">
                                    <b class="amount">${format(ledger.totalPaid)}</b>
                                </td>
                            </tr>
                            <br><br>
                        </table>
                    </div>

                </td>
            </tr>
        </table>
    </body>
</html>
