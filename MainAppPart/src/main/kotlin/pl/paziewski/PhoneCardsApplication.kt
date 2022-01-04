package pl.paziewski

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class PhoneCardsApplication

fun main(args: Array<String>) {
    runApplication<PhoneCardsApplication>(*args)
}
