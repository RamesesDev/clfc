[get-by-refid]
select objid from check_voucher where refid=$P{refid}

[get-details]
select * from check_voucher_detail where parentid=?

