package com.example;

import com.example.SparkProcess;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring4sparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring4sparkApplication.class, args);
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
