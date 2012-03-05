<html>
<style>
   body{font-family:arial;font-size:8px;}
</style>
<body>
    <%if(!data.employment){%>
        <h3>Not employed</h3>
    <% return;}%>
    <table width="100%">
        <tr><td bgcolor="gray"><h3><b>EMPLOYED</b></h3></td></tr>
        <tr>
            <td width="50%" valign="top">
                <%if(data.employment.size()>0){%>
                <table>
                    <tr><td width=100>Employer :</td><td> ${data.employment.employer}</td></tr>
                    <tr><td>Tel. No. :</td><td> ${data.employment.telno}</td></tr>
                    <tr><td>Address :</td><td> ${data.employment.address}</td></tr>
                    <tr><td>Position :</td><td> ${data.employment.position}</td></tr>
                    <tr><td>Montly Salary :</td><td> ${data.employment.salary}</td></tr>
                    <tr><td>Length of service :</td><td> ${data.employment.years}</td></tr>
                    <tr><td>Status :</td><td> ${data.employment.status}</td></tr>
                </table>
                <%}%>
            </td>  
        </tr>
   </table> 
</body>
</html>