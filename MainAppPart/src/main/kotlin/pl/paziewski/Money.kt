package pl.paziewski

import java.math.BigDecimal
import java.util.*

data class Money(
    val amount: BigDecimal,
    val currency: Currency,
) {

    init {
        if (amount.scale() > 2) {
            throw IllegalArgumentException("Money scale cannot be bigger than 2")
        }
    }

    operator fun plus(other: Money) = Money(amount.add(other.amount), currency)
    operator fun minus(other: Money) = Money(amount.subtract(other.amount), currency)
    fun multiply(multiplicand: Long) = Money(amount.multiply(multiplicand.toBigDecimal()), currency)
    fun fromAmountAsString(string: String) = Money(BigDecimal(string), currency)

    companion object {
        fun withDefaultCurrency() = Money(BigDecimal.ZERO, Currency.getInstance(Locale.getDefault()))
    }
}

fun BigDecimal.asMoneyWithLocalCurrency() = Money(this, Currency.getInstance(Locale.getDefault()))