package com.example.sql.query

import com.example.exception.InvalidParameterException
import com.example.sql.SqlQueryDslMarker
import com.example.sql.query.Where.Filter
import com.example.sql.query.Where.Operand

@SqlQueryDslMarker
class Select : Query {

    var columns: Set<String>? = null
    var from: String? = null
    var limit: Int? = null
    var orderBy: OrderBy? = null
    var where: Where? = null

    private fun handleColumns(): String =
        columns?.let {
            if (it.isEmpty()) throw InvalidParameterException("List of columns cannot be empty")
            if (it.size > 1 && it.contains("*")) {
                throw InvalidParameterException("List of columns cannot contain * with another ones")
            } else {
                it
                    .map { column -> column.trim() }
                    .joinToString(", ")
            }
        } ?: throw InvalidParameterException("Please specify columns to select")

    private fun handleFrom(): String =
        from?.let {
            if (it.isEmpty()) throw InvalidParameterException("Source table cannot be empty")
            "FROM ${it.trim()}"
        } ?: throw InvalidParameterException("Please specify table to select")

    private fun handleOrderBy(): String =
        orderBy?.buildSql() ?: ""

    private fun handleLimit(): String =
        limit?.let {
            if (it > -1) {
                "LIMIT $limit"
            } else throw InvalidParameterException("Limit cannot be negative")
        } ?: ""

    private fun handleWhere(): String =
        where?.buildSql() ?: ""

    override fun buildSql(): String {
        return listOf(
            "SELECT",
            handleColumns(),
            handleFrom(),
            handleWhere(),
            handleOrderBy(),
            handleLimit()
        ).filter { it.isNotEmpty() }
            .joinToString(" ")
    }

    fun orderBy(block: OrderBy.() -> Map<String, OrderDirection>) {
        orderBy = OrderBy().apply {
            orders = block()
        }
    }

    fun where(block: Where.() -> Map<Filter, Operand?>) {
        where = Where().apply {
            filters = block()
        }
    }
}

fun select(block: Select.() -> Unit): Query = Select().apply(block)
