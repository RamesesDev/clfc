<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"task_error",
		new function() {
			this.error;
		}
	);
</script>

<t:popup>
	<jsp:attribute name="leftactions">
		<input type="button" context="task_error" name="_close" value="Close"/>
	</jsp:attribute>
	<jsp:body>
		<textarea context="task_error" name="error" style="width:100%;height:100%;font-size:11px;"></textarea>
	</jsp:body>
</t:popup>