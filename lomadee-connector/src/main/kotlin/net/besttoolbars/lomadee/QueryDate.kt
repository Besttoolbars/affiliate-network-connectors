package net.besttoolbars.lomadee

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class QueryDate(val date: LocalDate) {
    override fun toString(): String = date.format(formatter)

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        fun of(year: Int, month: Int, day: Int): QueryDate = QueryDate(LocalDate.of(year, month, day))
    }
}