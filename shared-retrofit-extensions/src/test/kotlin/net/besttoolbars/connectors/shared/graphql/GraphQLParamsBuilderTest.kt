package net.besttoolbars.connectors.shared.graphql

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

internal class GraphQLParamsBuilderTest {
    enum class TestEnum { ENUM }

    @Test
    fun `graphql default scalars`() {
        val expected = "int: 1, float: 1.0, string: \"string\", boolean: true, enum: ENUM"
        val params = gqlParamsBuilder {
            "int" set 1
            "float" set 1.0
            "string" set "string"
            "boolean" set true
            "enum" set TestEnum.ENUM
        }
        Assertions.assertEquals(expected, params)
    }

    @Test
    fun `graphql list`() {
        val expected = "intList: [1, 2, 3], intArray: [1, 2, 3], enumList: [ENUM], stringList: [\"str1\", \"str2\"]"
        val params = gqlParamsBuilder {
            "intList" set listOf(1, 2, 3)
            "intArray" set arrayOf(1, 2, 3)
            "enumList" set listOf(TestEnum.ENUM)
            "stringList" set listOf("str1", "str2")
        }
        Assertions.assertEquals(expected, params)
    }

    @Test
    fun `graphql custom scalar`() {
        val expected = "str_id: \"string_id\", int_id: \"123123\""
        val params = gqlParamsBuilder {
            "str_id" set IdScalar("string_id")
            "int_id" set IdScalar(123123)
        }
        Assertions.assertEquals(expected, params)
    }

    @Test
    fun `graphql custom type`() {
        val date = ZonedDateTime.of(
            2021, 4, 6,
            15, 54, 23, 412,
            ZoneId.of("Z")
        )
        val expected = "date: \"2021-04-06T15:54:23.000000412Z\""
        val params = gqlParamsBuilder {
            "date" set date
        }
        Assertions.assertEquals(expected, params)
    }
}