<script type="text/javascript">
	$put(
		'employmentInfo',
		new function() {

			this.list;

		}
	);
</script>

<i context="employmentInfo" visibleWhen="#{!list}" class="maroon">
	<b>No Employment Specified</b>
</i>
<table context="employmentInfo" items="list" name="extended" visibleWhen="#{list}"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<td>Employer/Address/Tel. No.</td>
			<td>Position</td>
			<td>Years</td>
			<td>Salary</td>
			<td align="center">Status</td>
		</tr>
	</thead>
    <tbody>
    	<tr>
    		<td>
    			<b>#{employer}</b><br/>
    			<i>#{address} #{telno? '<br/>' + telno : ''}</i>
    		</td>
    		<td>#{position}</td>
    		<td>#{years}</td>
    		<td><b class="gray">Php</b> #{salary}</td>
    		<!--<td align="center">#{salary.formatDecimal()}</td>-->
    		<td align="center">#{status}</td>
    	</tr>
    </tbody>
</table>