package example

import org.apache.spark.sql.SparkSession

class SparkProcess(spark: SparkSession) {

  def runSql(sql: String): String={
    spark.sql(sql).take(10).mkString(",")
  }
}
