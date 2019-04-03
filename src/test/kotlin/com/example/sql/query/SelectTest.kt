package com.example.sql.query

import com.example.exception.InvalidParameterException
import com.example.sql.query.OrderDirection.DESC
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class SelectTest : Spek({

    describe("select dsl queries") {

        on("missed columns list") {

            val query = select {
                from = "t_users"
            }

            it("throws exception") {
                shouldThrow<InvalidParameterException> {
                    query.buildSql()
                }
            }
        }

        on("empty columns list") {

            val query = select {
                columns = setOf()
                from = "t_users"
            }

            it("throws exception") {
                shouldThrow<InvalidParameterException> {
                    query.buildSql()
                }
            }
        }

        on("missed source table") {

            val query = select {
                columns = setOf("id", "name")
            }

            it("throws exception") {
                shouldThrow<InvalidParameterException> {
                    query.buildSql()
                }
            }
        }

        on("empty source table") {

            val query = select {
                columns = setOf("id", "name")
                from = ""
            }

            it("throws exception") {
                shouldThrow<InvalidParameterException> {
                    query.buildSql()
                }
            }
        }

        on("negative limit") {

            val query = select {
                columns = setOf("*")
                from = "t_users"
                limit = -1
            }

            it("throws exception") {
                shouldThrow<InvalidParameterException> {
                    query.buildSql()
                }
            }
        }

        on("normal source table and all columns") {

            val query = select {
                columns = setOf("*")
                from = "t_users"
            }

            it("returns valid sql") {

                val expectedQuery = "SELECT * FROM t_users"
                query.buildSql() shouldBe expectedQuery
            }
        }

        on("normal source table, all columns and valid limit") {

            val query = select {
                columns = setOf("*")
                from = "t_users"
                limit = 50
            }

            it("returns valid sql") {

                val expectedQuery = "SELECT * FROM t_users LIMIT 50"
                query.buildSql() shouldBe expectedQuery
            }
        }

        on("normal source table, all columns, valid limit and valid orders") {

            val query = select {
                columns = setOf("*")
                from = "t_users"
                limit = 50
                orderBy {
                    mapOf("id" to DESC)
                }
            }

            val sql = query.buildSql()

            it("returns valid sql") {

                val expectedQuery = "SELECT * FROM t_users ORDER BY id DESC LIMIT 50"
                query.buildSql() shouldBe expectedQuery
            }
        }
    }
})