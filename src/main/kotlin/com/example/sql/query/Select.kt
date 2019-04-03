package com.example.sql.query

import com.example.exception.InvalidParameterException
import com.example.sql.SqlQueryDslMarker

@SqlQueryDslMarker
class Select : Query {

    var columns: List<String>? = null
    var from: String? = null

    var orderBy: List<String>? = null
    var limit: Int? = null

    var where: Map<String, Any>? = null

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
        orderBy?.let {
            "ORDER BY ${it.map { column -> column.trim() }.joinToString(", ")}"
        } ?: ""

    private fun handleLimit(): String =
        limit?.let {
            if (it > -1) {
                "LIMIT $limit"
            } else throw InvalidParameterException("Limit cannot be negative")
        } ?: ""

    private fun handleWhere(): String =
        where?.let {
            val args = it.entries.map { entry ->
                when (entry.value) {
                    is Int, Long, Double, Float -> "${entry.key} = ${entry.value}"
                    else -> "${entry.key} = '${entry.value}'"
                }
            }.joinToString(" AND ")
            "WHERE $args"
        } ?: ""

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
}