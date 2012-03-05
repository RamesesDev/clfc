<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"editcode",
		new function() {
			this.saveHandler;
			this.code;
			
			this.save = function() {
				this.saveHandler(this.code);
				return "_close";
			}
		}
	);
</script>

<t:popup>

	<jsp:attribute name="leftactions">
		<input type="button" context="editcode" name="save" value="Save" /><br/> 
	</jsp:attribute>
	
	<jsp:body>
		<table width="100%" cellpadding="0" cellspacing="0" height="100%">
			<tr>
				<td>
					<textarea context="editcode" name="code" style="height:100%;width:100%;font-family:courier;font-size:11px;"></textarea>
				</td>
			</tr>
		</table>
	</jsp:body>
	
</t:popup>


