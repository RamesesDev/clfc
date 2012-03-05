<script type="text/javascript">
	$put(
		'profBackground',
		new function() {
			
			this.list;
		}		
	);
</script>


<i context="profBackground" visibleWhen="#{!list}" class="maroon">
	<b>No Professional Background Specified</b>
</i>
<table context="profBackground" items="list" width="100%" visibleWhen="#{list}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>School Attended</th>
			<th>Profession</th>
			<th>Date Graduated</th>
			<th>Remarks/Others</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{schoolAttended}</td>
			 <td>#{profession? profession : '-'}</td>
			 <td align="center">#{dateGraduated? dateGraduated : '-'}</td>
			 <td>#{othersSpecs? othersSpecs : '-'}</td>
		</tr>
	</tbody>
</table>
