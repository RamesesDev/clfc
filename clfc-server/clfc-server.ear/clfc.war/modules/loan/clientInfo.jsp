<script>
	$put(
		"clientInfo",
		new function() {

			this.branchcode;
			this.appid;	
			this.client;
			this.selectedObj = {};

			this.onload = function () {
				console.log( $.toJSON(this.client) );	
				
				setTimeout( function(){ $('.profile-photo3 a').lightBox({fixedNavigation:true}); }, 100 );
				setTimeout( function(){ $('.profile-photo4 a').lightBox({fixedNavigation:true}); }, 100 );								
			}
			
			this.viewBusiness = function() {
				var p = new PopupOpener('loan:businessInfo', {business: this.selectedObj.business});
				p.title = 'Business Information';
				p.options = {width: 600, height: 570};
				return p;
			}
		}
	);
</script>
<div style="float: left; margin-right: 20px;">
	<div class="profile-photo3">			
		<label context="clientInfo">
			<a href="/clfc-photos/?b=#{branchcode}&a=#{appid}&id=#{client.objid}"
				title="#{client.lastname}, #{client.firstname} (#{client.middlename? client.middlename: '-'})"> 
				<img class="profile" src="#{client.hasPhoto? '/clfc-photos/?b=' +branchcode+ '&a=' +appid+ '&id=' +client.objid : 'img/nophoto.png'}" width="150px" 
					title="#{client.lastname}, #{client.firstname} (#{client.middlename? client.middlename: '-'})"/>
			</a>	
		</label>
	</div>
