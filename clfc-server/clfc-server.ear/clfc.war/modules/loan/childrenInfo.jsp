<script>
	$put(
		'childrenInfo',
		new function() {
			
			this.childrenList;
			this.selectedObj = {};

			this.onload = function () {
				console.log( $.toJSON(this.childrenList) );				
				console.log( $.toJSON(this.childrenList.employmentList) );	
			}
			
			this.viewChildrenOtherInfo = function() {
				var p = new PopupOpener('loan:childrenOtherInfo', {
						childrenOtherInfo: this.selectedObj.childrenOtherInfo,
						employmentList: this.childrenList.employmentList,
						otherSourcesOfIncomeList: this.otherSourcesOfIncomeList
				});
				p.title = 'Other Information';
				p.options = {width: 600, height: 500};
				return p;
			};
		}
	);
</script>

<i context="childrenInfo" visibleWhen="#{!childrenList}" class="maroon">
	<b>No Children Specified</b>
</i>
<table context="childrenInfo" items="childrenList" name="extended" visibleWhen="#{childrenList}"
	   width="100%" border="0" cellspacing="0" width="100%" class="grid">
	<thead>
		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Grade/Year</th>
			<th>School</th>
			<th>Course</th>
			<th>Year Graduated</th>
			<th>Status/Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="childrenInfo" action="viewChildrenOtherInfo">
			 <td>#{name}</td>
			 <td>#{age? age: '-'}</td>
			 <td>#{year? year:'-'}</td>
			 <td>#{school? school: '-'}</td>
			 <td>#{course? course: '-'}</td>
			 <td>#{dateGraduated? dateGraduated: '-'}</td>
			 <td>#{status? status: '-'}</td>
		</tr>
	</tbody>
</table>