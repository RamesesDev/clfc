 select 
	objid, appno, dtfiled, 
	fullborrowername as borrowername,
	routecode, routedescription,
	loaninfo,
	loancount
 from
	loanapplication
 where
	dtfiled >= $P{dtfrom} and dtfiled <= $P{dtto}
	${filter}

