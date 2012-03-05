<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put("role_list", new function() {

			this.mode = "create"; 
			this.query = {}; //for search
		  	var svc = ProxyService.lookup("RoleAdminService");
		  	
		  	this.addNew=function() {
		  		//return new PopupOpener( "modules/role/role_form.html" );			    	  					
		  	}
                        
            this.remove = function(objid) {
				var name = svc.getName({objid:objid});
				if(confirm("Are you sure you want to delete this [" + name + "] role?")) {
                    svc.delete({objid:objid});
					alert("[" + name + "] role has been deleted");
					this.listModel.refresh(true);
                }				
            }
			
			this.listModel = {
				rows:15,
				fetchList:function(p){return svc.getList(p);}
			}		
		}
	);
</script>

<t:content title="Roles">
	<jsp:attribute name="actions">
		<input type="button" context="role_list" name="addNew" value="Create Role" immediate="true">
	</jsp:attribute>
   
   	<jsp:body>
		<table context="role_list" model="listModel" varStatus="status" width="100%" class="grid" cellspacing="0">
			<thead>
				<tr>
					<th class="list-column-first" width="40%">Name</th>
					<th class="list-column">Master Role</th>			
					<th class="list-column-last" width="50px">&nbsp;</th>
				</tr>                
			</thead>
			<tbody>
				<tr class="#{status.index%2==0?'even':'odd'}">
					<td name="name" width="50%"></td>
					<td name="masterrole"></td>
					<td align="center">
						<a context="role_list" title="Edit Role">
							<img src="img/icon/edit.gif">
						</a>
						&nbsp;
						<a context="role_list" title="Delete Role">
							<img src="img/icon/delete.gif">
						</a>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr class="control">
					<td colspan="3" align="right">
						<input type="button" value="<<First" context="role_list" name="listModel.moveFirst"/>
						<input type="button" value="<Prev" context="role_list" name="listModel.movePrev"/>
						<input type="button" value="Next>" context="role_list" name="listModel.moveNext"/>
					</td>
				</tr>
			</tfoot>
		</table>
	</jsp:body>
</t:content>
    
