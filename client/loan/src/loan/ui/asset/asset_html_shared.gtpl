<% if(info.data.photos) { %>
    <hr>
    <div>
        <div><b>Attached Photos</b></div>
        <br>
        <% info.data.photos.each { item -> %>
            <div>
                <div><b><a href="preview?${item.objid}">${item.name}</a></b></div>
                <div class="desc">${item.description? item.description : 'No description'}</div>
            </div>
        <% } %>
    </div>
<% } %>

<br><br><hr>
<i><b>Comments</b></i>
<br>

<%if(info.mode!="read"){%>
    <a href="addAssetComment"><b>Add Comment</b></a>
    <br>
<%}%>

<%if(info.data?.comments.size()>0){%>
    <br>
    <%info.data.comments=info.data.comments.sort{a,b-> (b.date).toString().compareTo((a.date).toString())}%>
    <%info.data.comments.each(){o->%>
        <div>
            <font color="gray" size="2">
                <i>
                    ${o.date}
                    ${o.author}
                </i>
            </font>
        </div>
        <div>
            <pre><b>"</b><font size="3">${o.remarks}</font></pre>
        </div>
    <%}%>
<%}%>