package com.example.sql.query

import com.example.sql.SqlQueryDslMarker

@SqlQueryDslMarker
class Where : Query {

    var filters: Map<Filter, Operand?> = mapOf()

    override fun buildSql(): String =
        if (filters.isNotEmpty()) {
            val params = filters.map {
                "${it.key.arg} ${it.key.condition.sign} ${it.key.value}".apply {
                    if (it.value != null) {
                        plus(it.value)
                    }
                }
            }.joinToString(" ")

            "WHERE $params"
        } else ""

    data class Filter(
        val arg: String,
        val condition: Condition,
        val value: Any
    )

    enum class Operand {
        AND, OR
    }

    enum class Condition(val sign: String) {
        IN("IN"),
        EQUAL("="),
        NOT_IN("NOT IN"),
        LIKE("LIKE"),
        BETWEEN("BETWEEN")
    }
}