</div>
<table>
	<tr>
		<th>Name:</th>
		<td>
			<label context="clientInfo">
				<h3>#{client.lastname}, #{client.firstname} <i class="personalInfo">(#{client.middlename? client.middlename: 'Middle Name not specified.'})</i>
			</label>		
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.birthdate}">
		<th>Birthdate: </th>
		<td>
			<label context="clientInfo" visibleWhen="#{!client.birthdate}" class="personalInfo"><b><i>Not specified.</i></b></label>				
			<label context="clientInfo" visibleWhen="#{client.birthdate}">#{client.birthdate}</label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.age}">
		<th>Age: </th>
		<td>
			<label context="clientInfo" visibleWhen="#{!client.age}" class="personalInfo"><b><i>Not specified.</i></b></label>
			<label context="clientInfo" visibleWhen="#{client.age}">#{client.age} yrs. old</label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.homeaddress.address1}">
		<th valign="top">Home Address: </th>
		<td valign="top" class="listInfo">
			<label context="clientInfo" class="personalInfo" visibleWhen="#{!client.homeaddress}"><i><b>Not specified.<b></i></label>	
			<label context="clientInfo" class="listInfo" visibleWhen="#{client.homeaddress}">
				#{client.homeaddress.address1}
				#{client.homeaddress.address2? ', ' + client.homeaddress.address2 : ''}
				#{client.homeaddress.city? ', ' + client.homeaddress.city : ''}
				#{client.homeaddress.zipcode? ' ' + client.homeaddress.zipcode : ''}
				#{client.homeaddress.province? ', ' + client.homeaddress.province : ''}
				#{client.homeaddress.country? ', ' + client.homeaddress.country : ''}
			</label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.currentaddress.address1}">
		<th valign="top">Current Address: </th>
		<td valign="top" class="listInfo">
			<label context="clientInfo" class="personalInfo" visibleWhen="#{!client.currentaddress}"><i><b>Not specified.<b></i></label>	
			<label context="clientInfo" class="listInfo" visibleWhen="#{client.currentaddress}">
				#{client.currentaddress.address1}
				#{client.currentaddress.address2? ', ' + client.currentaddress.address2 : ''}
				#{client.currentaddress.city? ', ' + client.currentaddress.city : ''}
				#{client.currentaddress.zipcode? ' ' + client.currentaddress.zipcode : ''}
				#{client.currentaddress.province? ', ' + client.currentaddress.province : ''}
				#{client.currentaddress.country? ', ' + client.currentaddress.country : ''}
			</label>
		</td>
	</tr>	
	<tr context="clientInfo" visibleWhen="#{client.previousaddress.address1}"> 
		<th valign="top">Previous Address: </th>		
		<td valign="top" class="listInfo">			
			<label context="clientInfo" class="personalInfo" visibleWhen="#{!client.previousaddress}"><i><b>Not specified.<b></i></label>
			<label context="clientInfo" class="listInfo" visibleWhen="#{client.previousaddress}">
				#{client.previousaddress.address1}
				#{client.previousaddress.address2? ', ' + client.previousaddress.address2 : ''}
				#{client.previousaddress.city? ', ' + client.previousaddress.city : ''}
				#{client.previousaddress.zipcode? ' ' + client.previousaddress.zipcode : ''}
				#{client.previousaddress.province? ', ' + client.previousaddress.province : ''}
				#{client.previousaddress.country? ', ' + client.previousaddress.country : ''}
			</label>
		</td>
	</tr>	
	<tr context="clientInfo" visibleWhen="#{client.phone}">
		<th>Phone No.: </th>
		<td>
			<label context="clientInfo">#{client.phone}</label>
		</td>
	</tr>
	<tr context="clientInfo">
		<th>Marital Status: </th>
		<td>
			<label context="clientInfo" visibleWhen="#{!client.civilstat}" class="personalInfo"><b><i>Not specified.</i></b></label>
			<label context="clientInfo" visibleWhen="#{client.civilstat}">#{client.civilstat}</label>
		</td>
	</tr>
	<tr context="clientInfo"> 
		<th valign="top">Residency: </th>
		<td valign="top">
			<label context="clientInfo" class="personalInfo" visibleWhen="#{!client.residency.type}"><i><b>Not specified.<b></i></label>			
     		<label context="clientInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{client.residency.type? client.residency.type: ' '}</b></br>	
				<j class="listInfo2" visibleWhen="#{client.residency.renttype}">Rent Type:</j> <b class="black">#{client.residency.renttype? '  ' + client.residency.renttype : ''}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{client.residency.rentamount.formatDecimal()? 'Php' +' '+ client.residency.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{client.residency.remarks? ' ' + client.residency.remarks : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{client.residency.since? ' ' + client.residency.since : ''}</b><br/>
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{client.residency.yrsofstay? ' ' + client.residency.yrsofstay : ''}</b>
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="clientInfo"> 
		<th valign="top">Lot Occupancy: </th>
		<td valign="top">
			<label context="clientInfo" class="personalInfo" visibleWhen="#{!client.lotoccupancy.type}"><i><b>Not specified.<b></i></label>
			<label context="clientInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{client.lotoccupancy.type? client.lotoccupancy.type: ' '}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{client.lotoccupancy.renttype? ' ' + client.lotoccupancy.renttype : ''}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{client.lotoccupancy.rentamount.formatDecimal()? 'Php' +' '+ client.lotoccupancy.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{client.lotoccupancy.remarks? ' ' + client.lotoccupancy.remarks : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{client.lotoccupancy.since? ' ' + client.lotoccupancy.since : ''}</b><br/>
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{client.lotoccupancy.yrsofstay? ' ' + client.lotoccupancy.yrsofstay : ''}</b>
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="clientInfo"> 
		<th>Citizenship: </th>
		<td>
			<label context="clientInfo" visibleWhen="#{!client.citizenship}" class="personalInfo"><b><i>Not specified.</i></b></label>
			<label context="clientInfo" visibleWhen="#{client.citizenship}">#{client.citizenship}</label>
		</td>
	</tr>
</table>
<table>
	<tr context="clientInfo">
		<th>Relationship to the Principal Borrower: </th>
		<td>
			<label context="clientInfo" visibleWhen="#{!client.relation}"><b class="personalInfo"><i>Not specified.</i></b></label>				
			<label context="clientInfo" visibleWhen="#{client.relation}"><b class="fuchsia">#{client.relation}</b></label>
		</td>
	</tr>
