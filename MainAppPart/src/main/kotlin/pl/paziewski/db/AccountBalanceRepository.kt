package pl.paziewski.db

import org.springframework.data.repository.CrudRepository

interface AccountBalanceRepository : CrudRepository<AccountBalance, String>