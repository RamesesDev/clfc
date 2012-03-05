<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"task",
		new function() {
			var svc = ProxyService.lookup("TaskSchedulerService");
			this.entity;
			this.taskid;
			
			this.onload = function() {
				this.entity = svc.read({taskid: this.taskid});
			}
			this.start = function() {
				if(confirm("You are about to activate this task. Proceed?")) {
					alert("start runnung task");
				}
			}
		}
	);
</script>

<style>
	.caption {
		vertical-align:top;font-weight:bolder;width:200px;text-align:right;
	}
</style>

<a context="task" name="_close" style="font-size:10px;">&lt;&lt;Back</a>
<t:content title="Task">
	<jsp:attribute name="actions">
		<input type="button" value="Start" context="task" name="start"/>
	</jsp:attribute>
	<jsp:body>
	
	<table width="80%">
			<tr>
				<td class="caption">Task Id : </td>
				<td><label context="task">#{entity.taskid}</label></td>
			</tr>
			<tr>
				<td class="caption">Script Name/Method : </td>
				<td><label context="task">#{entity.scriptname}.#{entity.method}</label></td>
			</tr>
			<tr>
				<td class="caption">Parameters : </td>
				<td><textarea context="task" name="entity.parameters" style="width:100%;height:100px;" disabled="true"></textarea></td>
			</tr>
			<tr>
				<td class="caption">Allowed Host : </td>
				<td><label context="task">#{entity.allowedhost}</label></td>
			</tr>
			<tr>
				<td class="caption">Duration : </td>
				<td><label context="task">#{entity.duration}</label></td>
			</tr>
			<tr>
				<td class="caption">Start Date/Time : </td>
				<td><label context="task">#{entity.startdate}</label></td>
			</tr>
			<tr>
				<td class="caption">End Date/Time : </td>
				<td><label context="task">#{entity.enddate ? entity.enddate : 'No end date specified'}</label></td>
			</tr>
		</table>
	</jsp:body>
	
</t:content>
