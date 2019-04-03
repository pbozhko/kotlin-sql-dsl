package com.example.sql.query

fun select(block: Select.() -> Unit): Query {
    val select = Select()
    select.apply(block)
    return select
}

fun insert(block: Insert.() -> Unit): Query {
    val insert = Insert()
    insert.apply(block)
    return insert
}