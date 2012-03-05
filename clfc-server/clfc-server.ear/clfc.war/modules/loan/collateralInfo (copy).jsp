<script>
	$put(
		"collateralInfo",
		new function() {
		
			this.application;
			this.collateralInfo;
			this.selectedObj = {};
			this.entity;
			
			//this.selected is the selected object in loan controller
			this.selected;

			this.onload = function () {
				this.selected = $ctx('loan').selected;
			};
			
			this.doOk = function() {
				if (!confirm('You are about to add this collateral(s). Proceed?')) return;{
					if( this.closeHandler ) this.closeHandler();
						return "_close";	
				}
				//if( !this.entity )return "_close";{
			};

			this.checkAllApp = function() {
				if( this.selectHandler ) this.selectHandler();
				//name="selected.appliancelist[#{stat.index}].must"
				//return "_close";

				//for( i = 0; i < selected.appliancelist.length; ++i ){
					
				//}
			};
	
			/*	
			<!-- Begin
			function checkAll(field)
			{
			for (i = 0; i < field.length; i++)
				field[i].checked = true ;
			}

			function uncheckAll(field)
			{
			for (i = 0; i < field.length; i++)
				field[i].checked = false ;
			}
			//  End -->

			<form name="myform" action="checkboxes.asp" method="post">
				<b>Your Favorite Scripts & Languages</b><br>
				<input type="checkbox" name="list" value="1">Java<br>
				<input type="checkbox" name="list" value="2">Javascript<br>
				<input type="checkbox" name="list" value="3">Active Server Pages<br>
				<input type="checkbox" name="list" value="4">HTML<br>
				<input type="checkbox" name="list" value="5">SQL<br>

				<input type="button" name="CheckAll" value="Check All"
					onClick="checkAll(document.myform.list)">
				<input type="button" name="UnCheckAll" value="Uncheck All"
					onClick="uncheckAll(document.myform.list)">
				<br>
			</form>
			*/

			this.unCheckAllApp = function() {
				if( this.deselectHandler ) this.deselectHandler();
				//return "_close";
			};

			this.checkAllVehicle = function() {
				if( this.selectHandler ) this.selectHandler();
				//name="selected.appliancelist[#{stat.index}].must"
				//return "_close";

				//for( i = 0; i < selected.appliancelist.length; ++i ){
					
				//}
			};
	
			this.unCheckAllVehicle = function() {
				if( this.deselectHandler ) this.deselectHandler();
				//return "_close";
			};

			this.checkAllProperty = function() {
				if( this.selectHandler ) this.selectHandler();
				//name="selected.appliancelist[#{stat.index}].must"
				//return "_close";

				//for( i = 0; i < selected.appliancelist.length; ++i ){
					
				//}
			};
	
			this.unCheckAllProperty = function() {
				if( this.deselectHandler ) this.deselectHandler();
				//return "_close";
			};


			this.checkAllOthers = function() {
				if( this.selectHandler ) this.selectHandler();
				//name="selected.appliancelist[#{stat.index}].must"
				//return "_close";

				//for( i = 0; i < selected.appliancelist.length; ++i ){
					
				//}
			};
	
			this.unCheckAllOthers = function() {
				if( this.deselectHandler ) this.deselectHandler();
				//return "_close";
			};
	
			this.viewAppliance = function() {
				var p = new PopupOpener('loan:assetAppliance', {assetAppliance: this.selectedObj.appliance, application: this.selected});
				p.title = 'Appliance Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			this.viewVehicle = function() {
				var p = new PopupOpener('loan:assetVehicle', {assetVehicle: this.selectedObj.vehicle, application: this.selected});
				p.title = 'Vehicle Collateral Info';
				p.options = {width: 500, height: 550};
				return p;
			};

			this.viewProperty = function() {
				var p = new PopupOpener('loan:assetProperty', {assetProperty: this.selectedObj.property, application: this.selected});
				p.title = 'Property Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			this.viewOtherCollateral = function() {
				var p = new PopupOpener('loan:assetOthers', {assetOthers: this.selectedObj.others, application: this.selected});
				p.title = 'Other Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			//Select all in check box	
			this.checkedAll = function() {
				for(var i=0; i<this.selected.length; ++i)
					this.selected[i].must = true;
					//appliancelist[i].must = true;
					//name="selected.appliancelist[#{stat.index}].must"
			}
			this.uncheckedAll = function() {
				for(var i=0; i<selected.length; ++i)
					selected[i].must = true;
					//appliancelist[i].must = true;
					//name="selected.appliancelist[#{stat.index}].must"
			}
		}
	);	
</script>
<div class="box">
	COLLATERAL(S) INFORMATION
</div>
<div>
	<i context="collateralInfo" visibleWhen="#{!selected.appliancelist.length > 0}" class="maroon">
		No appliance(s) specified.<br/><br/>
	</i>
	<j context="collateralInfo" visibleWhen="#{selected.appliancelist.length >0}">
		<b class="maroon">Appliance(s)</b> ( Total Appraised = <b>Php</b> <b class="teal">
			<label context="collateralInfo">#{appraisedvalue.formatDecimal()}</label></b> )
	</j>
</div>
<table context="collateralInfo" items="selected.appliancelist" varStatus="stat"
	   visibleWhen="#{selected.appliancelist.length >0}" name="selectedObj.appliance"
	   		class="grid" width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="5%"> <!--for the check box header-->
				<input type="checkbox" context="collateralInfo" name="selected.appliancelist[#{stat.index}].selected""/>
				<!--<input type="checkbox" r:context="profile" r:name="list[#{stat.index}].selected"/>-->
			</th>
			<th width="25%">Appliance</th>
			<th width="35%">Brand <b>/</b> Serial# <b>/</b> Model#</th>
			<th width="15%">Appraised Value</th>
			<th width="25%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">
				<input type="checkbox" id="checkAllApp" context="collateralInfo" 
						name="selected.appliancelist[#{stat.index}].must"/>
				<!--<input type="checkbox" r:context="profile" r:name="list[#{stat.index}].selected"/>-->
				
					<!--name="selected.appliancelist[#{stat.index}].must" depends="sourceSelectAll"/>-->
				<!--
				<table context="basket" items="source" width="100%" varStatus="stat" varName="item">
					<thead>
						<tr>
							<td style="background-color:lightgrey;">
								<input type="checkbox" context="basket" name="sourceSelectAll"/>
								Select Courses below:
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<input type="checkbox" context="basket" name="source[#{stat.index}].selected" depends="sourceSelectAll"/>
								#{item.course}
							</td>
						</tr>
					</tbody>
				</table>
				-->				
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}"  context="collateralInfo" action="viewAppliance">
				<a>#{type}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}"  context="collateralInfo" action="viewAppliance">
				<a>#{brand} <b>/</b> #{serial} <b>/</b> #{model}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewAppliance">
				<a><b class="gray">Php</b> <j class="teal2">#{appraisedvalue.formatDecimal()}</j></a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewAppliance">
				<a>#{remarks? remarks:'-'}</a>
			</td>
		</tr>
	</tbody>
</table>
<br/>
<div>
	<i context="collateralInfo" visibleWhen="#{!selected.vehiclelist.length > 0}" class="maroon">
		No vehicle(s) specified.<br/><br/>
	</i>
	<j context="collateralInfo" visibleWhen="#{selected.vehiclelist.length >0}">
		<b class="maroon">Vehicle(s)</b> ( Total Appraised = <b>Php</b> <b class="teal">
		<label context="collateralInfo">#{appraisedvalue.formatDecimal()}</label></b> )
	</j>
</div>
<table context="collateralInfo" items="selected.vehiclelist" varStatus="stat" 
		visibleWhen="#{selected.vehiclelist.length >0}" name="selectedObj.vehicle"		
			class="grid" width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="5%"> <!--for the check box header-->
				<input type="checkbox" id="checkAllVehicle" context="collateralInfo"/>
			</th> 
			<th width="20%">Make</th>
			<th width="20%">Model</th>
			<th width="30%">Type <b>/</b> Engine# <b>/</b> Chassis#</th>
			<th width="10%">Plate#</th>
			<th width="20%">Registration#</th>
			<th width="20%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">
				<input type="checkbox" id="checkAllVehicle" context="collateralInfo" name="selected.vehiclelist[#{stat.index}].must"/>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{make}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{model}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{type} <b>/</b> #{engineno} <b>/</b> #{chassisno}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{plateno}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{regno}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewVehicle">
				<a>#{remarks? remarks: '-'}</a>
			</td>
		</tr>
	</tbody>
</table>
<br/>
<div>
	<i context="collateralInfo" visibleWhen="#{!selected.propertylist.length > 0}" class="maroon">
		No property(s) specified.<br/><br/>
	</i>
	<label context="collateralInfo" visibleWhen="#{selected.propertylist.length >0}">
		<b class="maroon">Property/Properties</b> ( Total Appraised = <b>Php</b> <b class="teal"> 
		<label context="collateralInfo">#{appraisedvalue.formatDecimal()}</label></b> )
	</label>
</div>
<table context="collateralInfo" items="selected.propertylist" varStatus="stat" 
		class="grid" width="100%" cellspacing="0"
			visibleWhen="#{selected.propertylist.length >0}" name="selectedObj.property">
	<thead>
		<tr>
			<th width="5%"> <!--For the check box header-->
				<input type="checkbox" id="checkAllProperty" context="collateralInfo"/>
			</th> 
			<th>Land/Building</th>
			<th>Location</th>
			<th>Zonal Value</th>
			<th>Appraised Value</th>
			<th>Area</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">
				<input type="checkbox" id="checkAllProperty" context="collateralInfo" 
					name="selected.propertylist[#{stat.index}].must"/>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewProperty">
				<a>#{rpu}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewProperty">
				<a>#{location}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" visibleWhen="#{zonalvalue}" 
					context="collateralInfo" action="viewProperty">
				<a><b class="gray">Php</b> <j class="teal2">#{zonalvalue.formatDecimal()}</j></a>
			</td>	
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewProperty">
				<a><b class="gray">Php</b> <j class="teal2">#{appraisedvalue.formatDecimal()}</j></a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewProperty">
				<a>#{area.formatDecimal()} #{uom}</a>
			</td>
		</tr>
	</tbody>
</table>
<br/>
<div>
	<i context="collateralInfo" visibleWhen="#{!selected.otherslist.length > 0}" class="maroon">
		No other collateral(s) specified.<br/><br/>
	</i>
	<j context="collateralInfo" visibleWhen="#{selected.otherslist.length >0}">
		<b class="maroon">Other Collateral(s)</b> ( Total Appraised = <b>Php</b> <b class="teal">
		<label context="collateralInfo">#{appraisedvalue.formatDecimal()}</label></b> )
	</j>
</div>
<table context="collateralInfo" items="selected.otherslist" varStatus="stat" 
		class="grid" width="100%" cellspacing="0"
			visibleWhen="#{selected.otherslist.length >0}" name="selectedObj.others">
	<thead>
		<tr>
			<th width="5%"> <!--For the check box header-->								
				<input type="checkbox" id="checkAllOthers" context="collateralInfo"/>
			</th>
			<th width="20%">Name</th>
			<th width="20%">Classification</th>
			<th width="10%">Use</th>
			<th width="15%">Date Acquired</th>
			<th width="15%">Market/Appraised Value</th>
			<th width="20%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">
				<input type="checkbox" id="checkAllOthers" context="collateralInfo" name="selected.otherslist[#{stat.index}].must"/>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a>#{subject}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a>#{modeOfAcquisition}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a>#{use}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a>#{dateAcquired}</a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a><b class="gray">Php</b> <b class="teal">#{appraisedvalue.formatDecimal()}</b></a>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="collateralInfo" action="viewOtherCollateral">
				<a>#{remarks.toUpperCase()}</a>
			</td>
		</tr>
	</tbody>
</table>
<br/>
<div>	
	<j class="black2">Additional Collateral(s) to be offered.</j> <b class="maroon">(Pls. specify if any)</b>
	<br/>
	<textarea context="loan_approval" id="additionalCollateral"
		name="entity.additionalCollateral" rows="5" style="width:50%;" placeholder="Specify your additional collateral(s) here...">
		<!--name="entity.additionalcollateral"   additionalCollateral[#{stat.index}].must-->
	</textarea>	
</div>
<br/>
<table>
	<div style="text-align:left">
		Total Collateral Appraisal Value : 
		<label context="collateralInfo" visibleWhen="#{selected.totalCAV}"><b>Php</b> <b class="teal">
			<u>#{selected.totalCAV.formatDecimal()}</u></b></label>
		<label context="collateralInfo" visibleWhen="#{!selected.totalCAV}" class="maroon"></u>0.00</u></label>
	</div>
</table>
<div class="dialog-buttons" alignment="right">
	<input type="button"  context="collateralInfo" name="doOk" value="Ok and continue"
		onClick="confirm( 'Continue adding collateral(s). Proceed?' )"/>
</div>
