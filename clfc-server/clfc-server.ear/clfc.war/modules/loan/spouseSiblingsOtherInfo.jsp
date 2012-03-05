<script type="text/javascript">
	$put(
		'spouseSiblingsOtherInfo',
		new function() {

			this.employmentList;
			this.otherSourcesOfIncomeList;
			//this.selectedObj = {};
		}
	);
</script>

<label context="spouseSiblingsOtherInfo" class="maroon">
	<b>Employment Information</b>
</label>
<i context="clientInfo" visibleWhen="#{!employmentList.length>0}" class="maroon">
	No Employment Information specified.<br/>
</i>
<table context="spouseSiblingsOtherInfo" items="employmentList" width="100%" visibleWhen="#{employmentList.length >0}" 
       border="0" cellpadding="0" class="grid" cellspacing="0">
	<thead>
		<tr>
			<td>Employer/Address/Tel. No.</td>
			<td>Position</td>
			<td>Years</td>
			<td align="center">Salary</td>
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
    		<td align="center">#{salary.formatDecimal()}</td>
    		<td align="center">#{status}</td>
    	</tr>
    </tbody>
</table>

<label context="spouseSiblingsOtherInfo" class="maroon">
	<b>Other Sources Of Income</b>
</label>
<i context="clientInfo" visibleWhen="#{!otherSourcesOfIncomeList.length>0}" class="maroon">
	No Other Sources of Income specified.<br/>
</i>
<table context="spouseSiblingsOtherInfo" items="otherSourcesOfIncomeList" width="100%" visibleWhen="#{otherSourcesOfIncomeList.length >0}" 
       border="0" cellpadding="0" class="grid" cellspacing="0">
	<thead>
		<tr context="spouseSiblingsOtherInfo">
			<th>Kind of Business</th>
			<th>Gross/Net Income</th>
			<th>Remarks</th>
		</tr>
	</thead>
    <tbody>
    	<tr context="spouseSiblingsOtherInfo">
			<td>#{kindOfBusiness}</td>
			<td>#{grossNetIncome.formatDecimal()}</td>
			<td>#{remarks}</td>
    	</tr>
    </tbody>
</table>
