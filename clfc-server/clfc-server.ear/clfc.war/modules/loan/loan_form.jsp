<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rameses.web.support.*" %>


<%
	Map p = new HashMap();
	p.put("objid", request.getParameter("objid"));
	request.setAttribute("READ_PARAM", p);
%>

<common:invoke service="LoanApplicationService" method="read" params="${READ_PARAM}" var="selected"/>
<common:invoke service="LoanApplicationService" method="read" params="${selected}" var="history"/>

<style>
	<jsp:include page="style.css"/>
</style>

<script>
	$put(
		"loan",
		new function() {

			var svc = ProxyService.lookup('LoanApplicationService');
			var _self = this;

			this._controller;

			//passed by the caller
			this.list_state;
			this.objid;
			this.branchcode;

			this.selected;
			this.history;
			
			//map of all selected items mapping
			this.selectedObj = {};

			this.onload = function() {
				this.selected = <%=JsonUtil.toString( request.getAttribute("selected"))%>;
				this.history = <%=JsonUtil.toString( request.getAttribute("history"))%>;
				if( this.history )
					this.history = this.history[0];
					
				setTimeout( function(){ $('.profile-photo a').lightBox({fixedNavigation:true}); }, 100 );
			};

			var approveHandler = function( annotation ) {
				_self.selected = svc.approveApp({entity: _self.selected, annotation: annotation});
				_self._controller.refresh();
			};

			var resendHandler = function( remarks ) {
				_self.selected = svc.resendApp({entity: _self.selected, comment: remarks});
				_self._controller.refresh();
			};

			var disapproveHandler = function( remarks ) {
				_self.selected = svc.disapproveApp({entity: _self.selected, comment: remarks});
				_self._controller.refresh();
			};

			var disqualifiedOutHandler = function( remarks ) {
				_self.selected = svc.disqualifiedOutApp({entity: _self.selected, comment: remarks});
				_self._controller.refresh();
			};

			var canceledOutHandler = function( remarks ) {
				_self.selected = svc.canceledOutApp({entity: _self.selected, comment: remarks});
				_self._controller.refresh();
			};

			var commentHandler = function( message ) {
				_self.selected.comments = svc.addComment({objid: _self.selected.objid, comment: message});
				_self._controller.refresh();
			};
			
			this.getAppType = function() {
				return this.selected.loancount == 1 ? 'New Application' : 'RENEWAL ' + (this.selected.loancount-1)
			}

			this.approve = function() {
				var p = new PopupOpener('loan:approve', {entity: this.selected.loaninfo, selectHandler:approveHandler});
				p.title = 'Approving Application Form';
				p.options = {width: 630, height: 530};
				return p;
			};

			this.provisions = function() {
				var p = new PopupOpener('loan:provisions', {entity: this.selected.loaninfo, selectHandler:provisionsHandler});
				p.title = 'Adding Provisions Form';
				p.options = {width: 500, height: 500};
				return p;
			};
			
			this.sendBack = function() {
				var p = new PopupOpener('loan:comment', {commentHandler:resendHandler,confirmMsg:'You are about to send back this application. Proceed?'});
				p.title = 'Send Back Application Form';
				p.options = {width: 415, height: 275};
				return p;
			};

			this.disapprove = function() {
				var p = new PopupOpener('loan:comment', {commentHandler:disapproveHandler,confirmMsg:'You are about to disapprove this application. Proceed?'});
				p.title = 'Disapprove Application Form';
				p.options = {width: 415, height: 275};
				return p;
			};

			this.disqualifiedOut = function() {
				var p = new PopupOpener('loan:comment', {commentHandler:disqualifiedOutHandler,confirmMsg:'You are about to disqualify this application. Proceed?'});
				p.title = 'Disqualify Application Form';
				p.options = {width: 415, height: 275};
				return p;
			};

			this.canceledOut = function() {
				var p = new PopupOpener('loan:comment', {commentHandler:canceledOutHandler,confirmMsg:'You are about to cancel this application. Proceed?'});
				p.title = 'Cancel Application Form';
				p.options = {width: 415, height: 275};
				return p;
			};

			this.addComment = function() {
				var p = new PopupOpener('loan:comment', {commentHandler:commentHandler});
				p.title = 'Loan Comment Form';
				p.options = {width: 415, height: 275};
				return p;
			};
			
			this.viewParentsSiblingsInfo = function() {
				var p = new PopupOpener('loan:siblingsInfo', {
					parents: this.selected.borrower,
					principalsiblingsList: this.selected.principalsiblingsList,
					principalSpouseSiblingsList: this.selected.principalSpouseSiblingsList
				});				
				p.title = 'Parents & Siblings Information';
				p.options = {width: 600, height: 500};
				return p;
			};
	
			this.viewSpouseInfo = function() {
				var p = new PopupOpener('loan:spouseInfo', {
						branchcode: this.selected.branchcode,
						objid: this.selected.objid,
						spouse: this.selected.spouse
				});	
				p.title = 'Spouse Information';
				p.options = {width: 600, height: 500};
				return p;
			};

			this.viewBorrower = function() {
				var p = new PopupOpener('loan:principalBorrowerInfo', {    
						branchcode: this.selected.branchcode,
						appid: this.selected.objid,
						borrower: this.selectedObj.borrower
				});
				p.title = 'Principal Borrower Information';
				p.options = {width: 600, height: 500};
				return p
			}
			
			this.viewChildrenInfo = function() {
				var p = new PopupOpener('loan:childrenInfo', {childrenList: this.selected.childrenList});				
				p.title = 'Children Information';
				p.options = {width: 780, height: 500};
				return p;
			};

			this.viewSiblingsEmployment = function() {
				var p = new PopupOpener('loan:employmentInfo', {employment: this.selectedObj.employment});
				p.title = 'Employment Information';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewSiblingsOtherIncome = function() {
				var p = new PopupOpener('loan:otherSrcOfIncome', {otherSrcOfIncome: this.selectedObj.otherSrcOfIncome});
				p.title = 'Other Source(s) of Income';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewProfBackground = function() {
				var p = new PopupOpener('loan:profBackground', {list: this.selected.principalProfessionalBackgroundList});
				p.title = 'Professional Background';
				p.options = {width: 600, height: 500};
				return p;
			};

			this.viewEmployment = function() {
				var p = new PopupOpener('loan:employmentInfo', {list: this.selected.principalEmploymentList});
				p.title = 'Employment Information';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewOtherIncome = function() {
				var p = new PopupOpener('loan:otherSrcOfIncome', {list: this.selected.principalOtherSourcesOfIncomeList});
				p.title = 'Other Source(s) of Income';
				p.options = {width: 600, height: 500};
				return p;
			};
			
			this.viewBankAcct = function() {
				var p = new PopupOpener('loan:bankAcct', {
					savingsList: this.selected.principalSavingsAccountsList,
					checkingList: this.selected.principalCheckingAccountsList,
					other: this.selected.borrower.otherAccnts
				});
				p.title = 'Bank Account Information';
				p.options = {width: 600, height: 500};
				return p;
			};
		
			this.viewProperty = function() {
				var p = new PopupOpener('loan:assetProperty', {assetProperty: this.selectedObj.property, application: this.selected});
				p.title = 'Property Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			this.viewVehicle = function() {
				var p = new PopupOpener('loan:assetVehicle', {assetVehicle: this.selectedObj.vehicle, application: this.selected});
				p.title = 'Vehicle Collateral Info';
				p.options = {width: 500, height: 550};
				return p;
			};

			this.viewAppliance = function() {
				var p = new PopupOpener('loan:assetAppliance', {assetAppliance: this.selectedObj.appliance, application: this.selected});
				p.title = 'Appliance Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			this.viewOtherCollateral = function() {
				var p = new PopupOpener('loan:assetOthers', {assetOthers: this.selectedObj.others, application: this.selected});
				p.title = 'Other Collateral Info';
				p.options = {width: 500, height: 430};
				return p;
			};
			
			this.viewAttachments = function() {
				var p = new PopupOpener('loan:assetAttachments', {assetAttachments: this.selectedObj.attachments, application: this.selected});
				p.title = 'Loan Attachments Info';
				p.options = {width: 500, height: 430};
				return p;
			};

			this.viewBusiness = function() {
				var p = new PopupOpener('loan:businessInfo', {business: this.selectedObj.business});
				p.title = 'Business Information';
				p.options = {width: 600, height: 580};
				return p;
			};

			this.viewOtherLending = function() {
				var p = new PopupOpener('loan:otherLending', { info: this.selectedObj.otherLending });
				p.title = 'Other Lending Information';
				p.options = {width: 600, height: 500};
				return p
			}

			this.viewBusinessPhoto = function() {
				var p = new PopupOpener('modules/loan/bussinessPhoto.html', 'businessPhoto', {businessPhoto: this.selectedPhoto});
				p.title = 'Business Photo View';
				p.options = {width: 400, height: 320};
				return p;
			};

			this.viewPrevLedger = function() {
				var p = new PopupOpener('loan:ledger', {ledger: this.history.ledger});
				p.title = 'Previous Ledger';
				p.options = {width: 650, height: 400};
				return p;
			}
			
			/*this.viewPrevLedger = function() {
				var p = new PopupOpener('loan:ledger');
				p.title = 'Previous Ledger';
				p.options = {width: 650, height: 400};
				return p;
			}*/

			this.viewClient = function() {
				var p = new PopupOpener('loan:clientInfo', {    
						branchcode: this.selected.branchcode,
						appid: this.selected.objid,
						client: this.selectedObj.client
				});
				p.title = 'Personal Information';
				p.options = {width: 860, height: 500};
				return p
			}
			
			this.commentTab = 'comment';
			this.showCommentTab = function( type ) { this.commentTab = 'comment'; }
			this.showRecommendationTab = function( type ) { this.commentTab = 'recommendation'; }
			this.showCrecomRecommendationTab = function( type ) { this.commentTab = 'crecomrecommendation'; }
			this.showPrevFLATab = function( type ) { this.commentTab = 'prevfla'; }					
		}
	);
</script>
<label class="doc-title" context="loan">
	<span style="float: left">
		Application: <span class="maroon">#{selected.appno}</span>
	</span>
	<span style="float: right">
		Status: <span class="maroon">#{selected.state}</span>
	</span>
</label>
<div class="toolbar">
	<a context="loan" name="_close">Back to List</a>
	<input type="button" context="loan" name="approve" value="Approve" visibleWhen="#{selected.state == 'FOR_APPROVAL'}"/>
	<input type="button" context="loan" name="sendBack" value="Send Back" tooltip="Return this application?" visibleWhen="#{selected.state == 'FOR_APPROVAL'}"/>
	<input type="button" context="loan" name="disapprove" value="Disapprove" visibleWhen="#{selected.state == 'FOR_APPROVAL'}"/>
	<input type="button" context="loan" name="disqualifiedOut" value="Disqualified Out" visibleWhen="#{selected.state == 'FOR_APPROVAL'}"/>	
	<input type="button" context="loan" name="canceledOut" value="Canceled Out" visibleWhen="#{selected.state == 'FOR_APPROVAL'}"/>
	<input type="button" context="loan" name="viewPrevLedger" value="View Previous Ledger" visibleWhen="#{history.ledger}"/>
	<!--<input type="button" context="loan" name="viewPrevLedger" value="View Previous Ledger"/>-->
</div>
<div context="loan">
	<label class="doc-title2" context="loan">
		<span style="float: left">
			<h2 class="navy">#{getAppType()}<br/>
			    <b class="black" context="loan">Client Type :</b>
				<b context="loan" class="maroon2">#{selected.clientType}</b> <b class="black" context="loan" 
						visibleWhen="#{selected.marketedby}">by</b>
				<b context="loan" visibleWhen="#{selected.marketedby}" class="fuchsia">#{selected.marketedby}</b>
				<j context="loan" visibleWhen="#{selected.marketedby2}"><br/></j>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b class="black" context="loan" visibleWhen="#{selected.marketedby2}">and</b>
				<b context="loan" visibleWhen="#{selected.marketedby2}" class="fuchsia">#{selected.marketedby2}</b><br/>
			</h2>	
		</span>
		<span style="float: right">
			<h2><b class="navy" context="loan" visibleWhen="#{selected.branchname}">#{selected.branchname}</b>
				<b class="black">Branch/Encoded By :</b> <b class="fuchsia">#{selected.encodedBy}</b><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b context="loan" visibleWhen="#{selected.updatedBy}" class="black">Updated By :</b> <b context="loan"  visibleWhen="#{selected.updatedBy}" class="green2">#{selected.updatedBy}</b><br/>
			</h2>	
		</span>
	</label>
</div>
<div class="box">
	Client Application Information
</div>
<table width="100%">
	<tr>
		<td valign="top" style="padding-right:15px; width:100px">
			<div class="profile-photo">			
				<label context="loan">
					<a href="/clfc-photos/?b=#{selected.branchcode}&a=#{selected.objid}&id=#{selected.borrower.objid}"
						title="#{selected.borrower.lastname}, #{selected.borrower.firstname} (#{selected.borrower.middlename? selected.borrower.middlename: '-'})"> 
						<img src="#{selected.borrower.hasPhoto? '/clfc-photos/?b=' +selected.branchcode+ '&a=' +selected.objid+ '&id=' +selected.borrower.objid : 'img/nophoto.png'}" width="100px" 
							title="#{selected.borrower.lastname}, #{selected.borrower.firstname} (#{selected.borrower.middlename? selected.borrower.middlename: '-'})"/>
					</a>	
				</label>
			</div>	
		</td>
		<td valign="top">
			<table>
				<tr>
					<th colspan="2" class="maroon">Principal Borrower</th>
				</tr>
				<tr>
					<th>Name: </th>
					<td>
						<label context="loan"><b class="borrowerInfo">#{selected.borrower.lastname}, #{selected.borrower.firstname} <i class="personalInfo">(#{selected.borrower.middlename? selected.borrower.middlename: '-'}) </i></b></label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.birthdate}">
					<th>Birthdate: </th>
					<td>
						<label context="loan">#{selected.borrower.birthdate}</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.age}">
					<th>Age: </th>
					<td>
						<label context="loan">#{selected.borrower.age} yrs. old</label>
					</td>
				</tr>
				<!--<tr context="loan" visibleWhen="#{selected.borrower.homeaddress.address1}">
					<th valign="top">Home Address: </th>
					<td valign="top" class="listInfo">
						<label context="loan" class="listInfo">
							#{selected.borrower.homeaddress.address1}
							#{selected.borrower.homeaddress.address2? ', ' + selected.borrower.homeaddress.address2 : ''}
							#{selected.borrower.homeaddress.city? ', ' + selected.borrower.homeaddress.city : ''}
							#{selected.borrower.homeaddress.zipcode? ' ' + selected.borrower.homeaddress.zipcode : ''}
							#{selected.borrower.homeaddress.province? ', ' + selected.borrower.homeaddress.province : ''}
							#{selected.borrower.homeaddress.country? ', ' + selected.borrower.homeaddress.country : ''}
							<br/><br/>
						</label>
					</td>
				</tr>-->
				<tr context="loan" visibleWhen="#{selected.borrower.currentaddress.address1}">
					<th valign="top">Current/Home Address: </th>
					<td valign="top" class="listInfo">
						<label context="loan" class="listInfo">
							#{selected.borrower.currentaddress.address1}
							#{selected.borrower.currentaddress.address2? ', ' + selected.borrower.currentaddress.address2 : ''}
							#{selected.borrower.currentaddress.city? ', ' + selected.borrower.currentaddress.city : ''}
							#{selected.borrower.currentaddress.zipcode? ' ' + selected.borrower.currentaddress.zipcode : ''}
							#{selected.borrower.currentaddress.province? ', ' + selected.borrower.currentaddress.province : ''}
							#{selected.borrower.currentaddress.country? ', ' + selected.borrower.currentaddress.country : ''}
							<br/><br/>
						</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.previousaddress.address1}">
					<th valign="top">Previous Address: </th>
					<td valign="top" class="listInfo">
						<label context="loan" class="listInfo">
							#{selected.borrower.previousaddress.address1}
							#{selected.borrower.previousaddress.address2? ', ' + selected.borrower.previousaddress.address2 : ''}
							#{selected.borrower.previousaddress.city? ', ' + selected.borrower.previousaddress.city : ''}
							#{selected.borrower.previousaddress.zipcode? ' ' + selected.borrower.previousaddress.zipcode : ''}
							#{selected.borrower.previousaddress.province? ', ' + selected.borrower.previousaddress.province : ''}
							#{selected.borrower.previousaddress.country? ', ' + selected.borrower.previousaddress.country : ''}
							<br/><br/>
						</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.provincialaddress.address1}">
					<th valign="top">Provincial Address/Origin: </th>
					<td valign="top" class="listInfo">
						<label context="loan" class="listInfo">
							#{selected.borrower.provincialaddress.address1}
							#{selected.borrower.provincialaddress.address2? ', ' + selected.borrower.provincialaddress.address2 : ''}
							#{selected.borrower.provincialaddress.city? ', ' + selected.borrower.provincialaddress.city : ''}
							#{selected.borrower.provincialaddress.zipcode? ' ' + selected.borrower.provincialaddress.zipcode : ''}
							#{selected.borrower.provincialaddress.province? ', ' + selected.borrower.provincialaddress.province : ''}
							#{selected.borrower.provincialaddress.country? ', ' + selected.borrower.provincialaddress.country : ''}
							<br/><br/>
						</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.phone}">
					<th>Phone No.: </th>
					<td>
						<label context="loan">#{selected.borrower.phone}</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.civilstat}">
					<th>Marital Status: </th>
					<td>
						<label context="loan">#{selected.borrower.civilstat? selected.borrower.civilstat: '-'}</label>
					</td>
				</tr>
				<tr context="loan" visibleWhen="#{selected.borrower.residency}">
					<th valign="top">Residency: </th>
					<td valign="top">
						<label context="loan">
							<j class="listInfo2">Type:</j> <b class="black">#{selected.borrower.residency.type? selected.borrower.residency.type: 'Not specified.'}</b><br/>	
							<j class="listInfo2">Rent Type:</j> <b class="black">#{selected.borrower.residency.renttype? ' ' + selected.borrower.residency.renttype : '-'}</b><br/>
							<j class="listInfo2" context="loan" visibleWhen="#{selected.borrower.residency.rentamount}">Rental Fee:</j> 
							    <b class="black" context="loan" visibleWhen="#{selected.borrower.residency.rentamount}">
							        #{selected.borrower.residency.rentamount.formatDecimal()? 'Php' +' '+ selected.borrower.residency.rentamount.formatDecimal() : ''}
							     </b><br/>
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
							<j class="listInfo2">Type:</j> <b class="black">#{selected.borrower.lotoccupancy.type? selected.borrower.lotoccupancy.type: 'Not specified.'}</b></br>
							<j class="listInfo2">Rent Type:</j> <b class="black">#{selected.borrower.lotoccupancy.renttype? ' ' + selected.borrower.lotoccupancy.renttype : ''}</b><br/>
							<j class="listInfo2">Rental Fee:</j> 
							    <b class="black">
							        #{selected.borrower.lotoccupancy.rentamount.formatDecimal()? 'Php' +' '+ selected.borrower.lotoccupancy.rentamount.formatDecimal() : ''}
							    </b><br/>
							<j class="listInfo2">Remarks:</j> <b class="black">#{selected.borrower.lotoccupancy.remarks? ' ' + selected.borrower.lotoccupancy.remarks : ''}</b><br/>
							<j class="listInfo2">Since:</j> <b class="black">#{selected.borrower.lotoccupancy.since? ' ' + selected.borrower.lotoccupancy.since : ''}</b><br/>
							<j class="listInfo2">Yrs.of Stay:</j> <b class="black">#{selected.borrower.lotoccupancy.yrsofstay? ' ' + selected.borrower.lotoccupancy.yrsofstay : ''}</b>
							<br/><br/>
						</label>
					</td>
				</tr>	
				<tr context="loan" visibleWhen="#{selected.borrower.citizenship}">
					<th>Citizenship: </th>
					<td>
						<label context="loan">#{selected.borrower.citizenship? selected.borrower.citizenship: '-'}</label>
					</td>
				</tr>
			</table>
			<table>
				<!-- Spouse Info  -->
				<tr context="loan" visibleWhen="#{selected.spouse.lastname.length >0 && selected.spouse.firstname.length >0}">
					<th>
						<label context="loan"><b>#{selected.spouse.relation.toLowerCase()? selected.spouse.relation.toLowerCase(): 'Spouse'}: </b></label>
					</th>
					<td>
						<label context="loan">
							<b class="borrowerInfo">
								<a context="loan" name="viewSpouseInfo"><b class="borrowerInfo" tooltip="click to view more">
								    #{selected.spouse.lastname}, #{selected.spouse.firstname}
								</b></a>	
								<a context="loan" name="viewSpouseInfo"><i class="personalInfo"><b> 
									(#{selected.spouse.middlename? selected.spouse.middlename: '-'})
								</i></b></a>
							</b>
						</label>
					</td>
				</tr>
			</table>
		</td>
		<td valign="top" width="220px" style="padding-left: 5px;">
			<b class="maroon" context="loan" visibleWhen="#{!selected.childrenList.length >0 
				&& !selected.spouse && !selected.principalProfessionalBackgroundList.length >0 
				&& !selected.principalEmploymentList.length >0}">
				<h4>No other information listed.</h4>
			</b>
			<b class="maroon" context="loan" 
				visibleWhen="#{selected.childrenList.length > 0 || selected.spouse || selected.principalProfessionalBackgroundList.length > 0 || selected.principalEmploymentList.length > 0 }">
				<h4>Click to view more information</h4>
			</b>							
			<ul>
				<li context="loan" visibleWhen="#{selected.spouse}">
					<a href="#" context="loan" name="viewSpouseInfo">
						<label context="loan">
							#{selected.spouse.relation.toLowerCase()? selected.spouse.relation.toLowerCase(): 'Spouse'}
						</label> 
						Information
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.childrenList.length > 0}">
					<a href="#" context="loan" name="viewChildrenInfo">
						Children Information
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.principalProfessionalBackgroundList.length > 0}" >
					<a href="#" context="loan" name="viewProfBackground">
						Professional Background
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.principalEmploymentList.length > 0}">
					<a href="#" context="loan" name="viewEmployment">
						Employment Record
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.principalOtherSourcesOfIncomeList.length > 0}">
					<a href="#" context="loan" name="viewOtherIncome">
						Other Source(s) of Income
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.principalSavingsAccountsList.length > 0}">
					<a href="#" context="loan" name="viewBankAcct">
						Bank Accounts
					</a>
				</li>
				<li context="loan" visibleWhen="#{selected.principalsiblingsList.length > 0}">
					<a href="#" context="loan" name="viewParentsSiblingsInfo">
						Parents & Siblings Information
					</a>
				</li>
			</ul>
		</td>
	</tr>
