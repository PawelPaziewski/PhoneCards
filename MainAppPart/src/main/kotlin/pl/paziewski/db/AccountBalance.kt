package pl.paziewski.db

import org.springframework.data.annotation.Id
import java.math.BigDecimal

data class AccountBalance(
    @Id
    val phoneNumber: String,
    var balance: BigDecimal
)
