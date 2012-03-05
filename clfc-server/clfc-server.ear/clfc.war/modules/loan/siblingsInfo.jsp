<script type="text/javascript">
	$put(
		'siblingsInfo',
		new function() {

			this.parents;
			this.principalsiblingsList;
			this.principalSpouseSiblingsList;
			this.selectedObj = {};	

			this.onload = function () {
				console.log( $.toJSON(this.principalsiblingsList) );				
			}
			
			this.onload = function () {
				console.log( $.toJSON(this.principalSpouseSiblingsList) );				
			}
	
			this.viewSiblingsOtherInfo = function() {
				var p = new PopupOpener('loan:siblingsOtherInfo', {
						siblingsOtherInfo: this.selectedObj.siblingsOtherInfo,
						employmentList: this.principalsiblingsList.employmentList,
						otherSourcesOfIncomeList: this.principalsiblingsList.otherSourcesOfIncomeList
				});
				p.title = 'Other Information';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewSpouseSiblingsOtherInfo = function() {
				var p = new PopupOpener('loan:spouseSiblingsOtherInfo', {
						spouseSiblingsOtherInfo: this.selectedObj.spouseSiblingsOtherInfo,
						employmentList: this.principalSpouseSiblingsList.employmentList,
						otherSourcesOfIncomeList: this.principalSpouseSiblingsList.otherSourcesOfIncomeList
				});
				p.title = 'Other Information';
				p.options = {width: 600, height: 500};
				return p;
			};
		}
	);
</script>

			<!-- Principal Parents -->
<b context="siblingsInfo" class="maroon">
	<h3>Parents Information</h3>
</b>
<table model="listModel" border="0" width="100%" class="grid">
    <tr context="siblingsInfo">
        <td width="130">Fathers Name: </td>
        <td><label context="siblingsInfo"><b>#{parents.principalFathersName? parents.principalFathersName: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Birthdate: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalFathersBDate? parents.principalFathersBDate: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Age: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalFathersAge? parents.principalFathersAge: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Mothers Name: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalMothersName? parents.principalMothersName: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Birthdate: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalMothersBDate? parents.principalMothersBDate: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Age: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalMothersAge? parents.principalMothersAge: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Address: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalParentsAddress? parents.principalParentsAddress: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Remarks / Others: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalOthersSpecs? parents.principalOthersSpecs: '-'}</b></label></td>
	</tr>
</table>
<hr/>
<b context="siblingsInfo" class="maroon">
	<h3>Siblings</h3>
</b>
<i context="siblingsInfo" visibleWhen="#{!principalsiblingsList.length >0}" class="maroon">
	<b>No Siblings Specified</b><br/><br/>
</i>	
<table context="siblingsInfo" items="principalsiblingsList" width="100%" visibleWhen="#{principalsiblingsList.length >0}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="siblingsInfo" action="viewSiblingsOtherInfo">
			 <td>#{name}</td>
			 <td class="gray"><b>#{age? age : '-'}</b></td>
			 <td>#{address? address : '-'}</td>
		</tr>
	</tbody>
</table>
<hr/>
	<!-- Principal Parents Spouse-->
<b context="siblingsInfo" class="maroon">
	<h3>Parents Spouse Information</h3>
</b>
<table model="listModel" border="0" width="100%" cellspacing="0" class="grid">
	<tr context="siblingsInfo">
		<td width="130">Fathers Name: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalSpouseFathersName? parents.principalSpouseFathersName: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Birthdate: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalSpouseFathersBDate? parents.principalSpouseFathersBDate: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Age: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalSpouseFathersAge? parents.principalSpouseFathersAge: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Mothers Name: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalSpouseMothersName? parents.principalSpouseMothersName: '-'}</b></label></td>
	</tr>
	<tr context="siblingsInfo">
		<td>Birthdate: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalSpouseMothersBDate? parents.principalSpouseMothersBDate: '-'}</b></label></td>
	</tr>	
	<tr context="siblingsInfo">
		<td>Age: </td>
		<td><label context="siblingsInfo"><b class="gray">#{parents.principalSpouseMothersAge? parents.principalSpouseMothersAge: '-'}</b></label></td>
	</tr>	
	<tr context="siblingsInfo">
		<td>Address: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalSpouseAddress? parents.principalSpouseAddress: '-'}</b></label></td>
	</tr>	
	<tr context="siblingsInfo">
		<td>Remarks / Others: </td>
		<td><label context="siblingsInfo"><b>#{parents.principalSpouseOthersSpecs? parents.principalSpouseOthersSpecs: '-' }</b></label></td>
	</tr>	
</table>
<hr/>
<b context="siblingsInfo" visibleWhen="#{principalSpouseSiblingsList.length >0}" class="maroon">
	<h3>Spouse Siblings</h3>
</b>
<i context="siblingsInfo" visibleWhen="#{!principalSpouseSiblingsList.length >0}" class="maroon">
	<b>No Spouse Siblings Specified</b><br/><br/>
</i>
<table context="siblingsInfo" items="principalSpouseSiblingsList" width="100%" visibleWhen="#{principalSpouseSiblingsList.length >0}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="siblingsInfo" action="viewSiblingsOtherInfo">
			 <td>#{name}</td>
			 <td class="gray"><b>#{age? age : '-'}</b></td>
			 <td>#{address? address : '-'}</td>
		</tr>
	</tbody>
</table>
