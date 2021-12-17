package pl.paziewski

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.Duration

@RestController()
class MainController {

    @GetMapping("/cardBalance")
    fun getCardBalance(@RequestParam(name = "phoneNumber", required = true) phoneNumber: String) {
    }

    @PutMapping("/buyPhoneCard")
    fun buyPhoneCard(
        @RequestParam(required = true, name = "initialMoney") initialMoneyAmount: BigDecimal,
        @RequestParam(required = true, name = "buyerFirstName") firstName: String,
        @RequestParam(required = true, name = "buyerLastName") lastName: String
    ) {
    }

    @PutMapping("/makePhoneCall")
    fun makePhoneCall(
        @RequestParam(required = true, name = "callerPhoneNumber") caller: String,
        @RequestParam(required = true, name = "receiverPhoneNumber") receiver: String,
        @RequestParam(required = true, name = "callDuration") callDuration: Duration
    ) {
    }

    @PutMapping("/topUpCard")
    fun topUpCard(
        @RequestParam(required = true, name = "phoneNumber") phoneNumber: String,
        @RequestParam(required = true, name = "amount") amount: BigDecimal
    ) {
    }

    @PutMapping("/sendSms")
    fun sendSms(
        @RequestParam(required = true, name = "senderPhoneNumber") sender: String,
        @RequestParam(required = true, name = "receiverPhoneNumber") receiver: String
    ) {
    }
}