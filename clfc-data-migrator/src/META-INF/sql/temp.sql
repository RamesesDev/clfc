#--- CLIENT DATA ----------------
[get_client_list]
  select * from client


#--- LOAN DATA ------------------
[get_loan_list]
  select * from loan where loantype = 'D'

