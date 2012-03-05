<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"script",
		new function() {
			var svc = ProxyService.lookup("ScriptMgmtService");
			this.entity;
			this.saveHandler;
			this.name;
			this._controller;
			var self = this;
			
			this.onload = function() {
				this.entity = svc.read( {name: this.name} );
			}
			
			this.editCode = function() {
				var saveHandler = function(o) {
					self.entity.content = o;
					svc.update( self.entity ); 
					self._controller.refresh();
				}
				return new PopupOpener("scripting:editcode", {code: this.entity.content, saveHandler: saveHandler} );
			}
			
			this.redeploy = function() {
				svc.redeploy( this.entity.name );
			};
			
		}
	);
</script>

<a context="script" name="_close" style="font-size:10px;">Back</a>

<t:content title="Script">
	<jsp:attribute name="actions">
		<input type="button" context="script" name="redeploy" value="Redeploy" />
	</jsp:attribute>
	<jsp:body>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="120">Script Name</td>
				<td><label context="script">#{entity.name}</label></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><label context="script">#{entity.category!=null ? entity.category: ''}</label></td>
			</tr>	
			<tr>
				<td>Description</td>
				<td><label context="script">#{entity.notes!=null ? entity.notes : ''}</label></td>
			</tr>	
			<tr>
				<td colspan="2">Code [<a context="script" name="editCode">Edit</a>]</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea context="script" name="entity.content" disabled="disabled" style="height:400px;width:100%;"></textarea>
				</td>
			</tr>
			
	</jsp:body>
	
</t:content>
