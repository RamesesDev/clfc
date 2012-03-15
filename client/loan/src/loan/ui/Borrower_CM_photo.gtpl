<% if(data.coMakerList.hasPhoto) { %>
    <hr>
    <div>
        <div><b>Attached Photos</b></div>
        <br>
        <% data.coMakerList.hasPhotos.each{ item -> %>
            <div>
                <div><b><a href="preview?${item.objid}">${item.name}</a></b></div>
            </div>
        <%}%>
    </div>
<%}%>