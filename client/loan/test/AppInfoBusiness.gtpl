<html>
<style>
   body{font-family:arial;font-size:8px;}
</style>
<body>
    <%if(data.business.size()<=1){%>
        <h3>No business specified for this application</h3>
    <% return;}%>
    <table width="100%">
        <tr><td bgcolor="gray"><h3><%if(data.business.size()>1){%><b>IN BUSINESS</b><%}%></h3></td></tr>
        <tr>
            <td width ="50%" valign="top">
                <%if(data.business.size()>1){%>
                <table>
                    <tr><td width=100>Firm / Trade name :</td><td> ${data.business.tradename}</td></tr>
                    <tr><td>Tel. No. :</td><td> ${data.business.telno}</td></tr>
                    <tr><td>Address :</td><td> ${data.business.address}</td></tr>
                    <tr><td>Ownership :</td><td> ${data.business.ownership}</td></tr>
                    <tr><td>Type of business :</td><td> ${data.business.businesstype}</td></tr>
                    <tr><td>Capital invested :</td><td> ${data.business.invested}</td></tr>
                    <tr><td>Established since :</td><td> ${data.business.established}</td></tr>
                    <tr><td>Status of business :</td><td> ${data.business.status}</td></tr>
                    <tr><td>Stall size :</td><td> ${data.business.stallsize}</td></tr>
                    <tr><td>Est. daily sales :</td><td> form : ${data.business.avgsale.from} to : ${data.business.avgsale.to}</td></tr>
                </table>
                <%}%>
            </td>
        </tr>
     </table>
</body>
</html>