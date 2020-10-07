package net.besttoolbars.connectors.shared.graphql

fun gqlParamsBuilder(f: GraphQLParamsBuilder.() -> Unit): String = GraphQLParamsBuilder(f).build()

class GraphQLParamsBuilder(f: GraphQLParamsBuilder.() -> Unit) {
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