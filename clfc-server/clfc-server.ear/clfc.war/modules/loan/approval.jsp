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
			//this.selectedOption;
			this.application;
			
			this.selected;
			this.collateralInfo;
			this.collateral;
			this.selectedObj = {};
			this.mustList;
			this.offeral;
			this.additionalCollateralList;
			this.additionalCollateral;
		
			this.onload = function() {
				this.info = ObjectUtil.clone( this.entity );
				//if( !this.info.approvalType )
				//	this.info.approvalType = 'approved';
					
				//if( this.info.amountapprovedOptions )
				//	this.selectedOption = this.info.amountapprovedOptions[0];
			};

			this.viewCollateral = function() {
				var p = new PopupOpener('loan:collateralInfo', {
						collateral: this.selectedObj.collateral,
						application: this.selected,
						closeHandler: collateralHandler
				});
				p.title = 'Must/Required Collateral(s)';
				p.options = {width: 800, height: 580};
				return p
			}

			this.viewOffer = function() {
				var p = new PopupOpener('loan:offer', {
						offer: this.selectedObj.offer,
						entity: this.entity,
						closeHandler: offeralHandler
						//offeral:this.offeral
				});
				p.title = 'Offeral Form';
				p.options = {width: 630, height: 530};
				return p
			}

			var offeralHandler = function(){
				_this.entity = [];
				
				var entity = $ctx('loan').selected;

				console.log( info.offeredamount );
				console.log( info.policy );
				console.log( info.provisions );
				console.log( info.creditLimit );
				console.log( info.increase );
				console.log( info.offeralAnnotation );

				//_this.entity = [];

				alert("entity");
				_this.offerModel.load();							
			};
			
			var collateralHandler = function(){
				_this.mustList = [];

				var app = $ctx('loan').selected;

				console.log( app.appliancelist );
				console.log( app.vehiclelist );
				console.log( app.propertylist );
				console.log( app.otherslist );
				
				for(var i=0; i<app.appliancelist.length; ++i){
					var a = app.appliancelist[i];
					if( a.must == true ) _this.mustList.push(a);
				}
				for(var i=0; i<app.vehiclelist.length; ++i){
					var a = app.vehiclelist[i];
					if( a.must == true ) _this.mustList.push(a);
				}
				for(var i=0; i<app.propertylist.length; ++i){
					var a = app.propertylist[i];
					if( a.must == true ) _this.mustList.push(a);
				}
				for(var i=0; i<app.otherslist.length; ++i){
					var a = app.otherslist[i];
					if( a.must == true ) _this.mustList.push(a);
				}
				
				_this.mustModel.load();
			};
			
			this.mustModel = {
				fetchList: function(){ return _this.mustList }
			};
		
			this.offerModel = {
				fetchList: function(){ return _this.entity }
			};

			var additionalCollateralHandler = function(){
				_this.additionalCollateral = [];

				var add = $ctx('loan').selected;
				console.log( add.additionalCollateral );

				/*for(var i=0; i<add.additionalCollateral.length; ++i;){
					var a = add.additionalCollateral[i];
					if( a.additionalCollateral == true ) _this.additionalCollateral.push(a);
				}*/

				alert("additionalCollateral");
				_this.additionalCollateralModel.load();
			};

			this.additionalCollateralModel = {
				fetchList: function(){ return _this.additionalCollateral }
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

			this.approve = function()
			{
				/*      LOAN APPROVAL		*/
				if( !this.info.amountapproved ){
					throw new Error('Please input an amount to approved.');
				}else if( this.info.amountapproved >= 1000001 ){
					throw new Error("Amount approved is too big");
				}

				if( !this.info.policy ){
					throw new Error('Please input a policy.');	
				}else if( this.info.policy >= 51 ){
					throw new Error('Policy exceeded.');
				}

				if( !this.info.provisions ){
					throw new Error('Please specify the provisions.');
				}else if( this.info.provisions >= 51 ){
					throw new Error('Provisions exceeded.');
				}

				if( !this.info.creditLimit ){
					throw new Error('Please specify the  Increase.');
				}else if( this.info.creditLimit >= 1000001 ){					
					throw new Error('Credit Limit has exceeded.');
				}

				if( !this.info.increase ){
					throw new Error('Please specify the increase amount.');
				}else if( this.info.increase >= 1000001 ){					
					throw new Error( 'Increase amount has exceeded.' );
				}

				if( !this.info.approvalAnnotation || !this.info.approvalAnnotation.trim() )
					throw new Error('Please specify the approval annotation.');

				/*      LOAN OFFER		*/
				if( this.info.offeredamount >= 1000001 ){
					throw new Error("Amount approved is too big");
				}

				if( this.info.offeredpolicy >= 51 ){
					throw new Error('Policy exceeded.');
				}

				if( this.info.offeredprovisions >= 51 ){
					throw new Error('Provisions exceeded.');
				}

				if( this.info.offeredcreditLimit >= 1000001 ){					
					throw new Error('Credit Limit has exceeded.');
				}

				if( this.info.offeredincrease >= 1000001 ){					
					throw new Error( 'Increase amount has exceeded.' );
				}

				if( this.info.offeredamount ){
					if( !this.info.offeralAnnotation || !this.info.offeralAnnotation.trim() )
						throw new Error('Please specify the offeral annotation.');	
				}				
				///if( !this.info.offeralAnnotation || !this.info.offeralAnnotation.trim() )
					//throw new Error('Please specify the offeral annotation.');	

				if (!confirm('You are about to approve this transaction. Proceed?')) return;

				for(var i in this.info) this.entity[i] = this.info[i];
				
				if (this.selectHandler) this.selectHandler(this.info.approvalAnnotation);
					return "_close";				
			};

			this.close = function() {
				return "_close";
			};

            //this.clearAll = function() {
				//this.info = { info:[] } //#{info.loanamount.formatDecimal()}
			//}
			
			// Design 
			 //******************** Tooltip ******************************//	
			// select all desired input fields and attach tooltips to them
			//$("#put :input").tooltip({

				// place tooltip on the right edge
				//position: "center right",

				// a little tweaking of the position
				//offset: [-2, 10],

				// use the built-in fadeIn/fadeOut effect
				//effect: "fade",

				// custom opacity setting
				//opacity: 0.7

			//});
			// ************************************************************

			// Mouse Click event on Collateral(s) Attached
			$("#b").hover(
				function(){
					//$("#b").css('display','block');
					$("#cb").fadeIn();
				},
				function(){
					//$("#b").css('display','none');
					$("#cb").fadeOut();
				}
			);

			var flgClick=false;
			$("#a").click(function(){
				if(flgClick){
					$("#z").fadeIn();
					flgClick=false;
				}
				else{
					$("#z").fadeOut();
					flgClick=true;
				}
			});
			$("#c").click(function(){
				if(flgClick){
					$("#o").fadeIn();
					flgClick=false;
				}
				else{
					$("#o").fadeOut();
					flgClick=true;
				}
			});
		}
	);
		//Pure number validation
		function isNumberKey(evt) { 
			var charCode = (evt.which) ? evt.which : event.keyCode ;
			if (charCode > 31 && (charCode < 48 || charCode > 57)) 
				return false; 
				return true; 
		}

		//Enter key usage
		function handleEnter(inField, e) {
			var charCode;

			if(e && e.which){
				charCode = e.which;
			}else if(window.event){
				e = window.event;
				charCode = e.keyCode;
			}

			if(charCode == 13) {
				alert("Enter was pressed on " + inField.id);
			}
		}
		
		//Fade out hint 
		 $("input").focus(function () {
         		$(this).next("span").css('display','inline').fadeOut(1000);
    	 });
