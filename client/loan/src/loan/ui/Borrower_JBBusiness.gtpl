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

       .info{ color: green; font-size: 8px; font-weight:bold;}
       .inv{ color: red; font-size:9px; font-weight:bold;}
       .inv2{ color: blue; font-size:9px;}
       .cash{ color: gray; font-size:9px;}
       .teal { color: teal; font-size: 9px; font-weight:bold;}

       .listInfo { color: black; font-size: 9px; }
       .info2 { color: black; font-size: 9px; }
    </style>
    <body>
        <% if(!data.jointBorrowerList) { %>
            <h3>No business specified for this application</h3>
        <% } else { %>
            <table width="100%">
                <tr>
                    <td bgcolor="gray">
                        <h3 class="white"><b>Business Information</b></h3>
                    </td>
                </tr>
                <tr>
                    <td width ="50%" valign="top">
                        <% data.jointBorrowerList.each { jb -> %>
                            <% jb.mainBusinessList.each { business -> %>
                                <div align="center"><h2>${business.tradename}</h2></div>
                                <table>
                                    <tr><td><b>Tel. No. :</b></td><td> ${business.telno}</td></tr>
                                    <tr><td><b>Address :</b></td><td> ${business.address}</td></tr>
                                    <tr>
                                        <td valign="top"><b>Occupancy</b></td>
                                        <tr>
                                            <%if ( business.occupancy.type=="OWNED" ) {%>
                                                <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.occupancy.type? business.occupancy.type: '-'}</b><br>
                                                    <b class="info">Description/Remarks :</b> <b class="info2">${business.occupancy.remarks? business.occupancy.remarks: '-'}</b><br>
                                                    <b class="info">Since :</b> <b class="info2">${business.occupancy.since? business.occupancy.since: '-'}</b>
                                                </td>
                                            <%}%>
                                            <%if ( business.occupancy.type=="RENTED" ) {%>
                                                <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.occupancy.type? business.occupancy.type: '-'}</b><br>
                                                    <b class="info">Rent Type :</b> <b class="info2">${business.occupancy.renttype? business.occupancy.renttype: '-'}</b><br>
                                                    <b class="info">Rental Fee :</b> <b>Php</b> <b class="info2">${business.occupancy.rentamount? business.occupancy.rentamount: '-'}</b><br>
                                                    <b class="info">Description/Remarks :</b> <b class="info2">${business.occupancy.remarks? business.occupancy.remarks: '-'}</b><br>
                                                    <b class="info">Since :</b> <b class="info2">${business.occupancy.since? business.occupancy.since: '-'}</b>
                                                </td>
                                            <%}%>
                                        </tr>
                                    </tr>
                                    <tr><td><b>Ownership :</b></td><td> ${business.ownership}</td></tr>
                                    <%if ( business.businesstype.type ) {%>
                                        <tr>
                                            <td valign="top"><b>Type of Business</b></td>
                                            <tr>
                                                <%if ( business.businesstype.type=="CARENDERIA" ) {%>
                                                    <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.businesstype.type? business.businesstype.type: '-'}</b><br>
                                                        <b class="info">Stall Size/P.O Size :</b> <b class="info2">${business.businesstype.stallsize? business.businesstype.stallsize: '-'}</b><br>
                                                        <b class="info">No. of Tables :</b> <b class="info2">${business.businesstype.nooftables? business.businesstype.nooftables: '-'}</b><br>
                                                        <b class="info">No. of Chair :</b> <b class="info2">${business.businesstype.noofchair? business.businesstype.noofchair: '-'}</b><br>
                                                        <b class="info">No. of CounterType :</b> <b class="info2">${business.businesstype.noofcountertype? business.businesstype.noofcountertype: '-'}</b><br>
                                                        <b class="info">Kind/Size :</b> <b class="info2">${business.businesstype.kind? business.businesstype.kind: '-'}</b><br>
                                                    </td>
                                                <%}%>
                                                <%if ( business.businesstype.type=="SARI-SARI" ) {%>
                                                    <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.businesstype.type? business.businesstype.type: '-'}</b><br>
                                                        <b class="info">Stall Size/P.O Size :</b> <b class="info2">${business.businesstype.stallsize? business.businesstype.stallsize: '-'}</b>
                                                    </td>
                                                <%}%>
                                                <%if ( business.businesstype.type=="OPERATOR" ) {%>
                                                    <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.businesstype.type? business.businesstype.type: '-'}</b><br>
                                                        <b class="info">No. of Units :</b> <b class="info2">${business.businesstype.noofunits? business.businesstype.noofunits:  '-'}</b><br>
                                                        <b class="info">Seating Cap. :</b> <b class="info2">${business.businesstype.seatingcap? business.businesstype.seatingcap: '-'}</b><br>
                                                        <b class="info">Boundary Fee :</b> <b>Php</b> <b class="info2">${business.businesstype.boundaryfee? business.businesstype.boundaryfee: '-'}</b><br>
                                                    </td>
                                                <%}%>
                                                <%if ( business.businesstype.type=="OTHERS" ) {%>
                                                    <td valign="top"><td><b class="info">Type :</b> <b class="info2">${business.businesstype.type? business.businesstype.type: '-'}</b><br>
                                                        <b class="info">Remarks :</b> <b class="info2">${business.businesstype.remarks? business.businesstype.remarks: '-'}</b>
                                                    </td>
                                                <%}%>
                                            </tr>
                                        </tr>
                                    <%}%>
                                    <tr><td><b>Capital invested :</b></td><td><b>Php</b> <b class="inv">${business.invested}</b></td></tr>
                                    <tr><td><b>Established since :</b></td><td> <b class="inv2">${formatDate(business.established)}</b></td></tr>
                                    <!--<tr><td><b>Status of business :</b></td><td> ${business.status}</td></tr>-->
                                    <!--<tr><td><b>Stall size :</b></td><td> ${business.stallsize}</td></tr>-->
                                    <tr><td><b>Business Hours :</b> </td>
                                        <td>
                                            ${business.businessHoursFrom? business.businessHoursFrom: '-'} 
                                            - ${business.businessHoursTo? business.businessHoursTo: '-'}
                                        </td>
                                    </tr>        
                                    <tr><td><b>Days Open :</b></td><td> ${business.daysOpen}</td></tr>
                                    <tr>
                                        <td><b>Est. daily sales :</b></td><td>
                                            <b>Php</b> <b class="teal">${business.avgsales_from? business.avgsales_from: '-'}
                                            - <b>Php</b> ${business.avgsales_to? business.avgsales_to: '-'}</b>
                                        </td>
                                    </tr>
                                    </br>
                                    <tr><td><b>Others Specification :</b></td><td> 
                                            ${business.others? business.others: 'Not specified.' }
                                        </td>
                                    </tr>
                                </table>
                                <%if ( business.cicashCountDate || business.cicashCountTime ) {%>
                                    <table>
                                        <tr>
                                            <th align="center">CI Report</b></th>
                                            <tr>
                                                <td valign="top"><td><b class="info">Business Evaluation:</b> <b class="listInfo">${business.businessEvaluation? business.businessEvaluation: '-'}</b><br>
                                                    <b class="info">Physical Outlook:</b> <b class="listInfo">${business.ciPhysicalOutlook? business.ciPhysicalOutlook: '-'}</b>
                                                </td>  
                                            </tr>    
                                            <th valign="top">Cash Count</b></th>
                                                <tr>
                                                    <td valign="top"><td><b class="info">Date :</b> <b class="listInfo">${formatDate(business.cicashCountDate)? formatDate(business.cicashCountDate): '-'}</b><br>
                                                        <b class="info">Time :</b> <b class="listInfo">${business.cicashCountTime? business.cicashCountTime: '-'}</b><br>    
                                                        <b class="info">Amount :</b> <b class="listInfo">${format(business.cicashCountAmount)? 'Php' +' '+ format(business.cicashCountAmount): '-'}</b>
                                                    </td>    
                                                </tr>
                                                <tr>
                                                    <td valign="top"><td><b class="info">CI Date :</b> <b class="listInfo">${formatDate(business.ciDate)? formatDate(business.ciDate): '-'}</b><br>
                                                        <b class="info">CI By :</b> <b class="listInfo">${business.ciBy? business.ciBy: '-'}</b><br>    
                                                    </td>
                                                </tr>
                                            </tr>
                                        </tr>    
                                    </table>
                                <%}%>
                                <hr>
                                <table>
                                    <hr>
                                    <tr>
                                        <td valign="top"><b>Photo(s) :</b></td>
                                        <tr>
                                            <td valign="top"><td><b class="inv">No Photo(s) Attached</td>
                                        </tr>
                                    </tr>
                                    <hr>
                                </table>
                                <%if(jb.mainBusinessList.size() >0 ) {%>
                                    <hr>
                                <%}%>
                            <%}%>
                        <%}%>
                    </td>    
                </tr>
             </table>
         <% } %>
    </body>
</html>