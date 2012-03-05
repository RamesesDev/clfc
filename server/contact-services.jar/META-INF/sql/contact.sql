[getNextContactno]
select contactno from contactno_keygen

[updateContactno]
update contactno_keygen set contactno=contactno+1

[updateContact]
update contact set info=$P{info},dtmodified=$P{dtmodified},modifiedby=$P{modifiedby} where objid=$P{objid}

[findByName]
select * from contact_name_index where lastname like CONCAT(SUBSTR($P{lastname},1,2), '%')

[findByContactno]
select * from contact_name_index where contactno =$P{contactno}

[findConnections]
select a.objid,r.relationship, concat(a.lastname, ', ', a.firstname, ' ', coalesce(a.middlename, '')) as name 
from contact_name_index a, social_connection r 
where r.relater = a.objid and r.principal = $P{principal}

[getChangeHistoryList]
select objid,dtmodified,modifiedby,reason from contact_change_hist where contactid=$P{contactid} order by dtmodified desc

[getChangeHistoryInfo]
select info,changeinfo,reason from contact_change_hist where objid=$P{objid}