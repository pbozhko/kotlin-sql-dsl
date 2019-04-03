# kotlin-sql-dsl

####Select queries:
```
select {
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

#######
SELECT id, name, age FROM t_users WHERE id NOT IN [1, 2, 3] age = 15 name LIKE %test% ORDER BY id, age DESC LIMIT 10
```