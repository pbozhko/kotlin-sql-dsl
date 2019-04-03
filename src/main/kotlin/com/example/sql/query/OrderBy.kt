package com.example.sql.query

import com.example.sql.SqlQueryDslMarker

@SqlQueryDslMarker
class OrderBy : Query {

    var orders: Map<String, OrderDirection> = mapOf()

    override fun buildSql(): String =
        if (orders.isNotEmpty()) {
            val params = orders.map { it ->
                when (it.value) {
                    OrderDirection.ASC -> it.key.trim()
                    OrderDirection.DESC -> "${it.key.trim()} ${it.value}"
                }
            }.joinToString()
            "ORDER BY $params"
        } else ""
}

enum class OrderDirection {
    ASC, DESC
}