</script>
<style>
	.form-lbl { width: 150px; display: inline-block; }
	.selection { background: #fff; border: 1px inset #aaa; }
	.selection td { padding: 3px 5px; cursor: pointer; }
	.selection .selected { background: #ccc; }
</style>
<fieldset>
	<legend class="green">LOAN APPROVAL</legend>
	<fieldset>
	    <!--<div style="text-align: left">-->
            <legend id="xyz" context="loan_approval">
                <a id="c" href="#" context="loan_approval" title="Click to view instructions" immediate="true"><b class="purple">Instructions</b></a>
            </legend>
            <div id="o" style="display:none">
                <ul>
                    <j class="dgold"><li></j>
				            <j class="purple">Clearly input the textbox (e.g)</j> <b class="purple">Approved Amount, Credit Limit, Increase</b> <j class="purple">with the amount provided.</j>
			            </a>
		            </li>
                    <j class="dgold"><li></j>
				            <j class="purple">Click the highlighted</j> <b class="purple">Must/Required Colalteral(s)</b> <j class="purple">to add a specific required collateral(s).</j>
			            </a>
		            </li>
	                <j class="dgold"><li></j>
				            <j class="purple">Click the highlighted</j> <b class="purple">Loan Offer</b> <j class="purple">to add an offer for the Loan Application.</j>
			            </a>
		            </li>
		            <j class="dgold"><li></j>
				             <j class="purple">Click the button</j> <b class="purple">Approve</b> <j class="purple">to approve this</j> <b class="purple">Loan Application.</b>
			            </a>
		            </li>
		            <j class="dgold"><li></j>
		                    <j class="purple">Click the button</j> <b class="purple">Cancel</b> <j class="purple">to close this</j> <b class="purple">Loan Approval Form.</b>
			            </a>
		            </li>						
	            </ul>
	        </div>
        <!--</div>-->
    </fieldset>
	<div>
		<table>
			<td valign="top" width="100px">
				<tr>
					<!--<td>Applied Amount:</td>-->
					<td colspan="5">
						<h3>Applied Amount: 
							<label context="loan_approval" name="loanAmount">
								<b>Php</b> <b><u><font color="red">#{info.loanamount.formatDecimal()}</font></u></b>
							</label>
						</h3>
						<br/>
						Approved Amount:
							<label context="loan_approval">
								<input id="approvedamount" 
									onkeypress="return isNumberKey(event)" context="loan_approval"
										name="info.amountapproved" datatype="decimal" required="true" maxlength="7" 
											style="text-align: right" value="0.00"/><br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;
											<j class="red2">type the amount</j> <j class="gray2"> (e.g. 1000000)</j>
							</label>
					</td>
				</tr>
				<tr>
					<td>Absences:</td>
					<td>
						A.) Policy
							<label context="loan_approval">
								<input id="policy" onkeypress="return isNumberKey(event)"
									context="loan_approval" name="info.policy" required="true" 
										immediate="true" maxlength="2" datatype="integer" style="text-align: center"/>										
							</label>
						B.) Provisions
							<label context="loan_approval">
								<input id="provisions" onkeypress="return isNumberKey(event)"
									context="loan_approval" required="true" immediate="true" 
										name="info.provisions" datatype="integer" maxlength="2" style="text-align: center"/><br/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<j class="gray2"> (e.g. 20/30)</j>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;
										<j class="gray2"> (e.g. 20/30)</j>
							</label>
					</td>
				</tr>
				<tr>
					<td>Credit Limit:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="creditLimit" onkeypress="return isNumberKey(event)"
								context="loan_approval" name="info.creditLimit" required="true"
									immediate="true" maxlength="7" datatype="decimal" style="text-align: right"/><br>
									<j class="red2">type the amount</j><j class="gray2"> (e.g. 1000000)</j>
						</label>
					</td>
				</tr>
				<tr>
					<td>Increase:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="increase" onkeypress="return isNumberKey(event)"
								context="loan_approval" name="info.increase" required="true" 
									immediate="true" maxlength="7" datatype="decimal" style="text-align: right"/><br/>
									<j class="red2">type the amount</j><j class="gray2"> (e.g. 1000000)</j>
						</label>
					</td>
				</tr>
			</td>	
		</table><br/>
		<div id="xyz">
			<a href="#" context="loan_approval" name="viewCollateral" title="Click to View/Add Must Collateral(s)" immediate="true">
				<b class="teal">Must/Required Collateral(s)</b>
			</a><br/>
			<table context="loan_approval" model="mustModel">
				<tbody>
					<td valign="top">
						<tr context="loan_approval">
							<!--<td>Appliance(s)</td>-->
							<td>
							    <span>
								        #{type? type: ' '} - #{brand? brand:' '} ( #{model? model:' '} )
                                        #{plateno? plateno:' '}
								        #{rpu? rpu:' '} - #{location? location:' '} / #{area? area:' '} #{uom? uom:' '}
								        #{subject? subject:' '} - #{registeredName? registeredName:' '}
								</span>
							</td>
						</tr>
					</td>
				</tbody>
			</table>
			<table context="loan_approval" model="additionalCollateralModel">
				<tbody>
					<td valign="top">
						<tr context="loan_approval">
							<!--<td>#{additionalCollateral}</td>-->
							<td>entity.additionalCollateral</td>
						</tr>
					</td>
				</tbody>
			</table>
		</div>
		<div>
		    <div style="text-align: left">
		        <br/>
			        Annotation
		        <br/>
		        <textarea context="loan_approval" name="info.approvalAnnotation" rows="6" style="width:60%;" 
				        placeholder="Write your annotations here...">
		        </textarea>
		    </div>
		    
		</div>
		<!--<div align="center">
			<input type="button" context="loan_approval" name="clearAll" value="Clear all fields" immediate="true"/>
		</div>-->
		<div class="dialog-buttons">
			<input type="button" context="loan_approval" name="approve" value="Approve" immediate="true"/>
			<input type="button" context="loan_approval" name="close"   value="Cancel"  immediate="true"/>
		</div>
		<br>
	</div>	
