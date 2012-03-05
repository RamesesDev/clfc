<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common" %>
<%@ page import="java.util.*"%>
<%@ page import="com.rameses.web.support.*" %>

<script>
	$put(
		"loan_list", 
		new function() {
			
			var listSvc = ProxyService.lookup('LoanApplicationSearchService');
			var allowedStates;
			var _self = this;
			
			this._controller;
			this.selected;

			this.stateList = [
				{code: 'FOR_APPROVAL', caption: 'For Approval'},
				{code: 'APPROVED', caption: 'Approved'},
				{code: 'FOR_FLA', caption: 'For FLA'},
				{code: 'DISAPPROVED', caption: 'Disapproved'},
				{code: 'CANCELED_OUT', caption: 'Canceled Out'},
				{code: 'DISQUALIFIED_OUT', caption: 'Disqualifed Out'},
				{code: 'RELEASED', caption: 'Released'}
			];
			
			this.selected;
			this.state;
			this.filter = {};
			
			
			this.onload = function() {
				allowedStates = [];
				this.stateList.each(function(o){ allowedStates.push( o.code ); });
			};
			
			this.listModel = {
				rows: 10,
				fetchList: function( p ) {
					p.allowedStates = allowedStates;
					p.state = _self.state;
					p.searchText = _self.filter.text;
					p.branchcode = _self.filter.branchcode;
					return listSvc.getList( p );
				}
			};
			
			this.search = function() {
				this.listModel.load();
			};
			
			this.open = function() {
				if( !this.selected ) return;
				
				return new DocOpener('loan:form', {objid: this.selected.objid, list_state: this.state});
			};
			
		}
	);
	
</script>

<script>
	$(function(){
		$('input[name^="filter"],select[name^="filter"]').keypress(function(e){ 
			if( e.keyCode == 13 ) {
				$(this).trigger('change');
				$('input[name="search"]').trigger('click'); 
			}
		});
		$('input[name="filter.branchcode"]').focus();
	});
</script>

<div class="doc-title">
	Loan Applications
</div>
<div>
	State:
	<label context="loan_list"><b>#{state}</b></label>
	<br/>
	<input type="text"   context="loan_list" name="filter.branchcode" hint="Branch Code" size="11"/>
	<input type="text"   context="loan_list" name="filter.text" hint="Type Application No./Borrower Name" size="50"/>
	<input type="button" context="loan_list" name="search" value="Search" />
</div>
<br/>
<table context="loan_list" name="selected" model="listModel"
       border="0" cellspacing="0"
       width="100%" varStatus="stat" class="grid">
	<thead>
		<tr>
			<th width="100px">App No.</th>
			<th>Borrower Name</th>
			<th>Amount Applied</th>
			<!--<th>Date Submitted</th>
			<th>Date Approved</th>
			<th>Branch Name</th>-->
			<th width="100px">Branch Code</th>
			<th width="150px">State</th>
		</tr>
	</thead>
	<tbody class="selectable">
		<tr class="#{stat.index%2 == 0? 'even' : 'odd'}" onclick="$get('loan_list').invoke(this, 'open');">
			<td align="center">
				<b>#{appno}</b>
			</td>
			<td>#{fullborrowername}</td>
			<td align="right">#{amountApplied.formatDecimal()}</td>
			<!--<td align="center"><b class="gray">#{dtsubmittedforapproval? dtsubmittedforapproval: '-'}</b></td>
			<td align="center"><b class="gray">#{approvedate? approvedate: '-'}</b></td>
			<td align="center">#{branchname? branchname: '-'}</td>-->
			<td align="center">#{branchcode}</td>
			<td align="center">#{state}</td>
		</tr>
	</tbody>
	<tfoot>
		<tr class="control">
			<td colspan="5" align="right">
				<input type="button" context="loan_list" name="listModel.moveFirst" value="First"/>
				<input type="button" context="loan_list" name="listModel.movePrev" value="Previous"/>
				<input type="button" context="loan_list" name="listModel.moveNext" value="Next"/>
			</td>
		</tr>
	</tfoot>
</table>
