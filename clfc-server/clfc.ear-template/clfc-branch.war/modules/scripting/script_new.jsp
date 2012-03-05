<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"script_new",
		new function() {
			this.saveHandler;
			this.entity = {};
			
			this.types= ["interceptor"];
			
			this.save = function() {
				this.saveHandler(this.entity);
				return "_close";
			}
		}
	);
</script>

<t:popup>

	<jsp:attribute name="leftactions">
		<input type="button" context="script_new" name="save" value="Save" /><br/> 
	</jsp:attribute>
	
	<jsp:body>
		Script Name<br>
		<input type="text" context="script_new" name="entity.name" size="50" style="width:350px;"/><br/> 
		Category<br>
		<select context="script_new" name="entity.category" items="types" allowNull="true"></select><br>
		Description<br>
		<textarea context="script_new" name="entity.notes"  style="width:350px;height:50px;"></textarea>
		
	</jsp:body>
	
</t:popup>


