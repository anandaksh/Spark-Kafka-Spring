package com.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kafka.serializer.StringDecoder;
@Component
public class KafkaReader implements CommandLineRunner {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SparkContext sparkContext;

	@Value("${kafka.servers}")
	private String SERVER;

	@Value("${topic.name}")
	private String TOPIC_NAME;

	@Value("${hdfs.folder.path}")
	private String HDFS_PATH;

	@Value("${out.file.prefix}")
	private String FILE_PREFIX;

	private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Running streaming reader");
		JavaSparkContext sc = new JavaSparkContext(sparkContext);
		JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(1000 * 60));
		
		Map<String, String> config = new HashMap<>();
		config.put("metadata.broker.list", SERVER);
		Set<String> topics = new HashSet<>(Arrays.asList(TOPIC_NAME.split(",")));

		JavaPairInputDStream<String, String> kafkaStream = KafkaUtils.createDirectStream(ssc, String.class,
				String.class, StringDecoder.class, StringDecoder.class, config, topics);

		kafkaStream.foreachRDD(rdd -> {
			if (!rdd.isEmpty()) {
				String dateSuffix = DATE_TIME_FORMAT.format(new Date());
				JavaRDD<String> jrdd = rdd.map(x -> x._2);
				jrdd.saveAsTextFile(HDFS_PATH + FILE_PREFIX + dateSuffix);
			}
		});
		ssc.start();
		ssc.awaitTermination();
	}

}
