<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" %>
<%@ attribute name="actions" fragment="true" %>
<%@ attribute name="toolbar" fragment="true" %>

<table width="100%">
	<tr>
		<td align="left" style="font-size:20px; color:darkslateblue;">
			<b>${title}</b>
		</td>
		<td align="right">
			<jsp:invoke fragment="actions"/>
		</td>
	</tr>
	<c:if test="${not empty toolbar}">
		<tr>
			<td colspan="2">
				<div class="toolbar">
					<jsp:invoke fragment="toolbar"/>
				</div>
			</td>
		</tr>
	</c:if>
	<tr>
		<td align="left" colspan="2" style="${empty toolbar? 'border-top:1px solid lightgrey; padding-top:10px;' : ''}">
			<jsp:doBody/>
		</td>
	</tr>
</table>
