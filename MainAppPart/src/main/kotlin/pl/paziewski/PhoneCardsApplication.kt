package pl.paziewski

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhoneCardsApplication

fun main(args: Array<String>) {
    runApplication<PhoneCardsApplication>(*args)
}
