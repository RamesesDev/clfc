<script type="text/javascript">
	$put(
		'childrenOtherInfo',
		new function() {

			this.employmentList;
			this.otherSourcesOfIncomeList;

			this.selectedObj = {};

			this.onload = function () {
				console.log( $.toJSON(this.selectedObj.employmentList) );				
			}
			this.onload = function () {
				console.log( $.toJSON(this.selectedObj.otherSourcesOfIncomeList) );				
			}

			this.viewSiblingsEmployment = function() {
				var p = new PopupOpener('loan:employmentInfo', {employment: this.selectedObj.employment});
				p.title = 'Employment Information';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewSiblingsOtherIncome = function() {
				var p = new PopupOpener('loan:otherSrcOfIncome', {otherSrcOfIncome: this.selectedObj.otherSrcOfIncome});
				p.title = 'Other Source(s) of Income';
				p.options = {width: 600, height: 500};
				return p;
			};

			this.viewOtherIncome = function() {
				var p = new PopupOpener('loan:otherSrcOfIncome', {list: this.selected.principalOtherSourcesOfIncomeList});
				p.title = 'Other Source(s) of Income';
				p.options = {width: 600, height: 500};
				return p;
			};
		}
	);
</script>
<label context="childrenOtherInfo" class="maroon">
	<b>Employment Information</b>
</label>
<table context="childrenOtherInfo" items="selectedObj.employment" name="extended"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr context="childrenOtherInfo">
			<td>Employer/Address/Tel. No.</td>
			<td>Position</td>
			<td>Years</td>
			<td align="center">Salary</td>
			<td align="center">Status</td>
		</tr>
	</thead>
    <tbody class="selectable">
    	<tr context="childrenOtherInfo">
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
<label context="childrenOtherInfo" class="maroon">
	<b>Other Sources Of Income</b>
</label>
<table context="childrenOtherInfo" items="selectedObj.otherSrcOfIncome" name="extended"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr context="childrenOtherInfo">
			<th>Kind of Business</th>
			<th>Gross/Net Income</th>
			<th>Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="childrenOtherInfo" action="viewOtherIncome">
			<td>#{kindOfBusiness}</td>
			<td>#{grossNetIncome.formatDecimal()}</td>
			<td>#{remarks}</td>
    	</tr>
    </tbody>
</table>