</table>
<hr/>
<b class="maroon">Joint Borrower(s)</b>
<j context="loan" visibleWhen="#{!selected.jointBorrowerList.length >0}">
	<br><br>
	<i class="maroon" align="center">No Joint Borrower specified.</i>
</j>
<table context="loan" items="selected.jointBorrowerList" name="selectedObj.client" varStatus="stat" class="list" >
	<tbody>
		<tr>
			<td colspan="2">
				(#{stat.index+1}) <a context="loan" name="viewClient"><b class="borrowerInfo">#{name}</a></b> - 
				<b class="fuchsia"> #{relation? relation : '-'} </b> of the <b class="maroon">
				<a context="loan" name="viewBorrower">Principal Borrower</a></b>
			</td>
		</tr>
		<tr>
			<th class="info" width="100px">Age:</th>
			<td class="info">#{age? age : '-'}  yrs. old</td>
		</tr>
		<tr>
			<th class="info">Phone No.:</th>
			<td class="info">#{phone? phone : '-'}</td>
		</tr>
		<tr>
			<th class="info" valign="top">Current Address:</th>
			<td class="info" valign="top">
				#{currentaddress.address1? currentaddress.address1 : ''}
				#{currentaddress.address2? ', ' + currentaddress.address2 : ''}
				#{currentaddress.city? ', ' + currentaddress.city : ''}
				#{currentaddress.zipcode? ' ' + currentaddress.zipcode : ''}
				#{currentaddress.province? ', ' + currentaddress.province : ''}
				#{currentaddress.country? ', ' + currentaddress.country : ''}
				<br/>
				<br/>
			</td>
		</tr>
	</tbody>
</table>
<hr/>
<b class="maroon">Co-Maker(s)</b>
<j context="loan" visibleWhen="#{!selected.coMakerList.length >0}">
	<br><br>
	<i class="maroon" align="center">No Co-Maker specified.</i>
</j>
<table context="loan" items="selected.coMakerList" name="selectedObj.client" varStatus="stat" class="list">
	<tbody>
		<tr>
			<td colspan="2">
				(#{stat.index+1}) <a context="loan" name="viewClient"><b class="borrowerInfo">#{name}</a></b> - 
				<b class="fuchsia"> #{relation? relation : '-'} </b> of the <b class="maroon">
				<a context="loan" name="viewBorrower">Principal Borrower</a></b>
			</td>
		</tr>
		<tr>
			<th class="info" width="100px">Age:</th>
			<td class="info">#{age? age : '-'} yrs. old</td>
		</tr>
		<tr>
			<th class="info">Phone No.:</th>
			<td class="info">#{phone? phone : '-'}</td>
		</tr>
		<tr>
			<th class="info" valign="top">Current Address:</th>
			<td class="info" valign="top">
				#{currentaddress.address1? currentaddress.address1 : ''}
				#{currentaddress.address2? ', ' + currentaddress.address2 : ''}
				#{currentaddress.city? ', ' + currentaddress.city : ''}
				#{currentaddress.zipcode? ' ' + currentaddress.zipcode : ''}
				#{currentaddress.province? ', ' + currentaddress.province : ''}
				#{currentaddress.country? ', ' + currentaddress.country : ''}
				<br/>
				<br/>
			</td>
		</tr>
	</tbody>
</table>
<hr/>
<div class="box" >
	LOAN INFORMATION
</div>
<div class="form-panel">
	<table>
		<tr>
			<th>Purpose of Loan:</th>
			<td><label context="loan">#{selected.loaninfo.loanpurpose.toUpperCase()}</label></td>
		</tr>
		<tr>
			<th>Amount Applied:</th>
			<td>
				<b class="navy">Php</b>
				<label context="loan">
					<b class="green">#{selected.loaninfo.loanamount.formatDecimal()}</b>
				</label>
			</td>
		</tr>
		<tr context="loan" visibleWhen="#{selected.dtfiled}">
			<th>Date Applied:</th>
			<td>
				<label context="loan" class="other" visibleWhen="#{selected.dtfiled}">
					<b class="gray">#{selected.dtfiled}</b></label>
			</td>
		</tr>
		<tr context="loan" visibleWhen="#{selected.loaninfo.amountapproved}">
			<th>Amount Approved:</th>
			<td>
				<b class="navy">Php</b> 
				<label context="loan" class="maroon">
					<b>#{selected.loaninfo.amountapproved.formatDecimal()}</b>
				</label>
			</td>
		</tr>
		<tr context="loan" visibleWhen="#{selected.dtreleased.length >0}">
			<th>Date Released:</th>
			<td><label context="loan" visibleWhen="#{selected.dtreleased.length >0}">
					<b class="dKhaki">#{selected.dtreleased}</b>
				</label>
			</td>
		</tr>	
		<tr context="loan" visibleWhen="#{selected.canceleddate}">
			<th>Date Canceled:</th>
			<td>
				<label context="loan" visibleWhen="#{selected.canceleddate}">
					<b class="gray">#{selected.canceleddate}</b>
				</label>
			</td>
		</tr>
		<tr context="loan" visibleWhen="#{selected.resenddate}">
			<th>Date Resend:</th>
			<td>
				<label context="loan" visibleWhen="#{selected.resenddate}">
					<b class="gray">#{selected.resenddate}</b>
				</label>
			</td>
		</tr>
		<tr context="loan" visibleWhen="#{selected.ledger.maturityDate.length >0}">
			<th>Maturity Date:</th>
			<td>
				<label context="loan" visibleWhen="#{selected.ledger.maturityDate.length >0}">
					<b class="gray">#{selected.ledger.maturityDate}</b>
				</label>
			</td>
		</tr>
		<tr>
			<th>Product Type:</th>
			<td><label context="loan">#{selected.loaninfo.producttype.code}</label></td>
		</tr>
		<tr>
			<th>Term:</th>
			<td><label context="loan">#{selected.loaninfo.producttype.term} days</label></td>
		</tr>		
		<tr>
			<th>Interest Rate (%):</th>
			<td><label context="loan">#{selected.loaninfo.producttype.interestrate.formatDecimal()}</label></td>
		</tr>
	</table>
</div>
<div class="box">
	BUSINESS INFORMATION
</div>
<label context="loan" visibleWhen="#{!selected.principalMainBusinessList}">
	<i class="maroon">No Business Specified.</i><br/>
</label>
<table context="loan" visibleWhen="#{selected.principalMainBusinessList}" name="selectedObj.business"
       items="selected.principalMainBusinessList" varName="item" 
       class="grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<td>Business Name</td>
			<td>Address</td>
			<td>Bus. Type</td>
			<td>Stall Size / Offer</td>
		</tr>
	</thead>
    <tbody class="selectable">
    	<tr context="loan" action="viewBusiness">
    		<td>#{item.tradename}</td>
    		<td>#{item.address}</td>
    		<td>#{item.businesstype.type? item.businesstype.type: '-'}</td>
    		<td>#{item.businesstype.stallsize? item.businesstype.stallsize: '-'}</td>
    	</tr>
    </tbody>
</table>
<div class="box">
	LOAN ATTACHMENT(S)
</div>
<i context="loan" visibleWhen="#{!selected.attachmentslist.length > 0}" class="maroon">
	No Attachment(s) specified.<br/><br/>
</i>
<table context="loan" items="selected.attachmentslist" name="selectedObj.attachments"
       visibleWhen="#{selected.attachmentslist.length>0}"
       width="100%" border="0" cellspacing="0" class="grid" width="100%" varStatus="stat">
	<thead>
		<th>Name</th>
		<th>Type</th>
		<th>Remarks</th>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewAttachments">
			 <td>#{subject}</td>
			 <td>#{type}</td>
			 <td>#{remarks.toUpperCase()}</td>
		</tr>
	</tbody>
</table>
<div class="box">
	COLLATERALS
</div>
<i class="maroon" context="loan" visibleWhen="#{!selected.propertylist.length >0}">
    No property(ies) specified. <br/>
</i>
<b class="maroon"  context="loan" visibleWhen="#{selected.propertylist.length >0 && !appraisedvalue}">Property(ies)</b> 
       <j context="loan" visibleWhen="#{selected.propertylist.length >0 && !appraisedvalue}"> ( Total Appraised =</j> 
       <b context="loan" visibleWhen="#{selected.propertylist.length >0 && !appraisedvalue}">Php</b>
        <j class="teal2" context="loan" visibleWhen="#{selected.propertylist.length >0 && !appraisedvalue}"> 0.00</j> 
        <j  context="loan" visibleWhen="#{selected.propertylist.length >0 && !appraisedvalue}">)</j>
<b class="maroon" context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}">Property(ies)</b> 
	   <j context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}"> ( Total Appraised = </j>
	   <b context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}">Php</b> 
	     <b class="teal" context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}"> 
	     <label context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}">#{appraisedvalue.formatDecimal()}</label></b> 
	     <j  context="loan" visibleWhen="#{selected.propertylist.length >0 && appraisedvalue}">)</j>

