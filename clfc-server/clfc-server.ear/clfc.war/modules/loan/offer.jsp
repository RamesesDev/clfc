<script>
	$put(
		"offer", 
		new function() {
			
			this.application;
			this.assetAppliance;
			this.entity;
			this.offeral;
			this.offeralHandler;

			this.onload = function() {
				//this.offeral = ObjectUtil.clone( this.offeral );
				//if( !this.info.approvalType )
				//	this.info.approvalType = 'approved';
					
				//if( this.info.amountapprovedOptions )
				//	this.selectedOption = this.info.amountapprovedOptions[0];
			};

			/*this.onload = function() {
				this.info = ObjectUtil.clone( this.entity );
				if( !this.info.approvalType )
					this.info.approvalType = 'okandcontinue';
					
				if( this.info.amountapprovedOptions )
					this.selectedOption = this.info.amountapprovedOptions[0];
			};*/
			
			//this.doOk = function() {
				//if( this.offeralHandler ) this.offeralHandler();
				//return "_close";
			//};

			this.doOk = function() {
				if( this.closeHandler ) this.closeHandler();
				return "_close";
			};

			this.close = function() {
				return "_close";
			};

			/*this.propertyChangeListener = {
				'info.approvalType' : function(v) {
					if( v == 'approved' )
						delete _this.info.amountapprovedOptions;
					else
						//_this.info.amountapprovedOptions = [];
						_this.info.approved = [];
				}
			};*/

			/*this.propertyChangeListener = {
				'offeral.approvalType' : function(v) {
					if( v == 'approved' )
						delete _this.info.amountapprovedOptions;
					else
						//_this.info.amountapprovedOptions = [];
						_this.offeral.approved = [];
				}
			};*/
	
			this.doOk = function(){
				//if( !this.offeral.offeredamount ){
				if( !this.entity.offeredamount ){
					throw new Error('Please input an amount to offer.');
				}else if( this.entity.offeredamount >= 1000001 ){
					throw new Error("Amount offered is too big");
				}

				if( !this.entity.policy ){
					throw new Error('Please input a policy.');
				}else if( this.entity.policy >= 51 ){
					throw new Error('Policy exceeded.');
				}

				if( !this.entity.provisions ){
					throw new Error('Please specify the provisions.');
				}else if( this.entity.provisions >= 51 ){
					throw new Error('Provisions exceeded.');
				}

				if( !this.entity.creditLimit ){
					throw new Error('Please specify the  Increase.');
				}else if( this.entity.creditLimit >= 1000001 ){					
					throw new Error('Credit Limit has exceeded.');
				}

				if( !this.entity.increase ){
					throw new Error('Please specify the  Increase.');
				}else if( this.entity.increase >= 1000001 ){					
					throw new Error('Increase amount has exceeded.');
				}

				if( !this.entity.offeralAnnotation || !this.entity.offeralAnnotation.trim() )
					throw new Error('Please specify the offeral annotation.');

				for(var i in this.entity) this.entity[i] = this.entity[i];
				
				if (this.offeralHandler) this.offeralHandler(this.entity.offeralAnnotation);
					//return "_close";

				if (!confirm('You are about to add this offer. Proceed?')) return;
					return "_close";
			};
		}
	);
				function isNumberKey(evt) { 
				var charCode = (evt.which) ? evt.which : event.keyCode ;
				if (charCode > 31 && (charCode < 48 || charCode > 57)) 
					return false; 
					return true; 
				}
</script>
<style>
   body{font-family:arial;font-size:8px;}
   h3.white { color: #ffffff; }
</style>
<fieldset>
	<legend>OFFER</legend>
	<div>
		<table>
			<td valign="top" width="100px">
				<tr>
					<td colspan="5">
						<h2>Applied Amount: 
							<label context="loan_approval">
								<b>Php</b> <b><u><font color="red">#{info.loanamount.formatDecimal()}</font></u></b>
							</label>
						</h2>
						<br/>
						Amount Offered:
						<label context="loan_approval">
							<input id="txtApp" onkeypress="return isNumberKey(event)" type="text" 
								context="loan_approval" name="entity.offeredamount" maxlength="7" 
								style="text-align: right" immediate="true"/>
						</label>
					</td>
				</tr>
				<tr>
					<td>Absences:</td>
					<td valign="top">
						A.) Policy
							<label context="loan_approval">
								<input id="txtApp" onkeypress="return isNumberKey(event)"
									type="text" context="loan_approval" name="entity.policy" maxlength="2" 
									style="text-align: center" immediate="true"/>
							</label>
						B.) Provisions
							<label context="loan_approval">
								<input id="txtApp" onkeypress="return isNumberKey(event)"
									type="text" context="loan_approval" name="entity.provisions" maxlength="2" 
     								style="text-align: center" immediate="true"/>
							</label>
					</td>
				</tr>
				<tr>
					<td>Credit Limit:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="txtApp" onkeypress="return isNumberKey(event)"
								type="text" context="loan_approval" name="entity.creditLimit" maxlength="7" 
								style="text-align: right" immediate="true"/>
						</label>
					</td>
				</tr>
				<tr>
					<td>Increase:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="txtApp" onkeypress="return isNumberKey(event)"
								type="text" context="loan_approval" name="entity.increase" maxlength="7" 
								style="text-align: right" immediate="true"/>
						</label>
					</td>
				</tr>
			</td>	
		</table>
		<table>
			<br/>
			Annotation
			<br/>
			<textarea context="loan_approval"  name="entity.offeralAnnotation" rows="8" 
				style="width:70%;" placeholder="Write your annotations here...">
			</textarea>

			<div class="dialog-buttons">
				<input type="button"  context="offer" name="doOk" value="Ok and continue"/>
			</div>
		</table>
	</div>
</fieldset>