<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"script_list",

		new function() {
			var svc = ProxyService.lookup("ScriptMgmtService");
			this.list = [];
			this.query = {}
			this.state;
			var self = this;
			
			this.listModel = {
				rows: 10,
				fetchList : function(o) {
					return svc.getList( o );
				}
			}

			this.open = function() {
				var e = this.listModel.getSelectedItem();
				if(e) return new DocOpener( "scripting:script", {name: e.name} );
			}
			
			this.create = function() {
				var saveNew = function(o) {
					svc.create(o);
					self.listModel.load();	
				}
				return new PopupOpener( "scripting:script_new", {saveHandler: saveNew} ); 
			}
			
			this.redeployAll = function() {
				if(confirm("This will activate scripts on this server. Proceed?")) {
					svc.redeployAll();	
				}
			}
			
			this.remove = function() {
				if( this.listModel.getSelectedItem() ) {
					if(confirm("You are about to remove this script. Continue?") ) {
						svc.remove( this.listModel.getSelectedItem() );
						self.listModel.load();	
					}
				}
			}
			
		}
	);
</script>

<t:content title="Scripts">

	<jsp:attribute name="actions">
		<input type="button" value="Add New" context="script_list" name="create" />
		<input type="button" value="Redeploy All" context="script_list" name="redeployAll" />
	</jsp:attribute>
	
	<jsp:body>
		<table class="grid" context="script_list" model="listModel" varName="item" varStatus="status" width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td align="left">Script Name</td>
					<td align="left">Category</td>
					<td align="center">Description</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="left">#{item.name}</td>
					<td align="left">#{item.category!=null? item.category: ''}</td>
					<td align="left">#{item.notes!=null? item.notes : ''}</td>
					<td align="center">
						<a context="script_list" name="open">Open</a>
					</td>
					<td align="left">
						<a context="script_list" name="remove">Remove</a>
					</td>
				</tr>                 
			</tbody>
			<tfoot>
				<tr class="control">
					<td colspan="5" align="right">
						<input type="button" context="script_list" name="listModel.moveFirst" value="&lt;&lt;" />
						<input type="button" context="script_list" name="listModel.movePrev" value="&lt;" />
						<input type="button" context="script_list" name="listModel.moveNext" value="&gt;" />
					</td>
				</tr>
			</tfoot>
		</table>
	</jsp:body>
</t:content>