<!--
<label context="loan" visibleWhen="#{selected.propertylist.length >0}">
	<b class="maroon">Property/Properties</b> ( Total Appraised = <b>Php</b> <b class="teal">
		<label context="loan">#{appraisedvalue.formatDecimal()}</label></b> )
</label>
-->
<table context="loan" items="selected.propertylist" class="grid" width="100%" cellspacing="0"
       visibleWhen="#{selected.propertylist.length >0}" name="selectedObj.property">
	<thead>
		<tr>
			<th>Land/Building</th>
			<th>Location</th>
			<th>Zonal Value</th>
			<th>Appraised Value</th>
			<th>Area</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewProperty">
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{rpu}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{location}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{zonalvalue}">
				<b class="gray">Php</b> <j class="teal2">#{zonalvalue.formatDecimal()}</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{!zonalvalue}">
				<b class="gray">Php</b> <j class="teal">0.00</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{appraisedvalue}">
				<b class="gray">Php</b> <j class="teal2">#{appraisedvalue.formatDecimal()}</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{!appraisedvalue}">
				<b class="gray"  context="loan" visibleWhen="#{!appraisedvalue}">Php</b> 
				    <j class="teal">0.00</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan">#{area.formatDecimal()} #{uom}</td>
		</tr>
	</tbody>
</table>

<!--  Other related Sites   -->
<div context="loan" width="220px" style="padding-left: 5px;" visibleWhen="#{selected.propertylist.length >0}">
       <h4 class="green"> Click the following related sites</h4>
            <ul> 
                 <li><span="50px><a href="http://www.bir.gov.ph/zonalvalues/zonalvalues.htm" target="_blank">Go to BIR Zonal Value</a></span></li>
                 <!--<li><span="50px"><a href="http://maps.google.com/" target="_blank">Go to Google Map / Search Boundary Limit</a></span></li>-->
            </ul>     
</div>
<br/>
            <!-- Google Map -->
<div align="center" context="loan" visibleWhen="#{selected.propertylist.length >0}">
        <iframe width="750" height="350" frameborder="0" scrolling="no" 
	        marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;
	        geocode=&amp;q=N.+Bacalso+Avenue,+Cebu,+Central+Visayas,+Philippines&amp;aq=0&amp;oq=cebu+n.bacalso&amp;
	        sll=37.0625,-95.677068&amp;sspn=38.690438,56.513672&amp;ie=UTF8&amp;hq=&amp;hnear=N+Bacalso+Ave,+Cebu+City,+Cebu,+Central+Visayas,      +Philippines&amp;
	        ll=10.29543,123.883436&amp;spn=0.023519,0.027595&amp;t=m&amp;z=14&amp;output=embed">
    </iframe><!--width="425" height="350"-->
    <br />
    <small>
	    <a href="http://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;
	    q=N.+Bacalso+Avenue,+Cebu,+Central+Visayas,+Philippines&amp;aq=0&amp;oq=cebu+n.bacalso&amp;
	    sll=37.0625,-95.677068&amp;sspn=38.690438,56.513672&amp;ie=UTF8&amp;
	    hq=&amp;hnear=N+Bacalso+Ave,+Cebu+City,+Cebu,+Central+Visayas,+Philippines&amp;ll=10.29543,123.883436&amp;
	    spn=0.023519,0.027595&amp;t=m&amp;z=14" style="color:#0000FF;text-align:left"  target="_blank">View Larger Map</a>
    </small>
</div>
<!-------------------->

<i class="maroon" context="loan" visibleWhen="#{!selected.appliancelist.length >0}">
    No appliance(s) specified. <br/>
</i>
<b class="maroon"  context="loan" visibleWhen="#{selected.appliancelist.length >0 && !appraisedvalue}">Appliance(s)</b> 
       <j context="loan" visibleWhen="#{selected.appliancelist.length >0 && !appraisedvalue}"> ( Total Appraised =</j> 
       <b context="loan" visibleWhen="#{selected.appliancelist.length >0 && !appraisedvalue}">Php</b>
        <j class="teal2" context="loan" visibleWhen="#{selected.appliancelist.length >0 && !appraisedvalue}"> 0.00</j> 
        <j  context="loan" visibleWhen="#{selected.appliancelist.length >0 && !appraisedvalue}">)</j>
<br/>
<b class="maroon" context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}">Appliance(s)</b> 
	   <j context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}"> ( Total Appraised = </j>
	   <b context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}">Php</b> 
	     <b class="teal" context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}"> 
	     <label context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}">#{appraisedvalue.formatDecimal()}</label></b> 
	     <j  context="loan" visibleWhen="#{selected.appliancelist.length >0 && appraisedvalue}">)</j>
	     
