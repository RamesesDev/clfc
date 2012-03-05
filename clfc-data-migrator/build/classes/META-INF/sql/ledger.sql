[get_unprocessed_list]
  select 
	l.objid, l.appno, l.loanno, l.loaninfo, l.ledger, 
        l.state, l.dtreleased, l2.dtreleased as nextrelease
  from  clfc_loan.loanapplication l
  left  join clfc_loan.loanapplication l2 
        on l2.loanno = l.loanno and l2.dtreleased > l.dtreleased
  where l.loanno is not null
  order by l.appno


[update_processed]
  update clfc_loan.loanapplication 
  set state = 'PENDING',
      ledger = $P{ledger}
  where objid = $P{objid}


[get_payments]
  select 
    * 
  from  clfc_loan.loan_ledger_item 
  where appid = $P{loanno} or appid = ${objid} 
        ${FILTER}
  order by date


[update_payment_pbalance]
  update clfc_loan.loan_ledger_item 
  set principal_balance = $P{principal_balance},
      extended = '[isMigrated: true]'
  where objid = $P{objid}


[update_payments_appid]
  update clfc_loan.loan_ledger_item 
  set    appid = $P{appid}
  where  appid = $P{loanno}
         ${FILTER}




