<script type="text/javascript">
	$put(
		'otherLending',
		new function() {
			
			this.info;
			
		}
	);
</script>
<table model="listModel" border="0" width="100%" cellspacing="0" class="grid">
	<tr>
		<td width=130>Company: </td>
		<td class="olive"><label context="otherLending"><b>#{info.company}</b></label></td>
	</tr>    
	<tr>
		<td>Kind of Loan: </td>
		<td><label context="otherLending"><b>#{info.kindofLoan}</b></label></td>
	</tr>	
	<tr>
		<td>Address: </td>
		<td><label context="otherLending"><b>#{info.address}</b></label></td>
	</tr>
	<tr>
		<td>Amount Loaned: </td>
		<td><label context="otherLending"><b>Php</b> <b class="gray">#{info.loanAmount.formatDecimal()}</b></label></td>
	</tr>
	<tr>
		<td>Date Granted: </td>
		<td><label context="otherLending" class="green"><b>#{info.dateGranted}</b></label></td>
	</tr>
	<tr>
		<td>Maturity Date: </td>
		<td><label context="otherLending" class="maroon"><b>#{info.maturityDate}</b></label></td>
	</tr>
	<tr>
		<td>Term: </td>
		<td><label context="otherLending"><b>#{info.term} days</b></label></td>
	</tr>
	<tr>
		<td>Interest Rate: </td>
		<td><label context="otherLending"></b>#{info.interestRate.formatDecimal()} %</b></label></td>
	</tr>
	<tr>
		<td>Mode of Pyt: </td>
		<td><label context="otherLending"><b>#{info.modeofPayment}</b></label></td>
	</tr>
	<tr>
		<td>Lending Pyt: </td>
		<td><label context="otherLending"><b>Php</b> <b class="navy">#{info.lendingPayment.formatDecimal()}</b></label></td>
	</tr>
	<tr>
		<td>Collateral Offered: </td>
		<td>
			<label context="otherLending">
				<span>
					<pre><b>#{info.collateralOffered}</b></pre>
				</span>
			</label>
		</td>
	</tr>
	<tr>
		<td>Remarks: </td>
		<td><label context="otherLending"><b>#{info.remarks? info.remarks: '-'}</b></label></td>
	</tr>
	<tr>
		<td>Others: </td>
		<td><label context="otherLending"><b>#{info.specs? info.specs: '-'}</b></label></td>
	</tr>
</table>