<table context="loan" items="selected.appliancelist" class="grid" width="100%" cellspacing="0"
       visibleWhen="#{selected.appliancelist.length >0}" name="selectedObj.appliance">
	<thead>
		<tr>
			<th width="30%">Appliance</th>
			<th width="35%">Brand <b>/</b> Serial# <b>/</b> Model#</th>
			<th width="15%">Appraised Value</th>
			<th width="20%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewAppliance">
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{type}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{brand} <b>/</b> #{serial} <b>/</b> #{model}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" align="center">
			    <!--<b class="gray" context="loan" visibleWhen="#{!appraisedvalue}" visibleWhen="#{!appraisedvalue}">Php</b> 
			    <j class="teal2" context="loan" visibleWhen="#{!appraisedvalue}">0.00</j> -->
			    <j class="gray" context="loan" visibleWhen="{appraisedvalue}">Php</j>
				<j class="teal2" context="loan" visibleWhen="#{appraisedvalue}">#{appraisedvalue.formatDecimal()}</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{remarks.toUpperCase()}</td>
		</tr>
	</tbody>
</table>
<!--<j context="loan" visibleWhen="#{selected.vehiclelist.length >0}">
	<b class="maroon">Vehicle(s)</b> ( Total Appraised = <b>Php</b> <b class="teal">
		<label context="loan">#{appraisedvalue.formatDecimal()}</label></b> )
</j>-->
<i class="maroon" context="loan" visibleWhen="#{!selected.vehiclelist.length >0}">
    No vehicle(s) specified. <br/>
</i>
<b class="maroon"  context="loan" visibleWhen="#{selected.vehiclelist.length >0 && !appraisedvalue}">Vehicle(s)</b> 
       <j context="loan" visibleWhen="#{selected.vehiclelist.length >0 && !appraisedvalue}"> ( Total Appraised =</j> 
       <b context="loan" visibleWhen="#{selected.vehiclelist.length >0 && !appraisedvalue}">Php</b>
        <j class="teal2" context="loan" visibleWhen="#{selected.vehiclelist.length >0 && !appraisedvalue}"> 0.00</j> 
        <j  context="loan" visibleWhen="#{selected.vehiclelist.length >0 && !appraisedvalue}">)</j> <br/>
<b class="maroon" context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}">Vehicle(s)</b> 
	   <j context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}"> ( Total Appraised = </j>
	   <b context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}">Php</b> 
	     <b class="teal" context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}"> 
	     <label context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}">#{appraisedvalue.formatDecimal()}</label></b> 
	     <j  context="loan" visibleWhen="#{selected.vehiclelist.length >0 && appraisedvalue}">)</j>
	     
