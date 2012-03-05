<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"task_new",
		new function() {
		
			this.entity = {};
			this.saveHandler;
			this.duration_value = 1;
			this.duration_type = "m";
			this.startdate;
			this.starthour = "00";
			this.startmin = "00";
			
			this.enddate;
			this.endhour = "00";
			this.endmin = "00";
			this.hasEndTime = false;
			
			this.durationTypes = ["m", "d", "s"]
			this._hours;
			this.hours = function() {
				if(this._hours==null) {
					this._hours = [];
					for(var i=0;i<24;i++) {
						var hr = i;
						if(i<10) hr = "0"+i;
						this._hours.push(hr);
					}
				}
				return this._hours;
			}
			this._minutes;
			this.minutes = function() {
				if(this._minutes==null) {
					this._minutes = [];
					for(var i=0;i<60;i++) {
						var min = i;
						if(i<10) min = "0"+i;
						this._minutes.push(min);
					}
				}
				return this._minutes;
			}
			
			
			this.save = function() {
				this.entity.duration = this.duration_value+this.duration_type;
				this.entity.startdate = this.startdate+" "+this.starthour+":"+this.startmin+":00";
				if(this.hasEndTime) {
					this.entity.enddate = this.enddate+" "+this.endhour+":"+this.endmin+":00";
				}
				else {
					this.entity.enddate = null;
				}
				this.saveHandler(this.entity);
				return "_close";
			}
			
			
		}
	);
</script>

<t:popup>
	<jsp:attribute name="leftactions">
		<input type="button" context="task_new" name="save" value="Save"/>
	</jsp:attribute>
	<jsp:body>
		<table>
			<tr>
				<td>Script Name</td>
				<td>Method</td>
			</tr>
			<tr>
				<td><input type="text" context="task_new" name="entity.scriptname" /></td>
				<td><input type="text" context="task_new" name="entity.method"/></td>
			</tr>
			
			<tr>
				<td colspan="2">Parameters</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea context="task_new" name="entity.parameters" style="width:100%"></textarea>
				</td>
			</tr>
			<tr>
				<td>Duration</td>
				<td>Allowed Host</td>
			</tr>
			<tr>
				<td>
					<input type="text" context="task_new" name="duration_value" size="2"/>
					<select context="task_new" name="duration_type" items="durationTypes"></select>
				</td>
				<td>
					<input type="text" context="task_new" name="entity.allowedhost" />
				</td>
			</tr>
			
			<tr>
				<td>Start Date</td>
				<td>Start Time</td>
			</tr>
			<tr>
				<td><input type="text" context="task_new" name="startdate" datatype="date" /></td>
				<td>
					<select context="task_new" name="starthour" items="hours()" allowNull="true" required="true" caption="Start Hours"></select>:
					<select context="task_new" name="startmin" items="minutes()" allowNull="true" required="true" caption="Start Minutes"></select>
				</td>
			</tr>
		</table>

		<input type="checkbox" context="task_new" name="hasEndTime"/>Has End Time
		
		<div context="task_new" visibleWhen="#{hasEndTime}" depends="hasEndTime">	
			<table>
				<tr>
					<td>End Date</td>
					<td>End Time</td>
				</tr>
				<tr>
					<td><input type="text" context="task_new" name="enddate" datatype="date" /></td>
					<td>
						<select context="task_new" name="endhour" items="hours()" allowNull="true" required="true" caption="Start Hours"></select>:
						<select context="task_new" name="endmin" items="minutes()" allowNull="true" required="true" caption="Start Minutes"></select>
					</td>
				</tr>
			</table>
		</div>
		
	</jsp:body>
</t:popup>


