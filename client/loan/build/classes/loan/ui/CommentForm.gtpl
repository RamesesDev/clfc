<html>
    <style>
       body{font-family:arial;font-size:8px;}
    </style>
    <body>
        <%if(data.comments.size>0){%>
            <%data.comments.each(){o->%>
                <table cellspacing=1 cellpadding=1>
                    <tr>
                        <td>
                            <font color="gray" size="2">
                                <i>posted by ${o.author} @ ${o.date}</i> 
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <pre><font size="3">${o.remarks}</font></pre>
                        </td>
                    </tr>
                </table>
            <%}%>
        <%}%>
    </body>
</html>