</fieldset>
<fieldset><!-- LOAN OFFER -->
	<legend id="xyz" context="loan_approval">
		<!--<a id="a" href="#" title="Click to View/Add Offer" context="loan_approval" name="viewOffer" immediate="true">LOAN OFFER</a>-->
		<a id="a" href="#" title="Double click to View/Add Loan Offer" context="loan_approval"><b class="navy">LOAN OFFER</b></a>
	</legend>
	<div id="z" style="display:none">
		<table>
			<td valign="top" width="100px">
				<tr>
					<td colspan="5">
						<h3>Applied Amount: 
							<label context="loan_approval">
								<b>Php</b> <b><u><font color="red">#{info.loanamount.formatDecimal()}</font></u></b>
							</label>
						</h3>
						<br/>
						Offered Amount:
							<label context="loan_approval">
								<input id="offeredamount" 
									onkeypress="return isNumberKey(event)" context="loan_approval"
										name="info.offeredamount" datatype="decimal" required="true"
											maxlength="7" style="text-align: right"/><br>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    										<j class="red2">type the amount</j> <j class="gray2"> (e.g. 1000000)</j>
							</label>
							<!--jQuery("idspolicy").focus();--> 
							<!--<input type="text" id="someInput" onkeypress="handleEnter(this, event)" />-->
							<!--onkeypress="return isNumberKey(event)"-->

							<!--onclick="dollarAmount(this.form.name, 'input')"-->
							<!--<input type="text" context="loan_approval" name="info.amountapproved" datatype="decimal"
							   required="true" caption="Approve amount" depends="info.approvalType"/>-->

							<!--<input type="number" context="loan_approval" name="info.amountapproved" datatype="decimal"
							   required="true" caption="Approve amount" depends="info.approvalType"/>-->
					</td>
				</tr>
				<tr>
					<td>Absences:</td>
					<td>
						A.) Policy
							<label context="loan_approval">
								<input id="offeredpolicy" onkeypress="return isNumberKey(event)"
									context="loan_approval" name="info.offeredpolicy" required="true" 
										immediate="true" maxlength="2" datatype="integer" style="text-align: center"/>
							</label>
						B.) Provisions
							<label context="loan_approval">
								<input id="offeredprovisions" onkeypress="return isNumberKey(event)"
									context="loan_approval" required="true" immediate="true" 
										name="info.offeredprovisions" datatype="integer" maxlength="2" style="text-align: center"/><br/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<j class="gray2"> (e.g. 20/30)</j>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;
									<j class="gray2"> (e.g. 20/30)</j>
							</label>
					</td>
				</tr>
				<tr>
					<td>Credit Limit:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="offeredcreditLimit" onkeypress="return isNumberKey(event)"
								context="loan_approval" name="info.offeredcreditLimit" required="true"
									immediate="true" maxlength="7" datatype="decimal" style="text-align: right"/><br/>
									<j class="red2">type the amount</j><j class="gray2"> (e.g. 1000000)</j>
						</label>
					</td>
				</tr>
				<tr>
					<td>Increase:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="offeredincrease" onkeypress="return isNumberKey(event)"
								context="loan_approval" name="info.offeredincrease" required="true" 
									immediate="true" maxlength="7" datatype="decimal" style="text-align: right"/><br/>
									<j class="red2">type the amount</j><j class="gray2"> (e.g. 1000000)</j>
						</label>
					</td>
				</tr>
			</td>	
		</table>
		    Annotation
		    <br/>
		    <textarea context="loan_approval"  name="info.offeralAnnotation" rows="6" 
			    style="width:60%;" placeholder="Write your annotations here...">
		    </textarea>
		    
		</div>
		<!--<div>
			<input type="reset" value="Reset Fields" style="text-align: right"/>
			<input type="button" value="Reset all fields" onClick="resetForm()"/>
		</div>-->
	    <!--</legend>-->
		<!--<div>	
			<input type="file" name="pic" accept="image/gif"/>
		</div>-->
</fieldset>
<!--<fieldset>
	<a id="a" href="#" title="Click to View/Add Offer" context="loan_approval" name="viewOffer" immediate="true">
			<b>LOAN OFFER</b>
		</a>-->
		<!--<table context="loan_approval" model="offerModel">
			<tbody>
				<td valign="top">
					<tr context="loan_approval">
						<td>Appliance(s)</td>
						<td>
							Offer Model per entity 
								entity.approvedamount
								entity.policy
								entity.provisions
								entity.creditLimit
								entity.increase
								entity.offeralAnnotation
							************************
						</td>
					</tr>
				</td>
			</tbody>
		</table>-->
		<!--<hr/><br/>
</fieldset>-->
