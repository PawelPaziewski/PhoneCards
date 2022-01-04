package pl.paziewski

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class PhoneCard() {

    @AggregateIdentifier
    lateinit var phoneNumber: String
    lateinit var moneyOnAccount: Money
    lateinit var owner: CardOwner

    @CommandHandler
    constructor(command: BuyCardCommand, validator: PhoneCardValidator, numberProvider: PhoneNumberProvider) : this() {
        if (!validator.isValidCommand(command)) {
            throw IllegalArgumentException("Initial money cannot be less then zero")
        }
        apply(
            CardBoughtEvent(
                numberProvider.getNumber(),
                command.initialMoney.asMoneyWithLocalCurrency(),
                command.owner,
                DateTimeAdapter.now()
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
        apply(
            PhoneCallMadeEvent(
                phoneNumber,
                command.receiverPhoneNumber,
                command.callDuration,
                costCalculator.calculate(command.callDuration),
                DateTimeAdapter.now()
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
        apply(
            SmsSentEvent(
                phoneNumber,
                command.receiverPhoneNumber,
                costCalculator.calculate(),
                DateTimeAdapter.now()
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
        apply(CardTopUpEvent(phoneNumber, command.amount.asMoneyWithLocalCurrency(), DateTimeAdapter.now()))
    }

    @EventSourcingHandler
    fun on(event: CardTopUpEvent) {
        moneyOnAccount = moneyOnAccount.plus(event.amount)
    }
}