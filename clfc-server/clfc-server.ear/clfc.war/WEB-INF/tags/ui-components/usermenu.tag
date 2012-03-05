<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ tag import="com.rameses.web.support.*" %>
<%@ tag import="java.util.*" %>

<%@ attribute name="target" rtexprvalue="true"%>

<script type="text/javascript">

	$put("module_manager", 
		new function() {
			var self = this;
			this.bookmark = new Bookmarker("${target}");
			this.selected;
			
			this.onload = function() {
				this.bookmark.updateHandler = function( inv ) {
					if( inv.type == 'usermenu' ) {
						self.selected = inv;
					}
					else if ( inv.parent ) {
						self.selected = InvokerUtil.find( inv );
					}
					
					if( self.menuModel.refresh )
						self.menuModel.refresh(); 
				}
				this.bookmark.load( 'usermenu' );
			}
			
			this.menuModel = {
				fetchList: function(o) {
					return InvokerUtil.lookup( "usermenu" );
				}
			}
			
			this.navigate = function() {
				this.bookmark.invokeSelected( this.selected );
			}

		}	
	);
	
	$(function(){
		$('a[href="#"]').click(function(e){ e.preventDefault(); });
	});
	
</script>


<table context="module_manager" name="selected" model="menuModel" varName="item" visibleWhen="#{menuModel}" varStatus="stat"
       cellpadding="0" cellspacing="0" style="display: none; padding-top:5px;"
       width="100%" class="usermenu" >
	<tbody>
		<tr class="category" visibleWhen="#{item.category && item.category != stat.prevItem.category}">
			<td selectable="false" >#{item.category}</td>
		</tr>
		<tr onclick="$ctx('module_manager').navigate();">
			<td>#{item.caption}</td>
		</tr>
	</tbody>
</table>
