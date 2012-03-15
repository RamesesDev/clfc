<html>
    <style>
       body{font-family:arial;font-size:8px;}
    </style>
    <body>
        <%if(data.crecomrecommendation.size()>0){%>
            <table cellspacing=1 cellpadding=1>
                <tr>
                    <td>
                        <font color="gray" size="2">
                            <i>posted by ${data.crecomrecommendation.author} @ ${data.crecomrecommendation.date}</i> 
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        <pre><font size="3">${data.crecomrecommendation.remarks}</font></pre>
                    </td>
                </tr>
            </table>
        <%}%>   
    </body>
</html>
