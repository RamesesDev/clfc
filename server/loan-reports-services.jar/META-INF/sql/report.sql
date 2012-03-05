[get-remitted-collection]
  select 
    rm.extended as rm_info,
    r.receiptno, r.amount as total, r.payerid, r.payername, r.dtfiled, r.info as receipt_info,
    ri.acctcode, ri.accttitle, ri.amount, ri.info 
  from 
    remittance rm, cash_receipt r, cash_receipt_detail ri
  where 
        rm.dtfiled > $P{datefrom} 
    and rm.dtfiled < $P{dateto} 
    and rm.type = 'loan_collection'
    and r.remittanceid = rm.objid
    and ri.parentid = r.objid

