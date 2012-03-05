<%@ taglib tagdir="/WEB-INF/tags/templates" prefix="t" %>

<script type="text/javascript">
	$put(
		"scheduler_list",

		new function() {
			var svc = ProxyService.lookup("TaskSchedulerService");

			this.query = {}
			this.state;
			var self = this;
			this._controller;
			
			this.listModel = {
				rows: 10,
				fetchList : function(o) {
					return svc.getList( o );
				}
			}

			this.open = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					return new DocOpener( "scheduler:task", {taskid: e.taskid} );
				}	
			}
			
			this.create = function() {
				var saveHandler = function(o) {
					svc.create( o );
					self.listModel.load();
				}
				return new PopupOpener( "scheduler:task_new", {saveHandler: saveHandler} ); 
			}
			
			this.start = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					svc.start({taskid:e.taskid});
					self.listModel.load();
				}
			}
			
			this.suspend = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					svc.suspend({taskid:e.taskid});
					self.listModel.load();
				}
			}
			
			this.resume = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					svc.resume({taskid:e.taskid});
					self.listModel.load();
				}
			}
			
			this.recover = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					svc.recover({taskid:e.taskid});
					self.listModel.load();
				}
			}
			
			this.viewError = function() {
				var e = this.listModel.getSelectedItem();
				if(e) {
					var o = svc.getErr({taskid:e.taskid});
					return new PopupOpener("scheduler:task_error", {error:o.msg});
				}
			}
			
			this.onload = function() {
				var func = function() { self.listModel.load(); }
				setInterval( func, 5000 ); 
			}
		}
	);
</script>

<t:content title="Scheduled Tasks">

	<jsp:attribute name="actions">
		<input type="button" value="Add New" context="scheduler_list" name="create" />
	</jsp:attribute>
	
	<jsp:body>
		<table class="grid" context="scheduler_list" model="listModel" varName="item" varStatus="status" width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td align="left">Id</td>
					<td align="left">Script Name</td>
					<td align="left">Method</td>
					<td align="center">Start Date</td>
					<td align="center">End Date</td>
					<td align="center">Duration</td>
					<td align="center">Status</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="left">#{item.taskid}</td>
					<td align="left">#{item.scriptname}</td>
					<td align="left">#{item.method}</td>
					<td align="center">#{item.startdate}</td>
					<td align="center">#{item.enddate? item.enddate : '-'}</td>
					<td align="center">#{item.duration}</td>
					<td align="center">#{item.status}</td>
					<td align="left">
						<a context="scheduler_list" name="start" visibleWhen="#{item.status == 'inactive'}">Start</a>
						<a context="scheduler_list" name="suspend" visibleWhen="#{item.status == 'queue'}">Suspend</a>
						<a context="scheduler_list" name="resume" visibleWhen="#{item.status == 'suspended'}">Resume</a>
						<a context="scheduler_list" name="viewError" visibleWhen="#{item.status == 'error'}">ViewMsg</a>
						<a context="scheduler_list" name="recover" visibleWhen="#{item.status == 'error'}">Recover</a>
					</td>
					<td align="left">
						<a context="scheduler_list" name="open">Open</a>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr class="control">
					<td colspan="9" align="right">
						<input type="button" context="scheduler_list" name="listModel.moveFirst" value="&lt;&lt;" />
						<input type="button" context="scheduler_list" name="listModel.movePrev" value="&lt;" />
						<input type="button" context="scheduler_list" name="listModel.moveNext" value="&gt;" />
					</td>
				</tr>
			</tfoot>
		</table>
	</jsp:body>
</t:content>

