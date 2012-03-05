<script>
	$put(
		'principalBorrowerInfo',
		new function() {
			
			this.branchcode;
			this.objid;	
			this.appid;	
			this.borrower;
			this.selected;

			this.onload = function () {
				//console.log( $.toJSON(this.selected.borrower) );				

				setTimeout( function(){ $('.profile-photo a').lightBox({fixedNavigation:true}); }, 100 );				
			}
		}
	);
</script>
<div style="float: left; margin-right: 20px;">
	<div class="profile-photo">			
		<label context="loan">
			<a href="/clfc-photos/?b=#{selected.branchcode}&a=#{selected.objid}&id=#{selected.borrower.objid}"
				title="#{selected.borrower.lastname}, #{selected.borrower.firstname} (#{selected.borrower.middlename? selected.borrower.middlename: '-'})"> 
				<img src="#{selected.borrower.hasPhoto? '/clfc-photos/?b=' +selected.branchcode+ '&a=' +selected.objid+ '&id=' +selected.borrower.objid : 'img/nophoto.png'}" width="100px" 
					title="#{selected.borrower.lastname}, #{selected.borrower.firstname} (#{selected.borrower.middlename? selected.borrower.middlename: '-'})"/>
			</a>	
		</label>
	</div>
</div>
<table>
	<tr context="loan">
		<th>Name:</th>
		<td>	
			<label context="loan">
				<h3>#{selected.borrower.lastname}, #{selected.borrower.firstname} <i class="personalInfo">(#{selected.borrower.middlename? selected.borrower.middlename: 'Middle Name not specified.'})</i>
			</label>
		</td>
	</tr>	
	<tr context="loan" visibleWhen="#{selected.borrower.birthdate}">
		<th>Birthdate: </th>
		<td><label context="loan" visibleWhen="#{selected.borrower.birthdate}">
			#{selected.borrower.birthdate? selected.borrower.birthdate: '-'}</label>
		</td>
	</tr>
	<tr context="loan">
		<th valign="top">Age: </th>
		<td><label context="loan">#{selected.borrower.age? selected.borrower.age: '-'}</label></td>
	</tr>
	<tr context="loan" visibleWhen="#{selected.borrower.currentaddress.address1}">
		<th valign="top">Address: </th>
		<td valign="top" class="listInfo">
			<label context="loan" class="listInfo">
				#{selected.borrower.currentaddress.address1}
				#{selected.borrower.currentaddress.address2? ', ' + selected.borrower.currentaddress.address2 : ''}, <br/>
				#{selected.borrower.currentaddress.city? ' ' + selected.borrower.currentaddress.city : ''}
				#{selected.borrower.currentaddress.zipcode? ' ' + selected.borrower.currentaddress.zipcode : ''}
				#{selected.borrower.currentaddress.province? ', ' + selected.borrower.currentaddress.province : ''}
				#{selected.borrower.currentaddress.country? ', ' + selected.borrower.currentaddress.country : ''}
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="loan" visibleWhen="#{selected.borrower.residency}">
		<th valign="top">Residency: </th>
		<td valign="top">
			<label context="loan">
				<j class="listInfo2">Type:</j> <b class="black">#{selected.borrower.residency.type? selected.borrower.residency.type: 'Not specified.'}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{selected.borrower.residency.renttype? ' ' + selected.borrower.residency.renttype : '-'}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{selected.borrower.residency.rentamount.formatDecimal()? 'Php' +' '+ selected.borrower.residency.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{selected.borrower.residency.remarks? ' ' + selected.borrower.residency.remarks : ''}</b><br/> 
				<j class="listInfo2">Since:</j> <b class="black">#{selected.borrower.residency.since? ' ' + selected.borrower.residency.since : ''}</b><br/>	
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{selected.borrower.residency.yrsofstay? ' ' + selected.borrower.residency.yrsofstay : ''}</b>
				<br/><br/>				
			</label>
		</td>
	</tr>	
	<tr context="loan" visibleWhen="#{selected.borrower.lotoccupancy}">
		<th valign="top">Lot Occupancy: </th>
		<td valign="top">
			<label context="loan">
				<j class="listInfo2">Type:</j> <b class="black">#{selected.borrower.lotoccupancy.type? selected.borrower.lotoccupancy.type: 'Not specified.'}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{selected.borrower.lotoccupancy.renttype? ' ' + selected.borrower.lotoccupancy.renttype : ''}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{selected.borrower.lotoccupancy.rentamount.formatDecimal()? 'Php' +' '+ selected.borrower.lotoccupancy.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{selected.borrower.lotoccupancy.remarks? ' ' + selected.borrower.lotoccupancy.remarks : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{selected.borrower.lotoccupancy.since? ' ' + selected.borrower.lotoccupancy.since : ''}</b><br/>
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{selected.borrower.lotoccupancy.yrsofstay? ' ' + selected.borrower.lotoccupancy.yrsofstay : ''}</b>
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="loan">
		<th valign="top">Citizenship: </th>
		<td><label context="loan">#{selected.borrower.citizenship? selected.borrower.citizenship: '-'}</label></td>
	</tr>	
	<tr context="loan">
		<th valign="top">Phone/Mobile No.: </th>
		<td><label context="loan">#{selected.borrower.phone? selected.borrower.phone: '-'}</label></td>
	</tr>
</table>