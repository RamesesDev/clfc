<% if(data.hasPhoto) { %>
    <hr>
    <div>
        <div><b>Attached Photos</b></div>
        <br>
        <% data.hasPhoto.each { item -> %>
            <div>
                <div><b><a href="preview?${item.objid}">${data.hasPhoto}</a></b></div>
                <div class="desc">${data.hasPhoto? data.hasPhoto : 'No Profile Photo'}</div>
            </div>
        <% } %>
    </div>
<% } %>
