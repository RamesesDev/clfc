[get_applications]
 select objid, appno, loanno from clfc_loan.loanapplication

[migrate]
 insert ignore into clfc_loan.loan_ledger_item
 (
	appid, date, refno, principal, principal_balance,
	interest, absent_penalty, underpayment_penalty,
	state, payment, paymentdate, extended, pastdue
 )
 select 
	l.LOANNOS as appid,
	l.ORDATE as date,
	l.ORNUMBER as refno,
	sum(ifnull(l.PRINCIPAL,0.00)) as principal,
	0.00 as principal_balance,
	sum(ifnull(l.INTEREST,0.00)) as interest,
	sum(ifnull(l.PENALTY,0.00)) as absent_penalty,
	0.00 as underpayment_penalty,
	'' as state,
	sum(ifnull(l.PRINCIPAL,0.00)) + sum(ifnull(l.INTEREST,0.00)) + sum(ifnull(l.PENALTY,0.00)) as payment,
	l.RECDATE as paymentdate,
	'[:]' as extended,
	0.00 as pastdue
 from 
	ld l
 where
	l.loannos = $P{loanno} and
	l.ORDATE is not null
 group by l.ORNUMBER
 order by l.ORDATE;