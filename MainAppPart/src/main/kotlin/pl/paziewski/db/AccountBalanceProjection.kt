package pl.paziewski.db

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.paziewski.CardBoughtEvent
import pl.paziewski.CardTopUpEvent
import pl.paziewski.PhoneCallMadeEvent
import pl.paziewski.SmsSentEvent
import java.math.BigDecimal
import java.util.*

@Service
class AccountBalanceProjection @Autowired constructor(private val repository: AccountBalanceRepository) {

    @EventHandler
    fun on(event: CardBoughtEvent) {
        repository.save(AccountBalance(event.phoneNumber, event.initialMoney))
    }

    @EventHandler
    fun on(event: CardTopUpEvent) {
        val balance = repository.findById(event.phoneNumber)
        balance.ifPresent {
            it.balance += event.amount
            repository.save(it)
        }
    }

    @EventHandler
    fun on(event: PhoneCallMadeEvent) {
        val balance = repository.findById(event.callerPhoneNumber)
        balance.ifPresent {
            it.balance -= event.callCost
            repository.save(it)
        }
    }

    @EventHandler
    fun on(event: SmsSentEvent) {
        val balance = repository.findById(event.senderPhoneNumber)
        balance.ifPresent {
            it.balance -= event.cost
            repository.save(it)
        }
    }

    @QueryHandler
    fun getAccountBalance(query: GetAccountBalanceQuery): Optional<BigDecimal> = repository.findById(query.phoneNumber)
        .map { it.balance }
}