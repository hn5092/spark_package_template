package com.example.demo;

import example.SparkProcess;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("org.springframework.boot.logging.LoggingSystem", "none");
		SpringApplication.run(DemoApplication.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}

	@Bean
	SparkProcess sparkProcess (){
		SparkConf sparkConf = new SparkConf();
		if (!sparkConf.contains("spark.master")) {
			sparkConf.setMaster("local[4]");
		}
		return new SparkProcess(SparkSession
				.builder()
				.enableHiveSupport()
				.appName("spring")
				.config(sparkConf)
				.getOrCreate()) ;
	}
}
