package pl.paziewski

import java.math.BigDecimal
import java.time.Duration

class BuyCardCommand(val initialMoney: BigDecimal, val firstName: String, val lastName: String)

class MakePhoneCallCommand(val callerPhoneNumber: String, val receiverPhoneNumber: String, val callDuration: Duration)

class TopUpCardCommand(val phoneNumber: String, val amount: BigDecimal)

class SendSmsCommand(val senderPhoneNumber: String, val receiverPhoneNumber: String)