<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ tag import="com.rameses.web.support.*" %>
<%@ tag import="java.util.*" %>

<%@ attribute name="target" rtexprvalue="true"%>

<style>
	.usermenu td { 
		cursor: pointer; font-size: 12px; font-family:arial; padding:5px;
		-moz-border-radius-bottomleft: 4px; -moz-border-radius-topleft: 4px; 
		-webkit-border-bottom-left-radius: 4px; -webkit-border-top-left-radius: 4px;
	}
	.usermenu tr.category td { 
		cursor: default; padding-left: 0px;
		font-weight: bold;
		border-bottom: solid 1px gray;
		-mox-border-radius: 0px; -webkit-border-radius: 0px;
	}
	.usermenu tr.selected td { 
		background: -moz-linear-gradient(top,  #336699,  #264d73);
		background: -webkit-gradient(linear, left top, left bottom, from(#336699), to(#264d73));
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#336699', endColorstr='#264d73');
		color: #fff; font-weight: bold;
	}
	.usermenu td.hover { 
		background: -moz-linear-gradient(top,  #e2e2e2,  #ccc);
		background: -webkit-gradient(linear, left top, left bottom, from(#e2e2e2), to(#ccc));
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e2e2e2', endColorstr='#cccccc');
	}
</style>

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
