package pl.paziewski

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.paziewski.billing.BillingEntry
import java.math.BigDecimal
import java.time.Duration
import java.util.*

@RestController
class MainController @Autowired constructor(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @GetMapping("/cardBalance")
    fun getCardBalance(@RequestParam(name = "phoneNumber", required = true) phoneNumber: String): Optional<Money> {
        return queryGateway
            .query(GetAccountBalanceQuery(phoneNumber), ResponseTypes.optionalInstanceOf(Money::class.java))
            .get()
    }

    @PutMapping("/buyPhoneCard")
    fun buyPhoneCard(
        @RequestParam(required = true, name = "initialMoney") initialMoneyAmount: BigDecimal,
        @RequestParam(required = true, name = "buyerFirstName") firstName: String,
        @RequestParam(required = true, name = "buyerLastName") lastName: String
    ): String {
        return commandGateway.sendAndWait(BuyCardCommand(initialMoneyAmount, CardOwner(firstName, lastName)))
    }

    @PutMapping("/makePhoneCall")
    fun makePhoneCall(
        @RequestParam(required = true, name = "callerPhoneNumber") caller: String,
        @RequestParam(required = true, name = "receiverPhoneNumber") receiver: String,
        @RequestParam(required = true, name = "callDuration") callDuration: Duration
    ) {
        commandGateway.send<Any>(MakePhoneCallCommand(caller, receiver, callDuration))
    }

    @PutMapping("/topUpCard")
    fun topUpCard(
        @RequestParam(required = true, name = "phoneNumber") phoneNumber: String,
        @RequestParam(required = true, name = "amount") amount: BigDecimal
    ) {
        commandGateway.send<Any>(TopUpCardCommand(phoneNumber, amount))
    }

    @PutMapping("/sendSms")
    fun sendSms(
        @RequestParam(required = true, name = "senderPhoneNumber") sender: String,
        @RequestParam(required = true, name = "receiverPhoneNumber") receiver: String
    ) {
        commandGateway.send<Any>(SendSmsCommand(sender, receiver))
    }

    @GetMapping("/billing")
    fun getBilling(@RequestParam(name = "phoneNumber", required = true) phoneNumber: String): List<BillingEntry> =
        queryGateway.query(GetBillingQuery(phoneNumber),
            ResponseTypes.multipleInstancesOf(BillingEntry::class.java)).get()
}