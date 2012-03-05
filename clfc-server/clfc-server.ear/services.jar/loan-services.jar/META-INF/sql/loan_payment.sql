[update-ledger-item]
 update loan_ledger_item
 set principal_paid = $P{principal_paid},
	 interest_paid = $P{interest_paid},
	 surcharge_paid = $P{surcharge_paid},
	 log = $P{log},
	state = $P{state}
 where appid = $P{appid} and
	   date = $P{date} and
	   type = $P{type}

[get-last-unpaid]
 select a.* from (
  select *,
	   principal_unpaid - principal_paid +
	   interest_unpaid - interest_paid +
	   surcharge_unpaid - surcharge_paid as balance
  from loan_ledger_item
  where appid=?
 )
 a where a.balance > 0
 order by a.date, a.type desc  limit 0,1
