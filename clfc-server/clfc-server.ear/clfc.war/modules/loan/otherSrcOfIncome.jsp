<script type="text/javascript">
	$put(
		'otherSrcOfIncome',
		new function() {
		
			this.list;
			
		}
	);
</script>

<i context="otherSrcOfIncome" visibleWhen="#{!list}" class="maroon">
	<b>No other source(s) specified</b>
</i>
<table context="otherSrcOfIncome" items="list" visibleWhen="#{list}"
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
