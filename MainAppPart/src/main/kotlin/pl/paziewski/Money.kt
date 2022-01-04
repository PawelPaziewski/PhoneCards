package pl.paziewski

import java.math.BigDecimal
import java.util.*

data class Money(
    val amount: BigDecimal,
    val currency: Currency,
) {
    operator fun plus(other: Money) = Money(amount.add(other.amount), currency)
    operator fun minus(other: Money) = Money(amount.subtract(other.amount), currency)
}

fun BigDecimal.asMoneyWithLocalCurrency() = Money(this, Currency.getInstance(Locale.getDefault()))

fun Long.asMoneyWithLocalCurrency() = Money(BigDecimal.valueOf(this), Currency.getInstance(Locale.getDefault()))