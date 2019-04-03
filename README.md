# kotlin-sql-dsl

####Select queries:
```
 select {
   columns = listOf("*") 
   from = "t_users"
   limit = 50
   orderBy = listOf("id")
   where = mapOf("id" to 100500, "name" to "Bob")
}
```
```
 select {
   columns = listOf("id", "name", "age") 
   from = "t_users"
   limit = 50
   orderBy = listOf("age")
   where = mapOf("id" to 100500, "age" to "Bob")
}
```