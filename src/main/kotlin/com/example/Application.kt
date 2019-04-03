package com.example

import com.example.sql.query.OrderDirection.ASC
import com.example.sql.query.OrderDirection.DESC
import com.example.sql.query.Where.Condition.*
import com.example.sql.query.Where.Filter
import com.example.sql.query.Where.Operand.AND
import com.example.sql.query.Where.Operand.OR
import com.example.sql.query.select

fun main() {

    val query = select {
        columns = setOf("id", "name", "age")
        from = "t_users"
        where {
            mapOf(
                Filter("id", NOT_IN, setOf(1, 2, 3)) to AND,
                Filter("age", EQUAL, 15) to OR,
                Filter("name", LIKE, "%test%") to null
            )
        }
        orderBy {
            mapOf(
                "id" to ASC,
                "age" to DESC
            )
        }
        limit = 10
    }

    println(query.buildSql())
}
