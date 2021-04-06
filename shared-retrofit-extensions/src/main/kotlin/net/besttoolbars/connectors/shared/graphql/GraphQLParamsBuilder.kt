package net.besttoolbars.connectors.shared.graphql

fun gqlParamsBuilder(builder: GraphQLParamsBuilder.() -> Unit): String = GraphQLParamsBuilder(builder).build()

class GraphQLParamsBuilder(builder: GraphQLParamsBuilder.() -> Unit) {
    private val params = mutableMapOf<String, Any?>()

    init {
        builder.invoke(this)
    }

    fun build(): String =
        params.map { (param, argument) -> "$param: ${argument?.formatted}" }
            .joinToString()

    fun String.set(value: Any?, nullable: Boolean = true) {
        check(nullable || value != null) { "non-nullable parameter $this is null" }
        params[this] = value
    }

    infix fun String.set(value: Any?) = set(value, nullable = true)
    infix fun String.setIfNotNull(value: Any?) = value?.let { set(value) }
    infix fun String.setNotNullable(value: Any) = set(value, nullable = false)
    infix fun String.set(builder: GraphQLParamsBuilder.() -> Unit) = set(GraphQLParamsBuilder(builder))

    infix fun String.setIfNotEmpty(value: Collection<*>?) {
        if (value != null && value.isEmpty()) return
        this set value
    }

    private val Any.formatted: String
        get() = when (this) {
            is GraphQLParamsBuilder -> "{ ${this.build()} }"
            is Iterable<*> -> this.filterNotNull().map { it.formatted }.toString()
            is Array<*> -> this.asIterable().formatted
            is GraphQLScalar -> this.string()
            is String -> "\"$this\""
            else -> toString()
        }
}

interface GraphQLScalar {
    fun string(): String
}

class IdScalar(val id: String) : GraphQLScalar {
    constructor(id: Int) : this("$id")

    override fun string(): String = "\"$id\""
}