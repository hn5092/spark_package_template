package com.pajk.bigdata

import org.apache.spark.sql.SparkSession

class SparkProcess(spark: SparkSession) {


  def runPi(): Unit ={
    val df = spark.read.json("src/main/resources/people.json")
    // Displays the content of the DataFrame to stdout
    df.show()
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+
    // $example off:create_df$
    import spark.implicits._

    // $example on:untyped_ops$
    // This import is needed to use the $-notation
    // Print the schema in a tree format
    df.printSchema()
    // root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)

    // Select only the "name" column
    df.select("name").show()
    // +-------+
    // |   name|
    // +-------+
    // |Michael|
    // |   Andy|
    // | Justin|
    // +-------+

    // Select everybody, but increment the age by 1
    df.select($"name", $"age" + 1).show()
    // +-------+---------+
    // |   name|(age + 1)|
    // +-------+---------+
    // |Michael|     null|
    // |   Andy|       31|
    // | Justin|       20|
    // +-------+---------+

    // Select people older than 21
    df.filter($"age" > 21).show()
    // +---+----+
    // |age|name|
    // +---+----+
    // | 30|Andy|
    // +---+----+

    // Count people by age
    df.groupBy("age").count().show()
    // +----+-----+
    // | age|count|
    // +----+-----+
    // |  19|    1|
    // |null|    1|
    // |  30|    1|
    // +----+-----+
    // $example off:untyped_ops$

    // $example on:run_sql$
    // Register the DataFrame as a SQL temporary view
//    df.createOrReplaceTempView("people")
//
//    val sqlDF = spark.sql("SELECT * FROM people")
//    sqlDF.show()
//    // +----+-------+
//    // | age|   name|
//    // +----+-------+
//    // |null|Michael|
//    // |  30|   Andy|
//    // |  19| Justin|
//    // +----+-------+
//    // $example off:run_sql$
//
//    // $example on:global_temp_view$
//    // Register the DataFrame as a global temporary view
//    df.createGlobalTempView("people")
//
//    // Global temporary view is tied to a system preserved database `global_temp`
//    spark.sql("SELECT * FROM global_temp.people").show()
//    // +----+-------+
//    // | age|   name|
//    // +----+-------+
//    // |null|Michael|
//    // |  30|   Andy|
//    // |  19| Justin|
//    // +----+-------+
//
//    // Global temporary view is cross-session
//    spark.newSession().sql("SELECT * FROM global_temp.people").show()



  }
}
