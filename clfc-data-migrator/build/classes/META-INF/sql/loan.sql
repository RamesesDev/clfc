[insert]
  insert into clfc_loan.loanapplication
  (
    objid,
    appno,
    clientcode,
    loanno,
    borrower,
    borrowername,
    fullborrowername,
    propertylist,
    vehiclelist,
    appliancelist,
    comments,
    cirecommendation,
    loancount,
    loaninfo,
    routecode,
    routedescription,
    dtfiled,
    dtreleased,
    approvedate,
    mode,
    branchid,
    branchcode,
    extended,
    state,
    ledger
  )
  values
  (
    $P{objid},
    $P{appno},
    $P{clientcode},
    $P{loanno},
    $P{borrower},
    $P{borrowername},
    $P{fullborrowername},
    $P{propertylist},
    $P{vehiclelist},
    $P{appliancelist},
    $P{comments},
    $P{cirecommendation},
    $P{loancount},
    $P{loaninfo},
    $P{routecode},
    $P{routedescription},
    $P{dtfiled},
    $P{dtreleased},
    $P{approvedate},
    $P{mode},
    $P{branchid},
    $P{branchcode},
    $P{extended},
    $P{state},
    $P{ledger}
  )


[get_docno]
  select value from clfc_loan.loan_docno where id='loan_app'

[update_docno]
  replace into clfc_loan.loan_docno(id,value) values('loan_app', ?)