<table context="loan" items="selected.vehiclelist" class="grid" width="100%" cellspacing="0"
			visibleWhen="#{selected.vehiclelist.length >0}" name="selectedObj.vehicle">
	<thead>
		<tr>
			<th width="20%">Make</th>
			<th width="20%">Model</th>
			<th width="30%">Type <b>/</b> Engine# <b>/</b> Chassis#</th>
			<th width="10%">Plate#</th>
			<th width="20%">Registration#</th>
			<th width="20%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewVehicle">
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{make}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{model}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{type} <b>/</b> #{engineno} <b>/</b> #{chassisno}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{plateno}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{regno}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{remarks.toUpperCase()}</td>
		</tr>
	</tbody>
</table>
<i class="maroon" context="loan" visibleWhen="#{!selected.otherslist.length >0}">
    No other collateral specified. <br/>
</i>
<b class="maroon"  context="loan" visibleWhen="#{selected.otherslist.length >0 && !appraisedvalue}">Other Collateral(s)</b> 
       <j context="loan" visibleWhen="#{selected.otherslist.length >0 && !appraisedvalue}"> ( Total Appraised =</j> 
       <b context="loan" visibleWhen="#{selected.otherslist.length >0 && !appraisedvalue}">Php</b>
        <j class="teal2" context="loan" visibleWhen="#{selected.otherslist.length >0 && !appraisedvalue}"> 0.00</j> 
        <j  context="loan" visibleWhen="#{selected.otherslist.length >0 && !appraisedvalue}">)</j> <br/>
<b class="maroon" context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}">Other Collateral(s)</b> 
	   <j context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}"> ( Total Appraised = </j>
	   <b context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}">Php</b> 
	     <b class="teal" context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}"> 
	     <label context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}">#{appraisedvalue.formatDecimal()}</label></b> 
	     <j  context="loan" visibleWhen="#{selected.otherslist.length >0 && appraisedvalue}">)</j>
	     
