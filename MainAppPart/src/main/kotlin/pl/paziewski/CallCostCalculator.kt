package pl.paziewski

import org.springframework.stereotype.Component
import java.time.Duration

interface CallCostCalculator {
    fun calculate(callDuration: Duration): Money
}

@Component
class SimpleCallCostCalculator : CallCostCalculator {
    override fun calculate(callDuration: Duration): Money = callDuration.toMinutes().asMoneyWithLocalCurrency()
}