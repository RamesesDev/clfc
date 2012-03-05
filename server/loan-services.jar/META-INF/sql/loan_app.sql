[get-apps-for-billing]
 select objid, appno, ledger from loanapplication where state = 'RELEASED'
 
[check-borrower]
 select 
	i.custid, i.contactno, i.name, i.firstname, i.lastname, i.middlename, i.borrowertype,
 	a.objid,a.appno,a.fullborrowername,a.borrowername,a.borrower,
 	a.routecode,a.routedescription,a.state,a.dtreleased,a.ledger,a.loaninfo 
 from 
 	loan_borrower_index i, loanapplication a
 where 
	a.objid = i.appid
    and i.custid = $P{objid}
 order by state,appno
