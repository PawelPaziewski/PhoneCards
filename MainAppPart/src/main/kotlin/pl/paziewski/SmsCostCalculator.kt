package pl.paziewski

import org.springframework.stereotype.Component
import java.math.BigDecimal

interface SmsCostCalculator {
    fun calculate(): BigDecimal
}

@Component
class SimpleSmsCostCalculator : SmsCostCalculator {
    override fun calculate(): BigDecimal = BigDecimal.ONE
}