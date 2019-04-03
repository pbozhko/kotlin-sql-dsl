package com.example.sql.query

import com.example.sql.SqlQueryDslMarker

@SqlQueryDslMarker
class Insert : Query {

    //    var into: String? = null
//    var columns: List<String>? = null
//    var values: List<String>? = null
//
//    private fun handleInto(): String =
//        into?.let {
//            if (it.isEmpty()) throw InvalidParameterException("Target table cannot be empty")
//            it.trim()
//        } ?: throw InvalidParameterException("Please specify target table")
//
//    private fun handleColumns(): String =
//        columns?.let { list ->
//            if (list.contains("*")) {
//                throw InvalidParameterException("List of columns cannot contain *")
//            } else {
//                list
//                    .map { it.trim() }
//                    .joinToString(", ")
//            }
//        } ?: throw InvalidParameterException("Please specify columns to insert")
//
//    private fun handleColumnsAndValues(): String =
//        values?.map {
//            "($it)"
//        }?.joinToString(", ") ?: throw InvalidParameterException("Please specify values to insert")
//
    override fun buildSql(): String = TODO()
//        listOf(
//            "INSERT INTO",
//            handleInto(),
//            handleColumnsAndValues()
//        ).filter { it.isNotEmpty() }.joinToString(" ")
}