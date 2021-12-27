package pl.paziewski

import org.springframework.stereotype.Component

interface PhoneNumberProvider {
    fun getNumber(): String
}

@Component
class StupidPhoneNumberProvider : PhoneNumberProvider {
    override fun getNumber() = "123 456 789"
}