</table>
<hr/>

<div class="box">
	BUSINESS INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.mainBusinessList.length>0}" class="maroon">
	No Business specified.<br/>
</i>	
<table context="clientInfo" name="selectedObj.business" items="client.mainBusinessList" 
       varName="item" visibleWhen="#{client.mainBusinessList.length>0}"
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<td>Business Name</td>
			<td>Address</td>
			<td>Bus. Type</td>
			<td>Stall size</td>
		</tr>
	</thead>
    <tbody class="selectable">
    	<tr context="clientInfo" action="viewBusiness">
    		<td>#{item.tradename}</td>
    		<td>#{item.address}</td>
    		<td>#{item.businesstype.type? item.businesstype.type: '-'}</td>
    		<td>#{item.businesstype.stallsize? item.businesstype.stallsize: '-'}</td>
    	</tr>
    </tbody>
</table>

<div class="box">
	OTHER SOURCES OF INCOME
</div>
<i context="clientInfo" visibleWhen="#{!client.otherSourcesOfIncomeList.length>0}" class="maroon">
	No Other Source of Income specified <br/>
</i>
<table context="clientInfo" items="client.otherSourcesOfIncomeList" name="selectedObj.otherLending"
       visibleWhen="#{client.otherSourcesOfIncomeList.length>0}"
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
			 <td>#{grossNetIncome.formatDecimal()? grossNetIncome.formatDecimal(): '-'}</td>
			 <td>#{remarks}</td>
		</tr>
	</tbody>
</table>

<div class="box">
	PROFESSIONAL BACKGROUND
</div>
<i context="clientInfo" visibleWhen="#{!client.profBackgroundList.length>0}" class="maroon">
	No Professional Background specified.<br/>
</i>
<table context="clientInfo" items="client.profBackgroundList" width="100%" visibleWhen="#{client.profBackgroundList.length>0}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>School Attended</th>
			<th>Profession</th>
			<th>Date Graduated</th>
			<th>Others/Remarks</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{schoolAttended}</td>
			 <td>#{profession? profession : '-'}</td>
			 <td>#{dateGraduated? dateGraduated : '-'}</td>
			 <td>#{othersSpecs? othersSpecs : '-'}</td>
		</tr>
	</tbody>
</table>

<div class="box">
	EMPLOYMENT RECORD
</div>
<i context="clientInfo" visibleWhen="#{!client.employmentList.length>0}" class="maroon">
	No Employment specified.<br/>
</i>
<table context="clientInfo" items="client.employmentList" width="100%" visibleWhen="#{client.employmentList.length>0}"
       border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<td>Employer/Address/Tel. No.</td>
			<td>Position</td>
			<td>Years</td>
			<td align="center">Salary</td>
			<td align="center">Status</td>
		</tr>
	</thead>
    <tbody>
    	<tr>
    		<td>
    			<b>#{employer}</b><br/>
    			<i>#{address} #{telno? '<br/>' + telno : ''}</i>
    		</td>
    		<td>#{position}</td>
    		<td>#{years}</td>
    		<td align="center" context="clientInfo" visibleWhen="#{salary}">#{salary}</td>
    		<td align="center" context="clientInfo" visibleWhen="#{!salary}"><b class="gray">Php</b> <j class="teal2">0.00</j></td>
    		<td align="center">#{status}</td>
    	</tr>
    </tbody>
</table>

<div class="box">
	PARENTS INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.fathersName && !client.mothersName}" class="maroon">
	No Parents specified.<br/>
