<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/ui-components" prefix="ui" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>


<c:set var="roleid" value="${param['roleid']}"/>
<script>
	var ___ROLES = [];
	
	<c:forEach items="${USER.roles}" var="item">
		<c:if test="${item.objid == roleid }">
			<c:set var="selectedRole" value="${item}" scope="page" />
		</c:if>
		<c:if test="${item.objid != roleid}">
			___ROLES.push({objid: '${item.objid}', groupname: '${item.groupname}'});
		</c:if>
	</c:forEach>
	
	$put("roleManager",
		new function() {
						
			this.lookupRole = function() {
				return new DropdownOpener('useraccount:select_role_menu', {roles: ___ROLES});
			}
			this.changeRole = function(o) {
				if (o == null ) {
					WindowUtil.reload();					
				}
				else {
					WindowUtil.reload({roleid: o });
				}
			}
		}
	);	
</script>

<c:if test="${!empty USER.roles}">
	<c:if test="${! empty pageScope.selectedRole}">
		<span class="user-role">
			User Group: <b>${pageScope.selectedRole.groupname}</b>
		</span>
		<c:if test="${fn:length(USER.roles) gt 1}">
			<input class="button3" type="button" value="Change Group" context="roleManager" name="lookupRole" />
		</c:if>
	</c:if>
	<c:if test="${empty pageScope.selectedRole}">
		<input class="button3" type="button" value="Select User Group" context="roleManager" name="lookupRole" />
	</c:if>
</c:if>
