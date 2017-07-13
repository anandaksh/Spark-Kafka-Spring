package com.example.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
	
	@Bean
	public SparkContext sparkContext() {
		SparkConf conf = new SparkConf();
		conf.setAppName("SparkKafkaDataConsumer").setMaster("local[*]");
		conf.set("spark.streaming.stopGracefullyOnShutdown", "true");
		return new SparkContext(conf);
	}
}
