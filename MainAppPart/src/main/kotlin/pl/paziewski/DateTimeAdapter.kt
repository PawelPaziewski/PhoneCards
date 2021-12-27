package pl.paziewski

import java.time.LocalDateTime

//The adapter will be developed according to needed functionality
class DateTimeAdapter(private val date: LocalDateTime) {

    companion object {
        fun now(): DateTimeAdapter {
            return DateTimeAdapter(LocalDateTime.now());
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DateTimeAdapter

        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }
}