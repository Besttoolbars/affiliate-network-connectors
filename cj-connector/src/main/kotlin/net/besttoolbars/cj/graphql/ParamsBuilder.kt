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
    infix fun String.setNotNull(value: Any) { params[this] = value }
    infix fun String.setNotEmpty(value: Collection<*>?) {
        if (value != null && value.isEmpty()) return
        this set value
    }
}