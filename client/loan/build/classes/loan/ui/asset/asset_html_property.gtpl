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
        .red{ color:red; font-size:9px; font-weight:bold;}
        .teal{ color:teal; font-size:9px; font-weight:bold;}
        .green{ color:green; font-size:9px; font-weight:bold;}
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
                <th>Property Type:</th>
                <td>${info.data.rpu}</td>
            </tr>
            <tr>
                <th>Location:</th>
                <td>${info.data.location}</td>
            </tr>
            <tr>
                <th>Area:</th>
                <td> <b class="green">${format(info.data.area)}</b> ${info.data.uom}</td>
            </tr>
            <tr>
                <th>Registered Name:</th>
                <td>${info.data.registeredName}</td>
            </tr>
            <% if(!info.data.zonalvalue) { %>
                 <th>Zonal Value:<td class="red"><b>No zonal value specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Zonal Value:</th>
                    <td><b class="teal">Php</b> ${format(info.data.zonalvalue)}</td>
                </tr>
            <% } %>
            <tr>
                <th>Mode of Acquisition:</th>
                <td>${info.data.modeOfAcquisition}</td>
            </tr>
            <% if(!info.data.appraisedvalue) { %>
                 <th>Market/Appraised Value:<td class="red"><b>No appraised value specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Market/Appraised Value:</th>
                    <td><b class="teal">Php</b> ${format(info.data.appraisedvalue)}</td>
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
