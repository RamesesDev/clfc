<script type="text/javascript">
	$put(
		'bankAcct',
		new function() {
			
			this.savingsList;
			this.checkingList;
			this.other;
		}
	);
</script>
<label class="maroon" context="bankAcct" visibleWhen="#{savingsList}">
	<b>Savings Account(s)</b>
</label>
<label class="maroon" context="bankAcct" visibleWhen="#{!savingsList}">
	<b>No Savings Account Specified</b><br/>
</label>
<table context="bankAcct" items="savingsList" visibleWhen="#{savingsList}" 
	   width="100%" border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Bank Name</th>
			<th>Branch</th>
		    <th>Type</th>
			<th>Status</th>
			<th>Others</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{bankName}</td>
			 <td>#{branch}</td>
			 <td>#{type? type: '-' }</td>
			 <td>#{status}</td>
			 <td>#{others? others: '-'}</td>
		</tr>
	</tbody>
</table>
<label class="maroon" context="bankAcct" visibleWhen="#{checkingList}">
	<b>Checking Account(s)</b>
</label>
<label class="maroon" context="bankAcct" visibleWhen="#{!checkingList}">
	<b>No Checking Account Specified</b><br/>
</label>
<table context="bankAcct" items="checkingList" visibleWhen="#{checkingList}"
	   width="100%" border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Bank Name</th>
			<th>Branch</th>
			<th>Kind of Check</th>
			<th>Status</th>
			<th>Others</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{bankName}</td>
			 <td>#{branch}</td>
			 <td>#{kindOfCheck}</td>
			 <td>#{status}</td>
			 <td>#{others? others: '-'}</td>
		</tr>
	</tbody>
</table>
<div context="bankAcct" visibleWhen="#{other}">
	<b class="maroon">Other Accounts</b>
	<br/>
	<label context="bankAcct">
		<span>
			<pre>#{other}</pre>
		</span>
	</label>
</div>
