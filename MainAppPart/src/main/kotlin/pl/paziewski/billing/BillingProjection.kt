package pl.paziewski.billing

import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.paziewski.PhoneCallMadeEvent
import pl.paziewski.SmsSentEvent

@Service
class BillingProjection @Autowired constructor(private val repository: BillingRepository) {

    @EventHandler
    fun handle(event: SmsSentEvent) {
        repository.save(BillingEntry(event.senderPhoneNumber,
            BillingEntryType.SMS,
            event.timestamp,
            event.cost,
            event.receiverPhoneNumber,
            null))
    }

    @EventHandler
    fun handle(event: PhoneCallMadeEvent) {
        repository.save(BillingEntry(event.callerPhoneNumber,
            BillingEntryType.CALL,
            event.timestamp,
            event.callCost,
            event.receiverPhoneNumber,
            event.callDuration))
    }
}