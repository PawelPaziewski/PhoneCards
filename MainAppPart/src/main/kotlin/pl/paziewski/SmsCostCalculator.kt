package pl.paziewski

import org.springframework.stereotype.Component
import java.math.BigDecimal

interface SmsCostCalculator {
    fun calculate(): Money
}

@Component
class SimpleSmsCostCalculator : SmsCostCalculator {
    override fun calculate(): Money = BigDecimal.ONE.asMoneyWithLocalCurrency()
}