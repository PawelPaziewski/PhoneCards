package pl.paziewski

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Duration

interface CallCostCalculator {
    fun calculate(callDuration: Duration): BigDecimal
}

@Component
class SimpleCallCostCalculator : CallCostCalculator {
    override fun calculate(callDuration: Duration): BigDecimal = BigDecimal.valueOf(callDuration.toMinutes())
}