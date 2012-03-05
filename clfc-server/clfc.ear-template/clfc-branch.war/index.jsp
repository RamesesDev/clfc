<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>

<common:redirect-secured-session/>

<c:if test="${empty SESSIONID}">
	<t:master>

		<jsp:attribute name="head_ext">
			<link href="css/public.css" rel="stylesheet" type="text/css"/>
			<link href="js/ext/slider/css/default.css" rel="stylesheet" type="text/css" media="screen,projection" />
			<link href="js/ext/slider/css/jquery.ennui.contentslider.css" rel="stylesheet" type="text/css" media="screen,projection" />
			<script src="js/ext/login.js" type="text/javascript"></script>
			<script type="text/javascript" src="js/ext/slider/js/jquery.easing.1.3.js"></script>
			<script type="text/javascript" src="js/ext/slider/js/jquery.ennui.contentslider.js"></script>
		</jsp:attribute>
	
		<jsp:attribute name="header">
			<t:public-header showLogin="true"/>
		</jsp:attribute>
	
		<jsp:attribute name="footer">
			<t:public-footer />
		</jsp:attribute>
	
		<jsp:body>
			<table align="right" border="0" width="100%" >
				<tr>
					<td align="center" style="padding-top:50px;color:#104E8B; font-size:40px;">
						Welcome to CLFC Online
					</td>
				</tr>
			</table>
		
		</jsp:body>

	</t:master>
</c:if>

	
