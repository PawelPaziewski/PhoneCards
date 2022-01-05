package pl.paziewski

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Duration

class CallCost {
    companion object {
        val minimalDuration: Duration = Duration.ofSeconds(3)
        val cost = Money.withDefaultCurrency().fromAmountAsString("0.01")
    }
}

interface CallCostCalculator {
    fun calculate(caller: String, receiver: String, callDuration: Duration): Money
}

@Component
class SimpleCallCostCalculator : CallCostCalculator {
    override fun calculate(caller: String, receiver: String, callDuration: Duration) =
        if (samePhoneProvider(caller, receiver)) {
            BigDecimal.ZERO.asMoneyWithLocalCurrency()
        } else {
            CallCost.cost.multiply(callDuration.dividedBy(CallCost.minimalDuration))
        }

    private fun samePhoneProvider(caller: String, receiver: String) = caller.substring(0, 2) == receiver.substring(0, 2)
}