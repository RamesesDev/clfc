<script>
	$put(
		"loan_approval", 
		new function() {
			
			var _this = this;
			this._controller;
			
			this.entity;
			this.info;
			this.selectHandler;
			this.annotation;
			this.selectedOption;
			
			this.addProvisions;
			this.provisionsHandler;
			
			this.propertyChangeListener = {
				'info.approvalType' : function(v) {
					if( v == 'fixed' )
						delete _this.info.amountapprovedOptions;
					else
						_this.info.amountapprovedOptions = [];
				}
			};

			this.onload = function() {
				this.info = ObjectUtil.clone( this.entity );
				if( !this.info.approvalType )
					this.info.approvalType = 'fixed';
					
				if( this.info.amountapprovedOptions )
					this.selectedOption = this.info.amountapprovedOptions[0];
			};
			
			this.close = function() {
				return "_close";
			};
			
			this.approve = function(){
				if( this.info.approvalType == 'conditional' ) {
					if( this.info.amountapprovedOptions.length < 2 ) {
						throw new Error('Amount options must have at least two entries.');	
					}
					for(var i=0; i<this.info.amountapprovedOptions.length; ++i) {
						var itm = this.info.amountapprovedOptions[i];
						if( !itm.remarks || !itm.remarks.trim() ) {
							this.selectedOption = itm;
							this._controller.refresh();
							$('#loan_approval_remarks').focus();
							throw new Error('Remarks is required for amount ' + itm.amount + '.');	
						}
					}
				}
				
				if( !this.annotation || !this.annotation.trim() )
					throw new Error('Please specify the annotation.');
				
				if (!confirm('You are about to approve this transaction. Proceed?')) return;

				for(var i in this.info) this.entity[i] = this.info[i];
				
				if (this.selectHandler) this.selectHandler(this.annotation);
				return "_close";
				
			};
			
			this.addAmount = function() {
				var amt = prompt("Enter an amount.", "");
				if( amt ) {
					amt = parseFloat(amt);
					if( isNaN(amt) )
						throw new Error('Invalid amount entered.');
					
					var itm = {amount: amt, remarks: ''};
					this.info.amountapprovedOptions.push( itm );
					if( !this.selectedOption ) this.selectedOption = itm;
				}
			}
			
			this.removeAmount = function() {
				if( !this.selectedOption ) return;
				if( !confirm('Are you sure you want to remove the selected amount?') ) return;
				
				var so = this.selectedOption, op = this.info.amountapprovedOptions;
				for(var i=0; i<op.length; ++i) {
					if( so.amount == op[i].amount ) {
						op.splice(i, 1);
						break;	
					}
				}
				this.selectedOption = null;
			}
</script>

<style>
	.form-lbl { width: 150px; display: inline-block; }
	.selection { background: #fff; border: 1px inset #aaa; }
	.selection td { padding: 3px 5px; cursor: pointer; }
	.selection .selected { background: #ccc; }
</style>

<div class="form-lbl">
	Applied Amount:
</div>
<label context="loan_approval">
	<b>#{info.loanamount.formatDecimal()}</b>
</label>
<br/>

<fieldset>
	<legend>Amount Approval</legend>
	<label>
		<input type="radio" context="loan_approval" name="info.approvalType" value="fixed"/>
		Fixed Amount
	</label>
	<label>
		<input type="radio" context="loan_approval" name="info.approvalType" value="conditional"/>
		Conditional
	</label>

	<div context="loan_approval" depends="info.approvalType" visibleWhen="#{info.approvalType == 'fixed'}"
	     style="height: 100px;">
		<div class="form-lbl">
			Approved Amount:
		</div>
		<input type="text" context="loan_approval" name="info.amountapproved" datatype="decimal"
			   required="true" caption="Approve amount" depends="info.approvalType"/>
	</div>

	<div context="loan_approval" depends="info.approvalType" visibleWhen="#{info.approvalType == 'conditional'}">
		Amount Options
		<table width="100%" cellpadding="0">
			<tr>
				<td width="120px" class="selection" height="100px" valign="top">
					<table context="loan_approval" name="selectedOption" items="info.amountapprovedOptions" 
					       depends="info.approvalType" width="100%" cellspacing="0">
						<tbody>
							<tr>
								<td align="right" onmousedown="$('#loan_approval_remarks').trigger('change')">
									#{amount.formatDecimal()}
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				<td>
					<textarea id="loan_approval_remarks" context="loan_approval" name="selectedOption.remarks"
					          depends="selectedOption,info.approvalType" style="width:100%;height:100%;margin:0px"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" context="loan_approval" name="addAmount" value="Add Amount" immediate="true"/>
					<input type="button" context="loan_approval" name="removeAmount" value="Remove Amount" immediate="true"/>
				</td>
			</tr>
		</table>
	</div>
</fieldset>

Annotation
<br/>
<textarea context="loan_approval"  name="annotation" rows="7" style="width:100%;"></textarea>

<!--Adding Provision Button -->
<input type="button" context="loan_approval" name="addProvisions" value="Add Provisions" immediate="true"/>

<div class="dialog-buttons">
	<input type="button"  context="loan_approval" name="approve" value="Approve"/> 
	<input type="button"  context="loan_approval" name="close" value="Cancel" immediate="true"/>
</div>
