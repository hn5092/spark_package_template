package com.example.demo

import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      System.err.println("Usage: HdfsWordCount <directory>")
      System.exit(1)
    }
    val spark = SparkSession
      .builder
      .appName("HdfsWordCount")
      .getOrCreate()

    val wordCounts =spark.sparkContext.textFile(args(0))
      .flatMap(_.split(" "))
      .map(x => (x, 1))
      .reduceByKey(_ + _)
    wordCounts.collect()
      .foreach(println)
  }
}
