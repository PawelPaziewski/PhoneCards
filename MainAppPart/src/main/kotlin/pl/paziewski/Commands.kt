package pl.paziewski

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.Duration

class BuyCardCommand(val initialMoney: BigDecimal, val owner: CardOwner)

class MakePhoneCallCommand(
    @TargetAggregateIdentifier val callerPhoneNumber: String,
    val receiverPhoneNumber: String,
    val callDuration: Duration
)

class TopUpCardCommand(
    @TargetAggregateIdentifier val phoneNumber: String,
    val amount: BigDecimal
)

class SendSmsCommand(
    @TargetAggregateIdentifier val senderPhoneNumber: String,
    val receiverPhoneNumber: String
)