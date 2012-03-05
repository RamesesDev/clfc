[find-app]
 select 
 	objid,appno,fullborrowername,borrowername,borrower,
 	routecode,routedescription,state,dtreleased,ledger,loaninfo 
 from 
 	loanapplication
 where
 	${filter}
 order by state,appno

 
[find-by-borrowername]
 select 
	i.custid, i.contactno, i.name, i.firstname, i.lastname, i.middlename, i.borrowertype,
 	a.objid,a.appno,a.fullborrowername,a.borrowername,a.borrower,
 	a.routecode,a.routedescription,a.state,a.dtreleased,a.ledger,a.loaninfo 
 from 
 	loan_borrower_index i, loanapplication a
 where 
	a.objid = i.appid
	and name like $P{name}
	${filter}
 group by a.objid
 order by state,appno