</i>
<table context="clientInfo" visibleWhen="#{client.fathersName && client.mothersName}" model="listModel" width="100%" border="0" cellspacing="0" class="grid">
	<tr context="clientInfo" visibleWhen="#{client.fathersName}">
		<td width="130"><b>Father's Name: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.fathersName? client.fathersName: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.fathersBDate}">
		<td><b>Birthdate: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.fathersBDate? client.fathersBDate: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.fathersAge}">
		<td><b>Age: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.fathersAge} yrs. old</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.mothersName}">
		<td><b>Mother's Name: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.mothersName? client.mothersName:'-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.mothersBDate}">
		<td><b>Birthdate: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.mothersBDate? client.mothersBDate: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.mothersAge}">
		<td><b>Age: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.mothersAge} yrs. old</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.parentsAddress}">
		<td><b>Address: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.parentsAddress? client.parentsAddress: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.othersSpecs}">
		<td><b>Remarks / Others: </b></th>
		<td>
			<label context="clientInfo"><b>#{client.othersSpecs? client.othersSpecs: '-'}</b></label>
		</td>
	</tr>
</table>

<div class="box">
	SIBLINGS INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.siblingsList.length>0}" class="maroon">
	No Siblings specified.<br/>
</i>
<table context="clientInfo" items="client.siblingsList" width="100%" visibleWhen="#{client.siblingsList.length>0}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{name}</td>
			 <td>#{age? age : '-'}</td>
			 <td>#{address? address : '-'}</td>
		</tr>
	</tbody>
</table>

<b context="clientInfo" visibleWhen="#{client.spouse.lastname && client.spouse.firstname || !client.spouse.lastname && !client.spouse.firstname}">	
	<hr/>
</b>
<div context="clientInfo" visibleWhen="#{client.spouse.lastname && client.spouse.firstname}">
	<b context="clientInfo" class="maroon">
		<h3>Spouse Information</h3>
	</b>
<div>
<i context="clientInfo" visibleWhen="#{!client.spouse.lastname && !client.spouse.firstname}" class="maroon">
	No Spouse Specified.<br/>
</i>
<br/>
<div style="float: left; margin-right: 20px;">
	<div class="profile-photo4">			
		<label context="clientInfo">
			<a href="/clfc-photos/?b=#{branchcode}&a=#{appid}&id=#{client.spouse.objid}"
				title="#{client.spouse.lastname}, #{client.spouse.firstname} (#{client.spouse.middlename? client.spouse.middlename: '-'})"> 
				<img class="profile" src="#{client.spouse.hasPhoto? '/clfc-photos/?b=' +branchcode+ '&a=' +appid+ '&id=' +client.spouse.objid : 'img/nophoto.png'}" width="150px" 
				title="#{client.spouse.lastname}, #{client.spouse.firstname} (#{client.spouse.middlename? client.spouse.middlename: '-'})"/>
			</a>	
		</label>
	</div>
