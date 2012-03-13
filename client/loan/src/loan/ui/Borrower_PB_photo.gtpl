<% if(data.borrower.hasPhoto) { %>
    <hr>
    <div>
        <div><b>Attached Photos</b></div>
        <br>
        <% data.borrower.hasPhotos.each{ item -> %>
            <div>
                <!--
                <div><b><a href="preview?${item.objid}">${item.name}</a></b></div>
                <div class="desc">${item.description? item.description : 'No description'}</div>
                -->
                <div><b><a href="preview?${item.objid}">${item.name}</a></b></div>
            </div>
        <%}%>
    </div>
<%}%>