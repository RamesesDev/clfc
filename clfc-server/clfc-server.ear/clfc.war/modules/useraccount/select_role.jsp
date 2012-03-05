<script>
	$put(
		"select_role",
		new function() {
			
			this.roles;
			this.selected;
			
			this.changeRole = function() {
				if ( this.selected && this.selected.objid ) {
					WindowUtil.reload({roleid: this.selected.objid });
				}
				else {
					WindowUtil.reload();
				}	
			}
		}
	);	
</script>

<table context="select_role" items="roles" name="selected"
       cellpadding="0" cellspacing="0" style="font-size:12px;">
	<tbody>
		<tr>
			<td>
				<a href="#" onclick="$ctx('select_role').changeRole();">
					<nobr>#{groupname}</nobr>
				</a>
			</td> 
		</tr>
	</tbody>
</table>
