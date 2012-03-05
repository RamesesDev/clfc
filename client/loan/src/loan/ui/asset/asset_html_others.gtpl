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
        .maroon{ color:maroon; font-size:9px; font-weight:bold; }
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
                <th>Registered Name:</th>
                <td class="maroon">${info.data.registeredName}</td>
            </tr>
            <tr>
                <th>Use:</th>
                <td class="navy">${info.data.use}</td>
            </tr>
            <tr>
                <th>Mode of Acquisition:</th>
                <td class="fuchsia">${info.data.modeOfAcquisition}</td>
            </tr>
            <% if(!info.data.dateAcquired) { %>
                 <th>Date Acquired:<td class="red"><b>No date specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Date Acquired:</th>
                    <td class="green">${formatDate(info.data.dateAcquired)}</td>
                </tr>
            <% } %>
            <% if(!info.data.appraisedvalue) { %>
                 <tr>
                    <th>Market/Appraised Value:</th>
                    <td class="red"><b>No appraised value specified.</td>
                 </tr>
            <% } else { %>    
                <tr>
                    <th>Market/Appraised Value:</th>
                    <td><b>Php</b> <b class="teal">${format(info.data.appraisedvalue)}</b></td>
                </tr>
            <%}%>
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