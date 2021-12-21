package pl.paziewski

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal
import java.time.LocalDateTime
import org.axonframework.commandhandling.model.AggregateLifecycle.apply as applyEvent

@Aggregate
class PhoneCard() {

    @AggregateIdentifier
    lateinit var phoneNumber: String
    lateinit var moneyOnAccount: BigDecimal
    lateinit var owner: CardOwner

    @CommandHandler
    constructor(command: BuyCardCommand, validator: PhoneCardValidator, numberProvider: PhoneNumberProvider) : this() {
        if (!validator.isValidCommand(command)) {
            throw IllegalArgumentException("Initial money cannot be less then zero")
        }
        applyEvent(
            CardBoughtEvent(
                numberProvider.getNumber(),
                command.initialMoney,
                command.owner,
                LocalDateTime.now()
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CardBoughtEvent) {
        phoneNumber = event.phoneNumber
        moneyOnAccount = event.initialMoney
        owner = event.owner
    }

    @CommandHandler
    fun handle(command: MakePhoneCallCommand, validator: PhoneCardValidator, costCalculator: CallCostCalculator) {
        if (!validator.isValidCommandAndState(command, this)) {
            throw IllegalStateException("Could not make call because of no money on account")
        }
        applyEvent(
            PhoneCallMadeEvent(
                phoneNumber,
                command.receiverPhoneNumber,
                command.callDuration,
                costCalculator.calculate(command.callDuration),
                LocalDateTime.now()
            )
        )
    }

    @EventSourcingHandler
    fun on(event: PhoneCallMadeEvent) {
        moneyOnAccount -= event.callCost
    }

    @CommandHandler
    fun handle(command: SendSmsCommand, validator: PhoneCardValidator, costCalculator: SmsCostCalculator) {
        if (!validator.isValidCommandAndState(command, this)) {
            throw IllegalStateException("Could not make call because of no money on account")
        }
        applyEvent(
            SmsSentEvent(
                phoneNumber,
                command.receiverPhoneNumber,
                costCalculator.calculate(),
                LocalDateTime.now()
            )
        )
    }

    @EventSourcingHandler
    fun on(event: SmsSentEvent) {
        moneyOnAccount -= event.cost
    }

    @CommandHandler
    fun handle(command: TopUpCardCommand, validator: PhoneCardValidator) {
        if (!validator.isValidCommand(command)) {
            throw IllegalArgumentException("Cannot top up account with amount less or equal then zero")
        }
        applyEvent(CardTopUpEvent(phoneNumber, command.amount, LocalDateTime.now()))
    }

    @EventSourcingHandler
    fun on(event: CardTopUpEvent) {
        moneyOnAccount += event.amount
    }
}