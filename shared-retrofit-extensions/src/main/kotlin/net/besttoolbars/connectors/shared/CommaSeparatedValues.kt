package net.besttoolbars.connectors.shared

class CommaSeparatedValues<T>(val values: Iterable<T>) {
    override fun toString(): String = values.joinToString(",")
}

fun <T> commaSeparatedValuesOf(vararg values: T): CommaSeparatedValues<T> =
    CommaSeparatedValues(values.asIterable())