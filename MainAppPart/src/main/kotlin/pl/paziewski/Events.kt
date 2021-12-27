package pl.paziewski

import java.math.BigDecimal
import java.time.Duration

abstract class AbstractEvent(
    val timestamp: DateTimeAdapter
)

class CardBoughtEvent(
    val phoneNumber: String,
    val initialMoney: BigDecimal,
    val owner: CardOwner,
    timestamp: DateTimeAdapter
) : AbstractEvent(timestamp)

class PhoneCallMadeEvent(
    val callerPhoneNumber: String,
    val receiverPhoneNumber: String,
    val callDuration: Duration,
    val callCost: BigDecimal,
    timestamp: DateTimeAdapter
) : AbstractEvent(timestamp)

class CardTopUpEvent(
    val phoneNumber: String,
    val amount: BigDecimal,
    timestamp: DateTimeAdapter
) : AbstractEvent(timestamp)

class SmsSentEvent(
    val senderPhoneNumber: String,
    val receiverPhoneNumber: String,
    val cost: BigDecimal,
    timestamp: DateTimeAdapter
) : AbstractEvent(timestamp)
