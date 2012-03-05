<script>
	$put(
		'spouseInfo',
		new function() {
			
			this.branchcode;
			this.objid;	
			this.appid;	
			this.spouse;

			this.onload = function () {
				console.log( $.toJSON(this.spouse) );				

				setTimeout( function(){ $('.profile-photo2 a').lightBox({fixedNavigation:true}); }, 100 );				
			}
		}
	);
</script>
<div style="float: left; margin-right: 20px;">
	<div class="profile-photo2">			
		<label context="spouseInfo">
			<a href="/clfc-photos/?b=#{branchcode}&a=#{objid}&id=#{spouse.objid}"
				title="#{spouse.lastname}, #{spouse.firstname} (#{spouse.middlename? spouse.middlename: '-'})">
				<img src="#{spouse.hasPhoto? '/clfc-photos/?b=' +branchcode+ '&a=' +objid+ '&id=' +spouse.objid : 'img/nophoto.png'}" width="150px"
					title="#{spouse.lastname}, #{spouse.firstname} (#{spouse.middlename? spouse.middlename: '-'})"/> 
			</a>	
		</label>
	</div>	
</div>
<table>
	<tr context="spouseInfo">
		<th>Name:</th>
		<td>	
			<label context="spouseInfo">
				<h3>#{spouse.lastname}, #{spouse.firstname} <i class="personalInfo">(#{spouse.middlename? spouse.middlename: 'Middle Name not specified.'})</i>
			</label>
		</td>
	</tr>	
	<tr context="spouseInfo" visibleWhen="#{spouse.birthdate}">
		<th>Birthdate: </th>
		<td><label context="spouseInfo" visibleWhen="#{spouse.birthdate}">
			#{spouse.birthdate? spouse.birthdate: '-'}</label>
		</td>
	</tr>
	<tr context="spouseInfo">
		<th valign="top">Age: </th>
		<td><label context="spouseInfo">#{spouse.age? spouse.age: '-'}</label></td>
	</tr>
	<tr context="spouseInfo" visibleWhen="#{spouse.currentaddress.address1}">
		<th valign="top">Address: </th>
		<td valign="top" class="listInfo">
			<label context="spouseInfo" class="listInfo">
				#{spouse.currentaddress.address1}
				#{spouse.currentaddress.address2? ', ' + spouse.currentaddress.address2 : ''}, <br/>
				#{spouse.currentaddress.city? ' ' + spouse.currentaddress.city : ''}
				#{spouse.currentaddress.zipcode? ' ' + spouse.currentaddress.zipcode : ''}
				#{spouse.currentaddress.province? ', ' + spouse.currentaddress.province : ''}
				#{spouse.currentaddress.country? ', ' + spouse.currentaddress.country : ''}
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="spouseInfo" visibleWhen="#{spouse.residency}">
		<th valign="top">Residency: </th>
		<td valign="top">
			<label context="spouseInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{spouse.residency.type? spouse.residency.type: 'Not specified.'}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{spouse.residency.renttype? ' ' + spouse.residency.renttype : '-'}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{spouse.residency.rentamount.formatDecimal()? 'Php' +' '+ spouse.residency.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{spouse.residency.remarks? ' ' + spouse.residency.remarks : ''}</b><br/> 
				<j class="listInfo2">Since:</j> <b class="black">#{spouse.residency.since? ' ' + spouse.residency.since : ''}</b><br/>	
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{spouse.residency.yrsofstay? ' ' + spouse.residency.yrsofstay : ''}</b>
				<br/><br/>				
			</label>
		</td>
	</tr>	
	<tr context="spouseInfo" visibleWhen="#{spouse.lotoccupancy}">
		<th valign="top">Lot Occupancy: </th>
		<td valign="top">
			<label context="spouseInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{spouse.lotoccupancy.type? spouse.lotoccupancy.type: 'Not specified.'}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{spouse.lotoccupancy.renttype? ' ' + spouse.lotoccupancy.renttype : ''}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{spouse.lotoccupancy.rentamount.formatDecimal()? 'Php' +' '+ spouse.lotoccupancy.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{spouse.lotoccupancy.remarks? ' ' + spouse.lotoccupancy.remarks : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{spouse.lotoccupancy.since? ' ' + spouse.lotoccupancy.since : ''}</b><br/>
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{spouse.lotoccupancy.yrsofstay? ' ' + spouse.lotoccupancy.yrsofstay : ''}</b>
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="spouseInfo">
		<th valign="top">Citizenship: </th>
		<td><label context="spouseInfo">#{spouse.citizenship? spouse.citizenship: '-'}</label></td>
	</tr>	
	<tr context="spouseInfo">
		<th valign="top">Phone/Mobile No.: </th>
		<td><label context="spouseInfo">#{spouse.phone? spouse.phone: '-'}</label></td>
	</tr>
	<tr context="loan">
		<th>
			<label context="spouseInfo"><b>#{spouse.relation.toLowerCase()? spouse.relation.toLowerCase(): 'Spouse'} of</b></label>
		</th>
		<td>
			<label context="loan">
				<b class="borrowerInfo">
					<b context="loan" class="borrowerInfo">
					    #{selected.borrower.lastname}, #{selected.borrower.firstname}
					</b>
					<i context="loan" class="personalInfo"><b> 
						(#{selected.borrower.middlename? selected.borrower.middlename: '-'})
					</i>
				</b>
			</label>
		</td>
	</tr>
</table>
