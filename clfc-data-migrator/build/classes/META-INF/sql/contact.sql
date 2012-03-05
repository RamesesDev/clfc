[insert]
  insert into clfc_contact.contact
  (
    objid,
    contactno,
    clientcode,
    dtfiled,
    schemaname,
    version,
    info,
    state
  )
  values
  (
    $P{objid},
    $P{contactno},
    $P{clientcode},
    $P{dtfiled},
    $P{schemaname},
    $P{version},
    $P{info},
    $P{state}
  )


[insert_name_idx]
  insert into clfc_contact.contact_name_index
  (
    objid,
    contactno,
    firstname,
    lastname,
    middlename,
    birthdate
  )
  values
  (
    $P{objid},
    $P{contactno},
    $P{firstname},
    $P{lastname},
    $P{middlename},
    $P{birthdate}
  )


[get_customer]
  select * from clfc_contact.contact where clientcode=?

