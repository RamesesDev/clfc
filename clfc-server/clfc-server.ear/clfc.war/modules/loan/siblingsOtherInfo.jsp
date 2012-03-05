<script type="text/javascript">
	$put(
		'siblingsOtherInfo',
		new function() {

			this.employmentList;
			this.otherSourcesOfIncomeList;
			
			this.siblingsOtherInfo;
			this.spouseSiblingsOtherInfo;
			//this.selectedObj = {};

			this.viewOtherIncome = function() {
				var p = new PopupOpener('loan:otherSrcOfIncome', {list: this.selected.principalOtherSourcesOfIncomeList});
				p.title = 'Other Source(s) of Income';
				p.options = {width: 600, height: 500};
				return p;
			};

		}
	);
</script>
<i context="siblingsOtherInfo" visibleWhen="#{!employmentList}" class="maroon">
	No employment specified.<br/><br/><hr/>
</i>
<label context="siblingsOtherInfo" class="maroon" visibleWhen="#{employmentList.length >0}">
	<b>Employment Information</b>
</label>
<table context="siblingsOtherInfo" items="employmentList"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0"
	   visibleWhen="#{employmentList.length >0}">
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
<!--
<i context="siblingsOtherInfo" visibleWhen="#{!otherSourcesOfIncomeList.length >0}" class="maroon">
	No other income specified<br/>
</i>
<j context="siblingsOtherInfo" class="maroon">
	<b>Other Sources Of Income</b>
</j>
<table context="siblingsOtherInfo" items="otherSourcesOfIncomeList" visibleWhen="#{otherSourcesOfIncomeList}"
	   width="100%" border="0" cellspacing="0" class="grid" width="100%" varStatus="stat">
	<thead>
		<tr>
			<th>Kind of Business</th>
			<th>Gross/Net Income</th>
			<th>Remarks</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{kindOfBusiness}</td>
			 <td align="center">#{grossNetIncome}</td>
			 <td>#{remarks}</td>
		</tr>
	</tbody>
</table>
-->
<i context="siblingsOtherInfo" visibleWhen="#{!otherSourcesOfIncomeList}" class="maroon">
	<br/>No other source(s) of income specified.<br/>
</i>
<j context="siblingsOtherInfo" class="maroon" visibleWhen="#{otherSourcesOfIncomeList.length >0}">
	<b>Other Sources Of Income</b>
</j>
<table context="siblingsOtherInfo" items="otherSourcesOfIncomeList"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0" 
	   visibleWhen="#{otherSourcesOfIncomeList.length >0}">
	<thead>
		<tr>
			<th>Kind of Business</th>
			<th>Gross/Net Income</th>
			<th>Remarks</th>
		</tr>
	</thead>
    <tbody>
    	<tr>
			<td>#{kindOfBusiness}</td>
			<td align="center">#{grossNetIncome.formatDecimal()}</td>
			<td>#{remarks}</td>
    	</tr>
    </tbody>
</table>