<%
    def dec_formatter = new java.text.DecimalFormat('#,##0.00');

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
       .red { color: red; font-size: 9px;}
       .maroon { color: maroon; font-size: 9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}
       .info, .grid { margin-left: 10px; }
       .source {
            pre {padding-left: 9px;}
       }
    </style>
    <body>
        <% data.jointBorrowerList.each { jb -> %>
            <%if(jb.fathersName){%>
                <table width="100%">
                    <tr>
                        <td bgcolor="gray">
                            <h3 class="white"><b>Joint Borrower Parents Information</b></h3>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" valign="top">
                            <table>
                                <tr>
                                    <th>Father's Name :</th>
                                    <td>${jb.fathersName? jb.fathersName: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Birthdate :</th>
                                    <td>${jb.fathersBDate? jb.fathersBDate: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Age :</th>
                                    <td>${jb.fathersAge? jb.fathersAge: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Mother's Name :</th>
                                    <td>${jb.mothersName? jb.mothersName: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Birthdate :</th>
                                    <td>${jb.mothersBDate? jb.mothersBDate: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Age :</th>
                                    <td>${jb.mothersAge? jb.mothersAge: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Address :</th>
                                    <td>${jb.parentsAddress? jb.parentsAddress: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Remarks :</th>
                                    <td>${jb.othersSpecs? jb.othersSpecs: '-'}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            <%}%>
        <%}%>
        <% if(!data.jointBorrowerList.siblingsList) { %>
            <h3>No siblings specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                    <h3 class="white"><b>Joint Borrower Siblings Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.siblingsList.each { sl -> %>
                                <table>
                                    <tr><td><b>Name :</b></td><td> ${sl.name? sl.name: '-'}</td></tr>
                                    <tr><td><b>Birthdate :</b></td><td> ${sl.Birthdate? sl.Birthdate: '-'}</td></tr>
                                    <tr><td><b>Age :</b></td><td> ${sl.age}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${sl.address}</td></tr>
                                </table>
                                <%if(!jb.siblingsList){%>
                                    
                                <%}%>
                                <%if(jb.siblingsList.size() > 0){%>
                                    <hr>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
           </table> 
       <%}%>
       <% data.jointBorrowerList.each { jb -> %>
            <%if(jb.spouseFathersName){%>
                <table width="100%">
                    <tr>
                        <td bgcolor="gray">
                            <h3 class="white"><b>Joint Borrower's Spouse Parents Information</b></h3>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" valign="top">
                            <table>
                                <tr>
                                    <th>Father's Name :</th>
                                    <td>${jb.spouseFathersName? jb.spouseFathersName: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Birthdate :</th>
                                    <td>${jb.spouseFathersBDate? jb.spouseFathersBDate: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Age :</th>
                                    <td>${jb.spouseFathersAge? jb.spouseFathersAge: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Mother's Name :</th>
                                    <td>${jb.spouseMothersName? jb.spouseMothersName: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Birthdate :</th>
                                    <td>${jb.spouseMothersBDate? jb.spouseMothersBDate: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Age :</th>
                                    <td>${jb.spouseMothersAge? jb.spouseMothersAge: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Address :</th>
                                    <td>${jb.spouseAddress? jb.spouseAddress: '-'}</td>
                                </tr>
                                <tr>
                                    <th>Remarks :</th>
                                    <td>${jb.spouseOthersSpecs? jb.spouseOthersSpecs: '-'}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            <%}%>
        <%}%>
        <% if(!data.jointBorrowerList.spouseSiblingsList) { %>
            <h3>No spouse siblings specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                    <h3 class="white"><b>Joint Borrower Spouse Siblings Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.spouseSiblingsList.each { ssl -> %>
                                <table>
                                    <tr><td><b>Name :</b></td><td> ${ssl.name? ssl.name: '-'}</td></tr>
                                    <tr><td><b>Birthdate :</b></td><td> ${ssl.Birthdate? ssl.Birthdate: '-'}</td></tr>
                                    <tr><td><b>Age :</b></td><td> ${ssl.age}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${ssl.address}</td></tr>
                                </table>
                                <%if(!jb.spouseSiblingsList){%>

                                <%}%>
                                <%if(jb.spouseSiblingsList.size() > 0){%>
                                    <hr>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>
                </tr>
           </table>
        <%}%>
    </body>
</html>