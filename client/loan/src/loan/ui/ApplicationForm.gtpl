<html>
    <style>
       body{font-family:arial;font-size:8px;}
    </style>
    <body>
        <table cellspacing=1 cellpadding=1>
            <tr><td><b>Last Name:</b></td><td>${data.borrower.lastname}</td></tr>
            <tr><td><b>First Name:</b></td><td>${data.borrower.firstname}</td></tr>
            <tr><td><b>Birth Date:</b></td><td>${data.borrower?.bdate}</td></tr>
            <tr><td><b>Address:</b></td><td>${data.borrower?.address}</td></tr>
        </table>
        <br><br>

        <%if(!data.spouse){%>
            <pre> <b><a href="addConnection">Add Social Connection</a></b></pre>
        <%if(data.spouse){%>
            <pre> <b><a href="addConnection">Edit Social Connection</a></b></pre>
    </body>
</html>