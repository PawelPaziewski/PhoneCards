package pl.paziewski

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime

abstract class AbstractEvent(
    val timestamp: LocalDateTime
)

class CardBoughtEvent(
    val phoneNumber: String,
    val initialMoney: BigDecimal,
    val firstName: String,
    val lastName: String,
    timestamp: LocalDateTime
) : AbstractEvent(timestamp)

class PhoneCallMadeEvent(
    val callerPhoneNumber: String,
    val receiverPhoneNumber: String,
    val callDuration: Duration,
    val callCost: BigDecimal,
    timestamp: LocalDateTime
) : AbstractEvent(timestamp)

class CardTopUpEvent(
    val phoneNumber: String,
    val amount: BigDecimal,
    timestamp: LocalDateTime
) : AbstractEvent(timestamp)

class SmsSentEvent(
    val senderPhoneNumber: String,
    val receiverPhoneNumber: String,
    val cost: BigDecimal,
    timestamp: LocalDateTime
) : AbstractEvent(timestamp)
