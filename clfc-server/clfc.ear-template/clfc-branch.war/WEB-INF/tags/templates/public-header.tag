<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="showLogin" rtexprvalue="true" %>


<script type="text/javascript">
	$(function(){
		var usr = $('input[name="data.username"]').keydown(focusNext).focus();
		var pwd = $('input[name="data.password"]').keydown(focusNext);
		var btn = $('input[name="login"]');
		
		function focusNext(e) {
			if( e.keyCode != 13 ) return;
			
			if( this == usr[0] ) {
				if( pwd.val() ) {
					usr.trigger('change');
					btn.click();
				}
				else {
					pwd.focus().select();
				}
			}
			else if( this == pwd[0] ) {
				if( usr.val() ) {
					pwd.trigger('change');
					btn.click();
				}
				else {
					usr.focus().select();
				}
			}
		}
	});
</script>

<table width="100%" style="height:80px;" style="color: #fff">
	<tr>
		<td width="70px">
			<a href="#">
				<img src="${pageContext.servletContext.contextPath}/img/logo/64x64.png" style="border:none;"/>
			</a>
		</td>
		<c:if test="${showLogin == 'true' }">
			<td align="right" valign="top">
				<table style="color:white;font-size:10px;" cellpadding="0" cellspacing="1">
					<tr>
						<td><input type="text" size="15" context="login" name="data.username" hint="Email"></td>
						<td><input type="password" size="15" context="login" name="data.password" hint="Password"></td>
						<td><input type="button" value="Login" context="login" name="login" style="border:none;"></td>
					</tr>
				</table>
			</td>
		</c:if>	
	</tr>
</table>

