package net.besttoolbars.cj.graphql

fun gqlParamsBuilder(f: ParamsBuilder.() -> Unit): String = ParamsBuilder(f).build()

class ParamsBuilder(f: ParamsBuilder.() -> Unit) {
    private val params = mutableMapOf<String, Any?>()

    init {
        f.invoke(this)
    }

    fun build(): String {
        return params.map { (key, value) -> "$key: $value" }
            .joinToString()
    }

    infix fun String.set(value: Any?) { params[this] = value }

    fun Collection<*>?.ifNotNullAndNotEmpty(pr: () -> Unit) {
        if (this == null || this.isEmpty()) return
        pr()
    }
}