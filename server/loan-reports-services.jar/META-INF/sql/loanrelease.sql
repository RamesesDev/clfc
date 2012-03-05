 select 
	objid, appno, routecode, routedescription, loancount,
	fullborrowername as borrowername, borrowername as borrowername2,
	ledger, extended
 from
	loanapplication
 where
	dtreleased >= $P{dtfrom} and dtreleased <= $P{dtto}
	${filter}
