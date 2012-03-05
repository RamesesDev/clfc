<html>
    <style>
       body{font-family:arial;font-size:8px;}
    </style>
    <body>
        <%if(data.cirecommendation.size()>0){%>
            <table cellspacing=1 cellpadding=1>
                <tr>
                    <td>
                        <font color="gray" size="2">
                            <i>posted by ${data.cirecommendation.author} @ ${data.cirecommendation.date}</i> 
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        <pre><font size="3">${data.cirecommendation.remarks}</font></pre>
                    </td>
                </tr>
            </table>
        <%}%>   
    </body>
</html>
