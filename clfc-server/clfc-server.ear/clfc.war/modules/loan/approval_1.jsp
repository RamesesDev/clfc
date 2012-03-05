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

				console.log( entity.offeredamount );
				console.log( entity.policy );
				console.log( entity.provisions );
				console.log( entity.creditLimit );
				console.log( entity.increase );
				console.log( entity.offeralAnnotation );

				//_this.entity = [];

				alert("entity");
				_this.offerModel.load();							
			};
			
			var collateralHandler = function(){
				_this.mustList = [];

				var app = $ctx('loan').selected;
				//var add = $ctx('loan').selected;
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
				
				alert( "vehicle of" + app.vehiclelist);
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

			// Mouse Click event on Collateral(s) Attached
			/*$("#a").hover(
				function(){
					//$("#z").css('display','block');
					$("#yz").fadeIn();
				},
				function(){
					//$("#z").css('display','none');
					$("#yz").fadeOut();
				}
			);
			
			//Mouse Focus event on Collateral(s) Attached
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
			});*/
			
			this.close = function() {
				return "_close";
			};
			
			this.approve = function()
			{
				if( !this.info.approvedamount ){
					throw new Error('Please input an amount to approved.');
				}else if( this.info.approvedamount >= 1000001 ){
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

					//if (!confirm('Sure najud ka?. Final Answer?')){
					//} return;						
					//alert("OK raka? pataka rman kag approve!!! Disqualified ka ron..");	
				}

				if( !this.info.increase ){
					throw new Error('Please specify the increase amount.');
				}else if( this.info.increase >= 1000001 ){					
					throw new Error( 'Increase amount has exceeded.' );
				}

				if (!confirm('You are about to approve this transaction. Proceed?')) return;

				if( !this.annotation || !this.annotation.trim() )
					throw new Error('Please specify the approval annotation.');

				for(var i in this.info) this.entity[i] = this.info[i];
				
				if (this.selectHandler) this.selectHandler(this.annotation);
					return "_close";
			};
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

		/*
		function checkNum(data) { // checks if all characters 
			var valid = "0123456789."; // are valid numbers or a "."
			var ok = 1; var checktemp;
			for (var i=0; i<data.length; i++) {
				checktemp = "" + data.substring(i, i+1);
				if (valid.indexOf(checktemp) == "-1") return 0; 
			}
			return 1;
		}
		function dollarAmount(form, field) { // idea by David Turley
			alert('musta ang jboss nato mo?');
			Num = "" + eval("document." + form + "." + field + ".value");
			dec = Num.indexOf(".");
			end = ((dec > -1) ? "" + Num.substring(dec,Num.length) : ".00");
			Num = "" + parseInt(Num);</data.length;>
			var temp1 = "";
			var temp2 = "";
			if (checkNum(Num) == 0) {
				alert("This does not appear to be a valid number. Please try again.");
			}
			else { 
				if (end.length == 2) end += "0";
				if (end.length == 1) end += "00";
				if (end == "") end += ".00";
				var count = 0;
				for (var k = Num.length-1; k >= 0; k--) {
					var oneChar = Num.charAt(k);
					if (count == 3) {
						temp1 += ",";
						temp1 += oneChar;
						count = 1;
						continue;
					}
					else {
					temp1 += oneChar;
					count ++;
					}
				}
				for (var k = temp1.length-1; k >= 0; k--) {
				var oneChar = temp1.charAt(k);
				temp2 += oneChar;
				}
				temp2 = "$" + temp2 + end;
				eval("document." + form + "." + field + ".value = '" + temp2 + "';");
			}
		}*/
		
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
	<legend>LOAN APPROVAL</legend>
	<div>
		<table>
			<td valign="top" width="100px">
				<tr>
					<!--<td>Applied Amount:</td>-->
					<td colspan="5">
						<h3>Applied Amount: 
							<label context="loan_approval">
								<b>Php</b> <b><u><font color="red">#{info.loanamount.formatDecimal()}</font></u></b>
							</label>
						</h3>
						<br/>
						Approved Amount:
							<label context="loan_approval">
								<input type="text" id="approvedamount" 
									onkeypress="return isNumberKey(event)" context="loan_approval" name="info.approvedamount"
								 	required="true" maxlength="7" style="text-align: right" jQuery("idspolicy").focus();/>
								<!--<span>Approved Amount</span>-->
							</label>
							 
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
					<td valign="top">
						A.) Policy
							<label context="loan_approval">
								<input id="policy" onkeypress="return isNumberKey(event)"
									type="text" context="loan_approval" name="info.policy" required="true" 
										immediate="true" maxlength="2" style="text-align: center"/>
							</label>
						B.) Provisions
							<label context="loan_approval">
								<input id="provisions" onkeypress="return isNumberKey(event)"
									type="text" context="loan_approval" required="true" immediate="true" 
										name="info.provisions" maxlength="2" style="text-align: center"/>
							</label>
					</td>
				</tr>
				<tr>
					<td>Credit Limit:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="creditLimit" onkeypress="return isNumberKey(event)"
								type="text" context="loan_approval" name="info.creditLimit" required="true"
									immediate="true" maxlength="7" style="text-align: right"/>
						</label>
					</td>
				</tr>
				<tr>
					<td>Increase:</td>
					<td colspan="3">
						<label context="loan_approval">
							<input id="increase" onkeypress="return isNumberKey(event)"
								type="text" context="loan_approval" name="info.increase" required="true" 
									immediate="true" maxlength="7" style="text-align: right"/>
						</label>
					</td>
				</tr>
			</td>	
		</table><br/>
		<div id="xyz">
			<a href="#" context="loan_approval" name="viewCollateral" title="Click to View/Add Must Collateral(s)" immediate="true">
				<b class="green">Must/Required Collateral(s)</b>
			</a><br/>
			<table context="loan_approval" model="mustModel">
				<tbody>
					<td valign="top">
						<tr context="loan_approval">
							<!--<td>Appliance(s)</td>-->
							<td>
								#{type? type: ' '} - #{brand? brand:' '} ( #{model? model:' '} )  
								#{plateno? plateno:' '}
								#{rpu? rpu:' '} - #{location? location:' '} / #{area? area:' '} #{uom? uom:' '}
								#{subject? subject:' '} - #{registeredName? registeredName:' '}
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
		<br/>
			Annotation
		<br/>
		<textarea context="loan_approval" name="annotation" rows="6" style="width:60%;" 
				placeholder="Write your annotations here...">
		</textarea>
		<div class="dialog-buttons">
			<input type="button"  context="loan_approval" name="approve" value="Approve" immediate="true"/>
			<input type="button"  context="loan_approval" name="close"   value="Cancel" immediate="true"/>
		</div>
		<br>
		<a id="a" href="#" title="Click to View/Add Offer" context="loan_approval" name="viewOffer" immediate="true">
			<b>LOAN OFFER</b>
		</a>
		<table context="loan_approval" model="offerModel">
			<tbody>
				<td valign="top">
					<tr context="loan_approval">
						<td>Appliance(s)</td>
						<td>
							<!-- Offer Model per entity -->
								entity.approvedamount
								entity.policy
								entity.provisions
								entity.creditLimit
								entity.increase
								entity.offeralAnnotation
							<!--************************-->
						</td>
					</tr>
				</td>
			</tbody>
		</table>
	</div>
</fieldset>
<fieldset>
	<legend>
		<a id="a" href="#" title="Click to View/Add Offer" context="loan_approval" name="viewOffer" immediate="true">
			<b>LOAN OFFER</b>
		</a>
		<table context="loan_approval" model="offerModel">
			<tbody>
				<td valign="top">
					<tr context="loan_approval">
						<td>Appliance(s)</td>
						<td>
							<!-- Offer Model per entity -->
								entity.approvedamount
								entity.policy
								entity.provisions
								entity.creditLimit
								entity.increase
								entity.offeralAnnotation
							<!--************************-->
						</td>
					</tr>
				</td>
			</tbody>
		</table>
	</legend>
</fieldset>
