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
        .green{ color:green; font-size:9px; font-weight:bold;}
        .teal{ color:teal; font-size:9px; font-weight:bold;}
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
                <th>Type:</th>
                <td>${info.data.type}</td>
            </tr>
            <tr>
                <th>Brand:</th>
                <td>${info.data.brand}</td>
            </tr>
            <% if(!info.data.dateAcquired) { %>
                 <th>Date Acquired:<td class="red">No date specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Date Acquired:</th>
                    <td class="green">${formatDate(info.data.dateAcquired)}</td>
                </tr>
            <% } %>    
            <tr>
                <th>Serial:</th>
                <td>${info.data.serial}</td>
            </tr>
            <tr>
                <th>Year Model:</th>
                <td>${info.data.model}</td>
            </tr>
            <% if(!info.data.appraisedvalue) { %>
                 <th>Market/Appraised Value:<td class="red">No appraised value specified.</td></th>   
            <% } else { %>    
                <tr>
                    <th>Market/Appraised Value:</th>
                    <td class="teal"><b>Php</b> ${format(info.data.appraisedvalue)}</td>
                </tr>
            <% } %>
            <tr>
                <th valign="top">Remarks:</th>
                <td valign="top">${info.data.remarks}</td>
            </tr>
        </table>        
        <br>
        
        <%

        import com.rameses.util.*;

        String tpl = "loan/ui/asset/asset_html_shared.gtpl";
        println TemplateProvider.instance.getResult( tpl, [info:info] );

        %>

    </body>
</html>