</div>
<table>
	<tr context="clientInfo" visibleWhen="#{client.spouse.lastname || client.spouse.firstname}">
		<th>Name:</th>
		<td>
			<label context="clientInfo">
				<h3>#{client.spouse.lastname? client.spouse.lastname:' '}, #{client.spouse.firstname? client.spouse.firstname:' '} <i class="personalInfo">(#{client.spouse.middlename? client.spouse.middlename: '-'})</i>
			</label>		
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouse.birthdate}">
		<th>Birthdate: </th>
		<td>
			<label context="clientInfo">#{client.spouse.birthdate? client.spouse.birthdate: '-'}</label>
		</td>
	</tr>	
	<tr context="clientInfo" visibleWhen="#{client.spouse.age}">
		<th>Age: </th>
		<td>
			<label context="clientInfo">#{client.spouse.age? client.spouse.age: '-'}</label>
		</td>
	</tr>		
	<tr context="clientInfo" visibleWhen="#{client.spouse.currentaddress}">
		<th valign="top">Address: </th>
		<td valign="top" class="listInfo">
			<label context="clientInfo" class="listInfo">
				#{client.spouse.currentaddress.address1}
				#{client.spouse.currentaddress.address2? ', ' + client.spouse.currentaddress.address2 : ''}, <br/>
				#{client.spouse.currentaddress.city? ' ' + client.spouse.currentaddress.city : ''}
				#{client.spouse.currentaddress.zipcode? ' ' + client.spouse.currentaddress.zipcode : ''}
				#{client.spouse.currentaddress.province? ', ' + client.spouse.currentaddress.province : ''}
				#{client.spouse.currentaddress.country? ', ' + client.spouse.currentaddress.country : ''}
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouse.residency}">
		<th valign="top">Residency: </th>
		<td valign="top">
			<label context="clientInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{client.spouse.residency.type? client.spouse.residency.type: 'Not specified.'}</b><br/>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{client.spouse.residency.renttype? ' ' + client.spouse.residency.renttype : '-'}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{client.spouse.residency.rentamount.formatDecimal()? 'Php' +' '+ client.spouse.residency.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{client.spouse.residency.remarks? ' ' + client.spouse.residency.remarks : ''}</b><br/> 
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{client.spouse.residency.yrsofstay? ' ' + client.spouse.residency.yrsofstay : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{client.spouse.residency.since? ' ' + client.spouse.residency.since : ''}</b>
				<br/><br/>				
			</label>
		</td>
	</tr>	
	<tr context="clientInfo" visibleWhen="#{client.spouse.lotoccupancy}">
		<th valign="top">Lot Occupancy: </th>
		<td valign="top">
			<label context="clientInfo">
				<j class="listInfo2">Type:</j> <b class="black">#{client.spouse.lotoccupancy.type? client.spouse.lotoccupancy.type: 'Not specified.'}</b></br>	
				<j class="listInfo2">Rent Type:</j> <b class="black">#{client.spouse.lotoccupancy.renttype? ' ' + client.spouse.lotoccupancy.renttype : ''}</b><br/>
				<j class="listInfo2">Rental Fee:</j> <b class="black">#{client.spouse.lotoccupancy.rentamount.formatDecimal()? 'Php' +' '+ client.spouse.lotoccupancy.rentamount.formatDecimal() : ''}</b><br/>
				<j class="listInfo2">Remarks:</j> <b class="black">#{client.spouse.lotoccupancy.remarks? ' ' + client.spouse.lotoccupancy.remarks : ''}</b><br/>
				<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{client.spouse.lotoccupancy.yrsofstay? ' ' + client.spouse.lotoccupancy.yrsofstay : ''}</b><br/>
				<j class="listInfo2">Since:</j> <b class="black">#{client.spouse.lotoccupancy.since? ' ' + client.spouse.lotoccupancy.since : ''}</b>
				<br/><br/>
			</label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouse.citizenship}">
		<th valign="top">Citizenship: </th>
		<td><label context="clientInfo">#{client.spouse.citizenship? client.spouse.citizenship: '-'}</label></td>
	</tr>	
	<tr context="clientInfo" visiblewhen="#{client.spouse.phone}">
		<th valign="top">Phone/Mobile No.: </th>
		<td><label context="clientInfo">#{client.spouse.phone? client.spouse.phone: '-'}</label></td>
	</tr>	
	<tr context="clientInfo" visibleWhen="#{client.spouse.lastname || client.spouse.firstname}">
		<th>
			<label context="clientInfo"><b>#{client.spouse.relation.toLowerCase()? client.spouse.relation.toLowerCase(): 'Spouse'} of </b></label>	
		</th>
		<td>
			<label context="clientInfo">
				<b class="borrowerInfo">
					<b context="loan" name="viewSpouseInfo" class="borrowerInfo">
					    #{client.lastname}, #{client.firstname}
					</b>
					<b><i context="loan" name="viewSpouseInfo" i class="personalInfo">
						(#{client.middlename? client.middlename: '-'})
					</b></i>
				</b>
			</label>
		</td>
	</tr>
</table>
<br/>
<label context="clientInfo" visibleWhen="#{client.spouseFathersName && client.spouseMothersName}">	
	<hr/>
</label>
<label context="clientInfo" visibleWhen="#{!client.spouseFathersName && !client.spouseMothersName}">	
	<hr/>
</label>

<div class="box">
	SPOUSE PARENTS INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.spouseFathersName && !client.spouseMothersName}" class="maroon">
	No Spouse Parents specified.<br/>
