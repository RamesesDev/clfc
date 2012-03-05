<script>
	$put(
		"ledger", 
		new function() {
			
			this.ledger;
			
		}
	);
	
</script>


<style>	
	.ledger .amount { color: navy; font-weight: bold; }
	.ledger .bal { font-weight: bold; color: maroon; }
</style>

<table class="ledger" width="100%">
	<tr>
		<td valign="top">

			<div class="container">
				<div class="box">
					<b>&nbsp;Loan Information</b>
				</div>
				<table width="95%" align="center">
					<tr>
						<th>Loan Amount:</th>
						<td><label context="ledger" class="amount">#{ledger.loanAmount.formatDecimal()}</label></td>
					</tr>
					<tr>
						<th>Date Granted:</th>
						<td><label context="ledger">#{ledger.dtcreated}</label></td>
					</tr>
					<tr>
						<th>Maturity Date:</th>
						<td><label context="ledger">#{ledger.maturityDate}</label></td>
					</tr>
					<tr>
						<th>Daily Payment:</th>
						<td><label context="ledger" class="amount">#{ledger.dailyPayment.formatDecimal()}</label></td>
					</tr>
					<tr>
						<th>Term:</th>
						<td><label context="ledger">#{ledger.term}</label> Days</td>
					</tr>
					<tr>
						<th>Interest Rate:</th>
						<td><label context="ledger">#{(ledger.interestrate*100.00)}</label> %</td>
					</tr>
					<tr>
						<th>Amount Fully Paid:</th>
						<td><label context="ledger">#{ledger.fullyPaidAmt? ledger.fullyPaidAmt.formatDecimal() : '0.00'}</label></td>
					</tr>
					<tr>
						<th>Date Fully Paid:</th>
						<td><label context="ledger">#{ledger.dateFullyPaid}</label></td>
					</tr>
					<tr>
						<th>Total Absences:</th>
						<td><label context="ledger">#{ledger.totalAbsent? ledger.totalAbsent : 0}</label></td>
					</tr>
					<tr>
						<th>Total Underpayment:</th>
						<td><label context="ledger">#{ledger.totalUnderpayment? ledger.totalUnderpayment : 0}</label></td>
					</tr>
				</table>
			</div>

		</td>
		<td width="5">&nbsp;</td>
		<td valign="top">
			
			<div class="container">
				<div class="box">
					<b>&nbsp;Balances</b>
				</div>
				<table width="95%" align="center">
					<tr>
						<th>Principal Balance:</th>
						<td align="right">
							<label context="ledger" class="bal">#{ledger.principalBalance.formatDecimal()}</label>
						</td>
					</tr>
					<tr>
						<th>Total Principal Payment:</th>
						<td align="right">
							<label context="ledger" class="amount">#{ledger.totalPrincipalPaid.formatDecimal()}</label>
						</td>
					</tr>
					<tr>
						<th>Total Interest Payment:</th>
						<td align="right">
							<label context="ledger" class="amount">#{ledger.totalInterestPaid.formatDecimal()}</label>
						</td>
					</tr>
					<tr>
						<th>Total Penalty Payment:</th>
						<td align="right">
							<label context="ledger" class="amount">#{ledger.totalPenaltyPaid.formatDecimal()}</label>
						</td>
					</tr>
					<tr>
						<th>Total Payment:</th>
						<td align="right">
							<label context="ledger" class="amount">#{ledger.totalPaid.formatDecimal()}</label>
						</td>
					</tr>
				</table>
			</div>

		</td>
	</tr>
</table>

<div style="text-align: right">
	<input type="button"  context="ledger" name="_close" value="Close" immediate="true"/>
</div>

