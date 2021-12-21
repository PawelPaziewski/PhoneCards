package pl.paziewski

import org.springframework.stereotype.Component

interface PhoneCardValidator {
    fun isValidCommand(command: BuyCardCommand): Boolean
    fun isValidCommand(command: TopUpCardCommand): Boolean
    fun isValidCommandAndState(command: MakePhoneCallCommand, state: PhoneCard): Boolean
    fun isValidCommandAndState(command: SendSmsCommand, state: PhoneCard): Boolean
}

@Component
class PositivePhoneCardValidator : PhoneCardValidator {
    override fun isValidCommand(command: BuyCardCommand) = true
    override fun isValidCommand(command: TopUpCardCommand) = true
    override fun isValidCommandAndState(command: SendSmsCommand, state: PhoneCard) = true
    override fun isValidCommandAndState(command: MakePhoneCallCommand, state: PhoneCard) = true
}