<!--
<i class="maroon" context="loan" visibleWhen="#{!selected.otherslist}">
    No other collateral specified. <br/>
</i>
<b class="maroon"  context="loan" visibleWhen="#{!appraisedvalue}">Other Collateral(s)</b> 
       <j context="loan" visibleWhen="#{!appraisedvalue}"> ( Total Appraised =</j> <b context="loan" visibleWhen="#{!appraisedvalue}">Php</b>
        <j class="teal2" context="loan" visibleWhen="#{!appraisedvalue}"> 0.00</j> <j  context="loan" visibleWhen="#{!appraisedvalue}">)</j>
<br/>
<b class="maroon" context="loan" visibleWhen="#{appraisedvalue}">Other Collateral(s)</b> 
	   <j context="loan" visibleWhen="#{appraisedvalue}"> ( Total Appraised = </j><b context="loan" visibleWhen="#{appraisedvalue}">Php</b> 
	     <b class="teal" context="loan" visibleWhen="#{appraisedvalue}"> 
	     <label context="loan" visibleWhen="#{appraisedvalue}">#{appraisedvalue.formatDecimal()}</label></b> <j  context="loan" visibleWhen="#{appraisedvalue}">)</j>
-->
    
<table context="loan" items="selected.otherslist" class="grid" width="100%" cellspacing="0"
			visibleWhen="#{selected.otherslist.length >0}" name="selectedObj.others">
	<thead>
		<tr>
			<th width="20%">Name</th>
			<th width="20%">Classification</th>
			<th width="15%">Use</th>
			<th width="10%">Date Acquired</th>
			<th width="15%">Market/Appraised Value</th>
			<th width="20%">Remarks</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewOtherCollateral">
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{subject}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{modeOfAcquisition}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{use}</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{dateAcquired}">
				#{dateAcquired}
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{!dateAcquired}">
				<b class="maroon"> - </b>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{appraisedvalue}" align="center">
				Php <j class="teal2">#{appraisedvalue.formatDecimal()}</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}" context="loan" visibleWhen="#{!appraisedvalue}" align="center">
				Php <j class="teal2">0.00</j>
			</td>
			<td valign="top" class="${idx%2==0? 'even' : 'odd'}">#{remarks.toUpperCase()}</td>
		</tr>
	</tbody>
</table>
<j context="loan" visibleWhen="#{!selected.otherslist}">
    <br/>
</j>  
<table>
	<div style="text-align:left">
		Total Collateral Appraisal Value : 
		<label context="loan" visibleWhen="#{selected.totalCAV}"><b>Php</b> <b class="teal">
			<u>#{selected.totalCAV.formatDecimal()}</u></b></label>
		<label context="loan" visibleWhen="#{!selected.totalCAV}"><b>Php</b> <u class="maroon">0.00</u></label>
	</div><br/>	
</table>
<div class="box">
	OTHER LENDING INFORMATION
</div>
<i context="loan" visibleWhen="#{!selected.otherLendingList.length > 0}" class="maroon">
	No Other Lending specified.<br/><br/>
</i>
<table context="loan" items="selected.otherLendingList" name="selectedObj.otherLending"
       visibleWhen="#{selected.otherLendingList.length>0}"
       width="100%" border="0" cellspacing="0" class="grid" width="100%" varStatus="stat">
	<thead>
		<th>Kind Of Loan</th>
		<th>Company</th>
		<th>Amount Loaned</th>
		<th>Date Granted</th>
		<th>Maturity Date</th>
		<th>Term</th>
		<th>Interest Rate</th>
		<th>Mode of Pyt</th>
		<th>Lending Pyt</th>
	</thead>
	<tbody class="selectable">
		<tr context="loan" action="viewOtherLending">
			 <td>#{kindofLoan}</td>
			 <td>#{company}</td>
			 <td class="dKhaki2">Php <b class="gray">#{loanAmount.formatDecimal()}</b></td>
			 <td class="lendingInfo">#{dateGranted}</td>
			 <td class="lendingInfo2">#{maturityDate}</td>
			 <td>#{term} days</td>
			 <td>#{interestRate.formatDecimal()} %</td>
			 <td>#{modeofPayment}</td>
			 <td class="dKhaki2">Php <b class="navy">#{lendingPayment.formatDecimal()}</b></td>
		</tr>
	</tbody>
</table>
<div context="loan"> <!--visibleWhen="#{selected.loaninfo.approvalType}">-->
	<div class="box">
		FLA
	</div>
	<div context="loan" visibleWhen="#{!selected.loaninfo.amountapproved || !selected.loaninfo.offeredamount}">
		<i class="maroon">No FLA record yet.</i>
	</div>

	<!-- Testing Purposes Only -->
	<div context="loan" visibleWhen="#{selected.loaninfo.amountapproved}">
		<fieldset context="loan" visibleWhen="#{selected.loaninfo.amountapproved}">
			<table>
				<legend>
					<h3 class="green">LOAN APPROVAL</h3>
				</legend>
				<td valign="top" style="padding-left:15px; width:100px">
					<tr>
						<td>Amount Approved:</td>
						<td>
							<label context="loan" visibleWhen="#{selected.loaninfo.amountapproved.formatDecimal()}">
								Php <b class="teal">#{selected.loaninfo.amountapproved.formatDecimal()}</b>
							</label>
							<label context="loan" visibleWhen="#{!selected.loaninfo.amountapproved}">
								<b>Php <u class="maroon">00.00</u></b>
							</label>
						</td>
					</tr>
					<tr>
						<td valign="top">Absences</td>
						<tr rowspan="3">
							<td valign="top">Policy:<td>
								<label context="loan" visibleWhen="#{selected.loaninfo.policy}">
									<b class="maroon">#{selected.loaninfo.policy}</b> Abs
								</label>
								<label context="loan" visibleWhen="#{!selected.loaninfo.policy}">
									<i class="maroon">Not specifed.</i>
								</label>
							</td>
							<td>Provisions:<td>
								<label context="loan" visibleWhen="#{selected.loaninfo.provisions}">
									<b class="maroon">#{selected.loaninfo.provisions}</b> Abs
								</label>
								<label context="loan" visibleWhen="#{!selected.loaninfo.provisions}">
									<i class="maroon">Not specifed.</i>
								</label>
							</td>
						</tr>
					</tr>
					<tr>
						<td>Credit Limit:</td>
						<td>
							<label context="loan" visibleWhen="#{selected.loaninfo.creditLimit}">
								Php <b class="teal">#{selected.loaninfo.creditLimit.formatDecimal()}</b>
							</label>
							<label context="loan" visibleWhen="#{!selected.loaninfo.creditLimit}">
								<b>Php <u class="maroon">00.00</u></b>
							</label>
						</td>
					</tr>
					<tr>
						<td>Increase:</td>
						<td>
							<label context="loan" visibleWhen="#{selected.loaninfo.increase}">
								Php <b class="teal">#{selected.loaninfo.increase.formatDecimal()}</b>
							</label>
							<label context="loan" visibleWhen="#{!selected.loaninfo.increase}">
								<b>Php <u class="maroon">00.00</u></b>
							</label>
						</td>
					</tr>
					<tr>
					    <td>Must Collateral(s)</td>
					    <td>
					     
					    </td>
					</tr>
				</td>
			</table>
			<br/>
			<b class="maroon">Annotation</b>
			<br/>
			<div class="sender">
				<label context="loan">
					posted by #{selected.approvalAnnotation.author? selected.approvalAnnotation.author : ' - '} @ 
					#{selected.approvalAnnotation.date? selected.approvalAnnotation.date : ' - '}
				</label>
				<!--posted by ronald @ 15:30:30-->
			</div>
			<div>
				<label context="loan" visibleWhen="#{selected.loaninfo.approvalAnnotation.length>0}"/>
					<span>
						<pre>#{selected.loaninfo.approvalAnnotation.toUpperCase()}</pre>
					</span>
				</label><br/>
				<!--This message is for testing purpose(s) only.-->
			</div>
		</fieldset>
		<fieldset context="loan" visibleWhen="#{selected.loaninfo.offeredamount}">
			<table>
				<legend>
					<h3 class="navy">LOAN OFFER</h3>
				</legend>
				    <td valign="top" style="padding-left:15px; width:100px">
				    <tr>
					    <td>Amount Offered:</td>
					    <td>
						    <label context="loan" visibleWhen="#{selected.loaninfo.offeredamount.formatDecimal()}">
							    Php <b class="teal">#{selected.loaninfo.offeredamount.formatDecimal()}</b>
						    </label>
						    <label context="loan" visibleWhen="#{!selected.loaninfo.offeredamount}">
							    <b>Php <u class="maroon">00.00</u></b>
						    </label>
					    </td>
				    </tr>
				    <tr>
					    <td valign="top">Absences</td>
					    <tr>
						    <td valign="top">Policy:<td>
							    <label context="loan" visibleWhen="#{selected.loaninfo.offeredpolicy}">
								    <b class="maroon">#{selected.loaninfo.offeredpolicy}</b> Abs
							    </label>
							    <label context="loan" visibleWhen="#{!selected.loaninfo.offeredpolicy}">
								    <i class="maroon">Not specifed.</i>
							    </label>
						    </td>
						    <td>Provisions:<td>
							    <label context="loan" visibleWhen="#{selected.loaninfo.offeredprovisions}">
								    <b class="maroon">#{selected.loaninfo.offeredprovisions}</b> Abs
							    </label>
							    <label context="loan" visibleWhen="#{!selected.loaninfo.offeredprovisions}">
								    <i class="maroon">Not specifed.</i>
							    </label>
						    </td>
					    </tr>
				    </tr>
				    <tr>
					    <td>Credit Limit:</td>
					    <td>
						    <label context="loan" visibleWhen="#{selected.loaninfo.offeredcreditLimit}">
							    Php <b class="teal">#{selected.loaninfo.offeredcreditLimit.formatDecimal()}</b>
						    </label>
						    <label context="loan" visibleWhen="#{!selected.loaninfo.offeredcreditLimit}">
							    <b>Php <u class="maroon">00.00</u></b>
						    </label>
					    </td>
				    </tr>
				    <tr>
					    <td>Increase:</td>
					    <td>
						    <label context="loan" visibleWhen="#{selected.loaninfo.offeredincrease}">
							    Php <b class="teal">#{selected.loaninfo.offeredincrease.formatDecimal()}</b>
						    </label>
						    <label context="loan" visibleWhen="#{!selected.loaninfo.offeredincrease}">
							    <b>Php <u class="maroon">00.00</u></b>
						    </label>
					    </td>
				    </tr>
			    </td>
			</table>
			<br/>
			<b class="maroon">Annotation</b>
			<br/>
			<div class="sender">
				<label context="loan">
					posted by #{selected.offeralAnnotation.author? selected.offeralAnnotation.author : ' - '} @ 
					#{selected.offeralAnnotation.date? selected.offeralAnnotation.date : ' - '}
				</label>
				<!--posted by ronald @ 15:30:20-->
			</div>
			<div>
				<label context="loan" visibleWhen="#{selected.loaninfo.offeralAnnotation.length>0}"/>
					<span>
						<pre>#{selected.loaninfo.offeralAnnotation.toUpperCase()}</pre>
					</span>				
				</label>
					<br/>
					<!--This message is for sample purpose(s) only.-->
			</div>
		</fieldset>
	</div>
</div>
<br/>	
<label context="loan" class="box tab-header">
	<a context="loan" name="showCommentTab" class="#{commentTab == 'comment'? 'selected' : ''}">COMMENT</a>
	<a context="loan" name="showRecommendationTab" class="#{commentTab == 'recommendation'? 'selected' : ''}">CI RECOMMENDATION</a>
	<a context="loan" name="showCrecomRecommendationTab" class="#{commentTab == 'crecomrecommendation'? 'selected' : ''}">CRECOM RECOMMENDATION</a>
	<a context="loan" name="showPrevFLATab" class="#{commentTab == 'prevfla'? 'selected' : ''}">Previous FLA</a>
</label>
<div class="tab-content">
	<table>
		<td>
		    <!-- Comments -->
			<div context="loan" visibleWhen="#{!selected.comments}">
				<i class="maroon">No comments available yet.</i>
			</div>
			<div context="loan" visibleWhen="#{commentTab == 'comment'}">
				<div context="loan" visibleWhen="#{selected.state == 'FOR_APPROVAL'}">
					<a href="#" context="loan" name="addComment">Add Comment</a>
					<br/>
				</div>
				<br/>
				<ul context="loan" items="selected.comments" name="comments" class="list">
					<li>
						<div class="sender">
							posted by #{author} @ #{date}
						</div>
						<div>
							#{remarks}
						</div>
						<br/>
					</li>
				</ul>
			</div>
			<!-- CI Recommendation -->
			<div context="loan" visibleWhen="#{commentTab == 'recommendation'}">
				<div context="loan" visibleWhen="#{!selected.cirecommendation}">
					<i class="maroon">No CI Recommendations yet.</i>
				</div>
				<div class="sender" WRAP="SOFT">
					<br/>
					<label context="loan" visibleWhen="#{selected.cirecommendation.author.length >0 
						&& selected.cirecommendation.date.length >0}">
						posted by #{selected.cirecommendation.author} @ #{selected.cirecommendation.date}
					</label>
				</div>
				<div>
					<label context="loan"visibleWhen="#{selected.cirecommendation.remarks.length >0}"/>
						<span>
							<pre>#{selected.cirecommendation.remarks}</pre>
						</span>
					</label>
				</div>
			</div>
			<!-- Crecom Recommendation -->    
			<div context="loan" visibleWhen="#{commentTab == 'crecomrecommendation'}">
				<div>
					<div>
						<div class="background" style="position: relative; width: 728px; height: 200px;">
							<div style="position: absolute; top: 10px; left: 3px; width: 150px; height: 150px;  float:left"
								context="loan" visibleWhen="#{selected.state == 'FOR_APPROVAL'}">
								<fieldset> <!-- style="padding-right:0px"-->
										<legend class="green">FLA DECISIONS</legend>
									<ul>
										<li>
											<a href="#" context="loan" name="approve" value="Approve">
												Approve
											</a>
										</li>
										<li>
											<a href="#" context="loan" name="sendBack" value="Send Back">
												Send Back
											</a>
										</li>
										<li>
											<a href="#" context="loan" name="disapprove" value="Disapprove">
												Disapprove
											</a>
										</li>
										<li>
											<a href="#" context="loan" name="disqualifiedOut" value="Disqualified Out">
												Disqualified-Out
											</a>
										</li>
										<li>
											<a href="#" context="loan" name="canceledOut" value="Canceled Out">
												Cancelled-Out
											</a>
										</li>
									</ul>
								</fieldset>
							</div>
							<div style="position: absolute; top: 10px; left: 507px; width: 210px; height: 150px;  float:right">
								<fieldset   context="loan" visibleWhen="#{selected.marketerRecomAmt}">
									<legend class="green">OTHER RECOMMENDATIONS</legend>
									<div align="right">
										<j context="loan" visibleWhen="#{selected.marketerRecomAmt || 
											selected.ciRecomAmt || selected.caoRecomAmt || selected.fcaRecomAmt ||
												selected.bcohRecomAmt}">
											<b context="loan" visibleWhen="#{!selected.marketerRecomAmt}">Marketers:</b>
											<label context="loan" visibleWhen="#{!selected.marketerRecomAmt}">
												<b class="black">Php</b> <b class="teal">0.00</b>
											</label>
											<b context="loan" visibleWhen="#{selected.marketerRecomAmt}">Marketers:</b>
											<label context="loan" visibleWhen="#{selected.marketerRecomAmt}">
												<b class="dKhaki">
													<b class="black">Php</b> <b class="maroon">
													#{selected.marketerRecomAmt.formatDecimal()}</b>
												</b>
											</label>
											<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<b context="loan" visibleWhen="#{!selected.ciRecomAmt}">CI:</b>
											<label context="loan" visibleWhen="#{!selected.ciRecomAmt}">
												<b class="black">Php</b> <b class="teal">0.00</b>
											</label>
											<b context="loan" visibleWhen="#{selected.ciRecomAmt}">CI:</b>
											<label context="loan" visibleWhen="#{selected.ciRecomAmt}">
												<b class="black">Php</b> <b class="maroon">
												#{selected.ciRecomAmt.formatDecimal()}</b>
											</b></label>
											<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<b context="loan" visibleWhen="#{!selected.fcaRecomAmt}">FCA:</b>
											<label context="loan" visibleWhen="#{!selected.fcaRecomAmt}">
												<b class="black">Php</b> <b class="teal">0.00</b>
											</label>
											<b context="loan" visibleWhen="#{selected.fcaRecomAmt}">FCA:</b>
											<label context="loan" visibleWhen="#{selected.fcaRecomAmt}">
												<b class="black">Php</b> <b class="maroon">
												#{selected.fcaRecomAmt.formatDecimal()}</b>
											</b></label>
											<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<b context="loan" visibleWhen="#{!selected.caoRecomAmt}">CAO:</b>
											<label context="loan" visibleWhen="#{!selected.caoRecomAmt}">
												<b class="black">Php</b> <b class="teal">0.00</b>
											</label>
											<b context="loan" visibleWhen="#{selected.caoRecomAmt}">CAO:</b>
											<label context="loan" visibleWhen="#{selected.caoRecomAmt}">
												<b class="black">Php</b> <b class="maroon">
												#{selected.caoRecomAmt.formatDecimal()}</b>
											</b></label>
											<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<b context="loan" visibleWhen="#{!selected.bcohRecomAmt}">BCOH:</b>
												<label context="loan" visibleWhen="#{!selected.bcohRecomAmt}"><b class="dKhaki">
													<b class="black">Php</b> <b class="teal">0.00</b>
												</label>
											</b>
											<b context="loan" visibleWhen="#{selected.bcohRecomAmt}">BCOH:</b>
											<label context="loan" visibleWhen="#{selected.bcohRecomAmt}"><b class="dKhaki">
												<b class="black">Php</b> <b class="maroon">
												#{selected.bcohRecomAmt.formatDecimal()}</b>
											</b></label>
										</j>
									</div>
								</fieldset>
								<i  context="loan" visibleWhen="#{!selected.marketerRecomAmt}" class="maroon">No Other Recommendations.</i>
							</div>
						</div> <!--end of containing div class="background"-->
					</div>
					<div context="loan" visibleWhen="#{!selected.crecomrecommendation}" align="center">
						<i class="maroon">No Crecom Recommendations yet.</i>
					</div>
					<div>
						<div class="sender">
							<label context="loan" visibleWhen="#{selected.crecomrecommendation.author.length >0 
								&& selected.crecomrecommendation.date.length >0}" class="sender">
								posted by #{selected.crecomrecommendation.author} @ #{selected.crecomrecommendation.date}
							</label>
						</div>
						<!--<br>-->
						<div>
							<label context="loan"visibleWhen="#{selected.crecomrecommendation.remarks.length >0}"/>
								<span>
									<pre>#{selected.crecomrecommendation.remarks}</pre>
								</span>
							</label>
						</div>
					</div>
				</div>
			</div>
			<!-- Previous FLA -->
			<div context="loan" visibleWhen="#{commentTab == 'prevfla'}">
				<div context="loan" visibleWhen="#{!selected.prevFLA.amountapproved}">
					<i class="maroon">No previous FLA record yet.</i>
				</div>
				<div context="loan" visibleWhen="#{selected.prevFLA}">
					<div context="loan" visibleWhen="#{selected.prevFLA.loanApproval}" style="padding: 10px 0px;">
					<!-- Loan Approval -->
					<fieldset>
							<table>
								<legend><h3 class="green">LOAN APPROVAL</h3></legend>
								<td valign="top" style="padding-left:15px; width:100px">
									<tr>
										<td>Amount Approved:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.amountapproved.formatDecimal()}">
												Php <b class="dKhaki">#{selected.prevFLA.amountapproved.formatDecimal()}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.amountapproved}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
									<tr>
										<td valign="top">Absences</td>
										<tr>
											<td valign="top">Policy:<td>
												<label context="loan" visibleWhen="#{selected.prevFLA.policy}">
													<b>#{selected.prevFLA.policy}</b>
												</label>
												<label context="loan" visibleWhen="#{!selected.prevFLA.policy}">
													<i class="maroon">Not specifed.</i>
												</label>
											</td>
											<td>Provisions:<td>
												<label context="loan" visibleWhen="#{selected.prevFLA.provisions}">
													<b>#{selected.prevFLA.provisions}</b>
												</label>
												<label context="loan" visibleWhen="#{!selected.prevFLA.provisions}">
													<i class="maroon">Not specifed.</i>
												</label>
											</td>
										</tr>
									</tr>
									<tr>
										<td>Credit Limit:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.creditLimit}">
												Php <b class="dKhaki">#{selected.prevFLA.creditLimit}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.creditLimit}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
									<tr>
										<td>Increase:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.increase}">
												Php <b class="dKhaki">#{selected.prevFLA.increase}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.increase}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
									<tr>
						                <td>Must Collateral(s)</td>
						                <td>
						                 
						                </td>
						            </tr>
								</td>
							</table>
							<b class="maroon">Annotation</b>
							<br/>
							<div class="sender">
								<label context="loan">
									posted by #{selected.prevFLA.approvalAnnotation.author? 
									selected.prevFLA.approvalAnnotation.author : ' - '} @ 
									#{selected.prevFLA.approvalAnnotation.date? selected.prevFLA.approvalAnnotation.date : ' - '}
								</label>
							</div>
							<div>
								<label context="loan" visibleWhen="#{#{selected.prevFLA.annotation.remarks.length>0}"/>
									<span>
										<pre>#{selected.prevFLA.annotation.remarks}</pre>
									</span>
								</label>
							</div>
						</div>
					</fieldset>
					<!-- Loan Offer -->
					<div context="loan" visibleWhen="#{selected.prevFLA.loanOffer}" style="padding: 10px 0px;">
						<fieldset>
							<table>
								<legend><h3 class="navy">LOAN OFFER</h3></legend>
								<td valign="top" style="padding-left:15px; width:100px">
									<tr>
										<td>Amount Offered:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.offeredamount.formatDecimal()}">
												Php <b class="dKhaki">#{selected.prevFLA.offeredamount.formatDecimal()}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.offeredamount}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
									<tr>
										<td valign="top">Absences</td>
										<tr>
											<td valign="top">Policy:<td>
												<label context="loan" visibleWhen="#{selected.prevFLA.offeredpolicy}">
													Php <b class="dKhaki">#{selected.prevFLA.offeredpolicy}</b>
												</label>
												<label context="loan" visibleWhen="#{!selected.prevFLA.offeredpolicy}">
													<i class="maroon">Not specifed.</i>
												</label>
											</td>
											<td>Provisions:<td>
												<label context="loan" visibleWhen="#{selected.prevFLA.offeredprovisions}">
													Php <b class="dKhaki">#{selected.prevFLA.offeredprovisions}</b>
												</label>
												<label context="loan" visibleWhen="#{!selected.prevFLA.offeredprovisions}">
													<i class="maroon">Not specifed.</i>
												</label>
											</td>
										</tr>
									</tr>
									<tr>
										<td>Credit Limit:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.offeredcreditLimit}">
												Php <b class="dKhaki">#{selected.prevFLA.offeredcreditLimit}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.offeredcreditLimit}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
									<tr>
										<td>Increase:</td>
										<td>
											<label context="loan" visibleWhen="#{selected.prevFLA.offeredincrease}">
												Php <b class="dKhaki">#{selected.prevFLA.offeredincrease}</b>
											</label>
											<label context="loan" visibleWhen="#{!selected.prevFLA.offeredincrease}">
												<b>Php <u class="maroon">00.00</u></b>
											</label>
										</td>
									</tr>
								</td>
							</table>
							<b class="maroon">Annotation</b>
							<br/>
							<div class="sender">
								<label context="loan">
									posted by #{selected.prevFLA.offeralAnnotation.author?
									selected.prevFLA.offeralAnnotation.author : ' - '} @ 
									#{selected.prevFLA.offeralAnnotation.date? selected.prevFLA.offeralAnnotation.date : ' - '}
								</label>
							</div>
							<div>
								<label context="loan" visibleWhen="#{#{selected.prevFLA.offeralAnnotation.remarks.length>0}"/>
									<span>
										<pre>#{selected.prevFLA.offeralAnnotation.remarks}</pre>
									</span>
								</label>
							</div>
							<br/>
						</div>
					</fieldset>
				</div>
			</div>
		</td>
	</table>
</div>