</i>
<!--<table context="clientInfo" model="listModel" border="0" width='100%' cellspacing="0" class="grid" 
		visibleWhen="#{client.spouseFathersName && client.spouseMothersName}">-->
<table context="clientInfo" model="listModel" border="0" width="100%" cellspacing="0" class="grid">
	<!--<tr context="clientInfo" visibleWhen="#{client.spouseFathersName}">-->
	<tr>
		<td width="130"><b>Father's Name: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseFathersName? client.spouseFathersName: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseFathersBDate}">
		<td><b>Birthdate: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseFathersBDate? client.spouseFathersBDate: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseFathersAge}">
		<td><b>Age: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseFathersAge} yrs. old</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseMothersName}">
		<td><b>Mother's Name: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseMothersName? client.spouseMothersName:'-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseMothersBDate}">
		<td><b>Birthdate: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseMothersBDate? client.spouseMothersBDate: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseMothersAge}">
		<td><b>Age: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseMothersAge} yrs. old</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseParentsAddress}">
		<td><b>Address: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseAddress? client.spouseAddress: '-'}</b></label>
		</td>
	</tr>
	<tr context="clientInfo" visibleWhen="#{client.spouseOthersSpecs}">
		<td><b>Remarks / Others: </b></td>
		<td>
			<label context="clientInfo"><b>#{client.spouseOthersSpecs? client.spouseOthersSpecs: '-'}</b></label>
		</td>
	</tr>
</table>

<div class="box">
	SPOUSE SIBLINGS INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.spouseSiblingsList.length >0}" class="maroon">
	No Siblings specified.<br/>
</i>
<table context="clientInfo" items="client.spouseSiblingsList" width="100%" visibleWhen="#{client.spouseSiblingsList.length>0}"
	   border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{name}</td>
			 <td>#{age? age: '-'}</td>
			 <td>#{address? address: '-'}</td>
		</tr>
	</tbody>
</table>

<div class="box">
	SAVINGS ACCOUNT INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.savingsAccountsList.length>0}" class="maroon">
	No Savings Account specified.<br/>
</i>
<table context="clientInfo" items="client.savingsAccountsList" visibleWhen="#{client.savingsAccountsList.length>0}" 
	   width="100%" border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Bank Name</th>
			<th>Branch</th>
			<th>Type</th>
			<th>Status</th>
			<th>Others</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{bankName}</td>
			 <td>#{branch}</td>
	    	 <td>#{type? type: '-'}</td>
			 <td>#{status}</td>
			 <td>#{others? others: '-'}</td>
		</tr>
	</tbody>
</table>

<div class="box">
	CHECKING ACCOUNT INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.checkingAccountsList.length>0}" class="maroon">
	No Checking Account specified.<br/>
</i>
<table context="clientInfo" items="client.checkingAccountsList" visibleWhen="#{client.checkingAccountsList.length>0}"
	   width="100%" border="0" cellspacing="0" class="grid" width="100%">
	<thead>
		<tr>
			<th>Bank Name</th>
			<th>Branch</th>
			<th>Kind of Check</th>
			<th>Status</th>
			<th>Others</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			 <td>#{bankName}</td>
			 <td>#{branch}</td>
			 <td>#{kindOfCheck}</td>
			 <td>#{status}</td>
			 <td>#{others? others: '-'}</td>
		</tr>
	</tbody>
</table>

<div class="box">
	OTHER ACCOUNT INFORMATION
</div>
<i context="clientInfo" visibleWhen="#{!client.spouseFathersName}" class="maroon">
	No Other Account specified.<br/>
</i>
<div context="clientInfo" visibleWhen="#{client.otherAccnts}">
	<!--<b class="maroon">Other Accounts</b>-->
	<!--<br/>-->
	<label context="clientInfo">
		<span>
			<pre>#{client.otherAccnts}</pre>
		</span>
	</label>
</div>
