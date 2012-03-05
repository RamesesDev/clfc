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
        body{ font-family:arial;font-size:9px; }
        th { text-align: left; padding-right: 5px; }

        .desc { 
            font-size: 8px; color: gray; 
            font-style: italic;
        }
        
        .listInfo { color: green; font-size: 9px; }
        .info { color: black; font-size: 9px; }
        .red{ color:red; font-size:9px; font-weight:bold; }
        .green{ color:green; font-size:9px; font-weight:bold; }
        .fuchsia{ color:fuchsia; font-size:9px; font-weight:bold; }
        .teal{ color:teal; font-size:9px; font-weight:bold; }
        .navy{ color:navy; font-size:9px; font-weight:bold; }
    </style>
    <body>
        <%if(info.mode!="read"){%>
            <a href="editAsset"><b>Edit</b></a>
            <br><br>
        <%}%>
        <table>
            <% if(info.data.subject) { %>
                <tr>
                    <th>Subject:</th>
                    <td>${info.data.subject}</td>
                </tr>
            <% } %>
            <tr>
                <th>Make:</th>
                <td>${info.data.make}</td>
            </tr>
            <tr>
                <th>Model:</th>
                <td>${info.data.model}</td>
            </tr>
            <tr>
                <th>Type:</th>
                <td>${info.data.type}</td>
            </tr>
            <tr>
                <th>Use:</th>
                <td>${info.data.use}</td>
            </tr>
            <% if(!info.data.dateAcquired) { %>
                 <th>Date Acquired:<td class="red"><b>No date specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Date Acquired:</th>
                    <td class="green">${formatDate(info.data.dateAcquired)}</td>
                </tr>
            <% } %>
            <tr>
                <th>Registered Name:</th>
                <td class="navy">${info.data.registeredName}</td>
            </tr>
            <tr>
                <th>Registration no.:</th>
                <td>${info.data.regno}</td>
            </tr>
            <tr>
                <th>Chassis no.:</th>
                <td>${info.data.chassisno}</td>
            </tr>
            <tr>
                <th>Plate no.:</th>
                <td class="Fuchsia"><b>${info.data.plateno}</td>
            </tr>
            <tr>
                <th>Engine no.:</th>
                <td>${info.data.engineno}</td>
            </tr>
            <tr><th><b>OR/CR</b></th>
                <tr>
                    <td valign="top"><td>
                        <b class="listInfo">CR No. :</b> <b class="info">${info.data.crno? info.data.crno: '-'}</b><br>
                        <b class="listInfo">Fuel :</b> <b class="info">${info.data.fuel? info.data.fuel: '-'}</b><br>
                        <b class="listInfo">Denomination :</b> <b class="info">${info.data.denomination? info.data.denomination: '-'}</b><br>
                        <b class="listInfo">Series :</b> <b class="info">${info.data.series? info.data.series: '-'}</b><br>
                        <b class="listInfo">Piston Displacement :</b> <b class="info">${info.data.pistondisplacement? info.data.pistondisplacement: '-'}</b><br>
                        <b class="listInfo">No. of Cylinders :</b> <b class="info">${info.data.noofcylinders? info.data.noofcylinders: '-'}</b><br>
                        <b class="listInfo">Net Wt.:</b> <b class="info">${info.data.netwt?info.data.netwt: '-'}</b><br>
                        <b class="listInfo">Gross Wt.:</b> <b class="info">${info.data.grosswt? info.data.grosswt: '-'}</b><br>
                        <b class="listInfo">Net Cap.:</b> <b class="info">${info.data.netcap? info.data.netcap: '-'}</b><br>
                        <b class="listInfo">Ship Wt.:</b> <b class="info">${info.data.shipwt? info.data.shipwt: '-'}</b><br>
                    </td>
                </tr>
            </tr><br>
            <% if(!info.data.appraisedvalue) { %>
                 <th>Market/Appraised Value:<td class="red"><b>No appraised value specified.</td></th>
            <% } else { %>    
                <tr>
                    <th>Market/Appraised Value:</th>
                    <td><b>Php</b> <b class="teal">${format(info.data.appraisedvalue)}</b></td>
                </tr>
            <% } %>
            <tr>
                <th valign="top">Remarks:</th>
                <td>${info.data.remarks}</td>
            </tr>
        </table>
        <br><br>
        
        <%

        import com.rameses.util.*;

        String tpl = "loan/ui/asset/asset_html_shared.gtpl";
        println TemplateProvider.instance.getResult( tpl, [info:info] );

        %>

        
    </body>
</html>
