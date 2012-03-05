<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags/ui-components" prefix="ui"%>
<%@ attribute name="username" rtexprvalue="true" %>
<%@ attribute name="contextmenu" fragment="true" %>

<table height="40px" cellspacing="0" cellpadding="0" width="100%" border="0">
	<tr>
		<td width="45px">
			<a href="#">
				<img src="${pageContext.servletContext.contextPath}/img/logo/32x32.png" style="border:none;"/>
			</a>
		</td>
		<td>
			CLFC <i>Loan Webportal</i>
		</td>
		<td align="right">
			<jsp:invoke fragment="contextmenu" />	
		</td>
		<td align="right" width="150px">
			<a class="secured-link" context="session" name="showProfileMenu">
				Welcome ${username} &#9660;
			</a>
		</td>
	</tr>
</table>

