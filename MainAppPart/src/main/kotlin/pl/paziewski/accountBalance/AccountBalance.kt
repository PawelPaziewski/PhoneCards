package pl.paziewski.accountBalance

import org.springframework.data.annotation.Id
import pl.paziewski.Money

data class AccountBalance(
    @Id
    val phoneNumber: String,
    var balance: Money
)
