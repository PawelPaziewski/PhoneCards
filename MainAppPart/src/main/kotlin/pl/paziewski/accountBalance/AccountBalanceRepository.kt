package pl.paziewski.accountBalance

import org.springframework.data.repository.CrudRepository

interface AccountBalanceRepository : CrudRepository<AccountBalance, String>