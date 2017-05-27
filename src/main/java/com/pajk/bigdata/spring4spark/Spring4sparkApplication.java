package com.pajk.bigdata.spring4spark;

import com.pajk.bigdata.SparkProcess;
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



		return new SparkProcess(SparkSession
				.builder()
				.enableHiveSupport()
//				.master("local")
				.appName("spring")
				.getOrCreate()) ;
	}
}
