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
