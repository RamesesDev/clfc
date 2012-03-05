<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
    $put("user", 
        new function() {
             
            var svc = ProxyService.lookup( 'UserAdminService' );
            
            var _this = this;
                        
            this.typeList = [];
			this.parentList = [];
                       
            this.entity = {};
            this.query = {};
            this.mode = "create";
            this.selectedUser;
            
            this.addNew = function() {
				var p = new PopupOpener( "user:create" );
				p.options = {width: 400, height: 400}
				return p;
			}

			this.save = function() {
				if(this.mode == "create") {
                    this.entity = svc.create(this.entity);
                }
                else {
                    svc.update(this.entity);
                }
                
                this.userListModel.refresh( true );
                this.entity = {};
                alert("Record saved");
                return "_close";
			}

			this.edit = function() {
				if( !this.selectedUser ) return;
				this.entity = svc.read( {objid: this.selectedUser.objid} );
				var p = new PopupOpener( "user:edit", {'entity': this.entity} );
				p.title = 'Edit User';
				p.options = {width: 400, height: 400};				
				return p;
			}
            
            this.remove = function() {
				if( !this.selectedUser ) return;
                if(confirm("Please confirm to remove this record.")) {
                    svc.remove({"objid" : this.selectedUser.objid});
                    this.userListModel.refresh( true );
                }
            }
            
            this.doSearch = function() {
				this.userListModel.refresh( true );            	
            }
            
            this.userListModel = {
            	rows: 15,
				fetchList: function( p ) {
					p.search = _this.query.search;
					return svc.getList(p);
				}
            }
            
            this.searchFilter = function(o) {
            	return svc.searchSuggest({'type':o});
            }
        }
    );
</script>

<t:content title="Users">
	<jsp:attribute name="actions">
		<table>
			<tr>
				<td align="left">
					<input type="text" context="user" name="query.search" suggest="searchFilter" hint="Search" size="30"/>
					<input type="button" context="user" name="doSearch" value="Go" immediate="true" />  
				</td>
				<td align="right">
					<input type="button" context="user" name="addNew" value="Create" immediate="true"/>  
				</td>
			</tr>
		</table>  
	</jsp:attribute>
	<jsp:body>
		<table class="grid" context="user" name="selectedUser" model="userListModel" varStatus="status" 
		       width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td class="list-column-first" align="left">Username</td>
					<td class="list-column" align="left">Fullname</td>   
					<td class="list-column" align="left">Email</td>
					<td class="list-column-last" width="50px">&nbsp;</td>
				</tr>                 
			</thead>
			<tbody>
				<tr class="#{status.index%2==0?'list-row-even':'list-row-odd'}">
					<td class="list-row-column-first" name="username"></td>
					<td class="list-row-column">
						#{firstname} #{middlename? middlename + ' ': ''}#{lastname}
					</td>
					<td class="list-row-column" name="email"></td>
					<td class="list-row-column-last" align="center">
						<a context="user" name="edit" title="Edit User">
							<img src="img/icon/edit.gif">
						</a>
						&nbsp;
						<a context="user" name="remove" title="Delete User">
							<img src="img/icon/delete.gif">    
						</a>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr class="control">
					<td colspan="4" align="right">
						<input type="button" context="user" name="userListModel.movePrev" value="&lt;&lt; Previous" immediate="true">
						<input type="button" context="user" name="userListModel.moveNext" value="Next &gt;&gt;" immediate="true">
					</td>
				</tr>
			</tfoot>
		</table>
		<div align="right" style="font-size:9px;">form-version-0.05</div>
		
	</jsp:body>
</t:content>
