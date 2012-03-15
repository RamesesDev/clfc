<%
    def dec_formatter = new java.text.DecimalFormat('#,###,##0.00');

    def format = {amt-> 
        return dec_formatter.format(amt); 
    };

    def dt_parser = new java.text.SimpleDateFormat('yyyy-MM-dd');
    def dt_formatter = new java.text.SimpleDateFormat('MMMM dd, yyyy');
    def dt_formatter2 = new java.text.SimpleDateFormat('MMMM dd, yyyy @ HH:mm:ss');
    
    def formatDate = { date->
        if( date instanceof String ) date = dt_parser.parse( date );
        return dt_formatter.format( date );
    };

    def formatDate2 = { date->
        if( date instanceof String ) date = dt_parser.parse( date );
        return dt_formatter2.format( date );
    };
%>
<html>
    <style>
       body{font-family:arial;font-size:9px;}
       h3.white { color: #ffffff; }

       .info{ color: blue; font-size: 8px;}
       .inv{ color: red; font-size:9px; font-weight:bold;}
       .inv2{ color: blue; font-size:9px;}
       .cash{ color: gray; font-size:9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}
       .maroon { color: maroon; font-size: 9px; }
       .green { color: green; font-size: 9px; }
       .listInfo { color: green; font-size: 9px; }
       .info2 { color: black; font-size: 9px; }
    </style>
    <body>
        <%if(!data.childrenList) { %>
            <h3>No Family Background Information</h3>
        <%}else{ %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Principal Children(s) Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.childrenList.each { family -> %>
                            <table>
                                <tr><td><b>Name :</b></td><td> ${family.name}</td></tr>
                                <%if(!family.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="maroon">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(family.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <j class="green">${formatDate2(family.Birthdate)}</j></td></tr>
                                <%}%>
                                <%if(!family.age) { %>
                                    <tr><td><b>Age :</b></td><td><b class="maroon">-</b></td></tr>
                                <%}%>
                                <%if(family.age) { %>
                                    <tr><td><b>Age :</b></td><td> ${family.age}</td></tr>
                                <%}%>
                                <%if(!family.address) { %>
                                    <tr><td><b>Address :</b></td><td><i class="maroon">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(family.address) { %>
                                    <tr><td><b>Address :</b></td><td> ${family.address}</td></tr>
                                <%}%>
                            </table><hr>
                        <%}%>
                        <%if (data.childrenList) {%>
                            <table>
                                <%if (data.childrenList.employmentList && data.childrenList.otherSourcesOfIncomeList) {%>
                                    <tr>
                                        <th><b>SOURCE(S) OF INCOME</b></th>
                                    </tr>
                                <%}%>
                                <%if (!data.childrenList.employmentList && !data.childrenList.otherSourcesOfIncomeList) {%>
                                    <tr>
                                        <th><b>SOURCE(S) OF INCOME</b></th>
                                        <tr><i class="red2">Not Specified at present</i></tr>
                                    </tr>
                                <%}%>
                           </table>
                           <div>
                                <%
                                    def links = [];
                                    if( data.childrenList.employmentList )   links << '<a href="viewChildrenEmployment">Employment</a>';
                                    if( data.childrenList.otherSourcesOfIncomeList ) links << '<a href="viewChildrenOtherIncome">Other Income</a>';
                                    if( links )
                                        println links.join(' | ');
                                %>
                           </div>
                           <hr>
                        <%}%>
                    </td>
                </tr>
            </table>
        <%}%>
        <%if(!data.borrower.principalFathersName){%>
            <h3>No Principal Parents Information</h3>
            <br>
        <%}else{%>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Principal Parents Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width="50%" valign="top">
                        <table>
                            <tr>
                                <td><b>Father's Name :</b></td>
                                <td>${data.borrower.principalFathersName? data.borrower.principalFathersName: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Birthdate :</b></td>
                                <td>${data.borrower.principalFathersBDate? data.borrower.principalFathersBDate: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Age :</b></td>
                                <td>${data.borrower.principalFathersAge? data.borrower.principalFathersAge: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Mother's Name :</b></td>
                                <td>${data.borrower.principalMothersName? data.borrower.principalMothersName: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Birthdate :</b></td>
                                <td>${data.borrower.principalMothersBDate? data.borrower.principalMothersBDate: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Age :</b></td>
                                <td>${data.borrower.principalMothersAge? data.borrower.principalMothersAge: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Address :</b></td>
                                <td>${data.borrower.principalParentsAddress? data.borrower.principalParentsAddress: '-'}</td>
                            </tr>
                            <tr>
                                <td><b>Remarks :</b></td>
                                <td>${data.borrower.principalOthersSpecs? data.borrower.principalOthersSpecs: '-'}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
         <%}%>
         <%if(!data.principalsiblingsList) { %>
            <h3>No Principal Siblings Information</h3>
         <%}else{%>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                    <h3 class="white"><b>Principal Siblings Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.principalsiblingsList.each { sl -> %>
                            <table>
                                <tr><td><b>Name :</b></td><td> ${sl.name}</td></tr>
                                <%if(!sl.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <i class="maroon">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(sl.Birthdate) { %>
                                    <tr><td><b>Birthdate :</b></td><td> <j class="green">${formatDate(sl.Birthdate)}</j></td></tr>
                                <%}%>
                                <%if(!sl.age) { %>
                                    <tr><td><b>Age :</b></td><td><b class="maroon">-</b></td></tr>
                                <%}%>
                                <%if(sl.age) { %>
                                    <tr><td><b>Age :</b></td><td> ${sl.age}</td></tr>
                                <%}%>
                                <%if(!sl.address) { %>
                                    <tr><td><b>Address :</b></td><td><i class="maroon">Not specified at present.</i></td></tr>
                                <%}%>
                                <%if(sl.address) { %>
                                    <tr><td><b>Address :</b></td><td> ${sl.address}</td></tr>
                                <%}%><hr>
                            </table>
                       <%}%>
                    </td>
                </tr>
             </table>
         <%}%>
    </body>
</html>