package com.example;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.BranchLocation;
import com.example.model.KafkaInputMessage;
import com.example.model.ResultData;
import com.example.repository.ResultJdbcRepository;
import com.google.gson.Gson;

import scala.Tuple2;
@Component
public class SparkDataConsumerRunner implements CommandLineRunner{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SparkContext sparkContext;
	@Autowired
	private ResultJdbcRepository respository;
	
	@Value("${hdfs.folder.path}")
	private String HDFS_PATH;

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("running consumer");
		JavaSparkContext sc = new JavaSparkContext(sparkContext);
		while (true) {
			logger.info("loading file");
			JavaRDD<String> data = sc.textFile(HDFS_PATH);
			JavaRDD<KafkaInputMessage> records = data.map(line -> {
				Gson objGson = new Gson();
				KafkaInputMessage rd = objGson.fromJson(line, KafkaInputMessage.class);
				return rd;
			});

			JavaPairRDD<BranchLocation, Integer> rddBrandCount = records
					.mapToPair(e -> new Tuple2<BranchLocation, Integer>(new BranchLocation(e.getLocation(), e.getBranch()), 1));

			JavaPairRDD<BranchLocation, Integer> rddGroupByKeyResult = rddBrandCount.reduceByKey((a, b) -> a + b);
			Map<BranchLocation, Integer> resultMap = rddGroupByKeyResult.collectAsMap();

			Iterator<Entry<BranchLocation, Integer>> it = resultMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<BranchLocation, Integer> pair = it.next();
				BranchLocation key = (BranchLocation) pair.getKey();
				Integer value = Integer.parseInt(pair.getValue().toString());

				ResultData rs = respository.findByLocationAndBranch(key.getLocation(), key.getBranch());
				if (rs != null) {
					rs.setNumberofbranch(value);
				}
				else {
					rs = new ResultData(key.getLocation(), key.getBranch() ,value);
				}
				respository.save(rs);
			}			
			Thread.sleep(1000 * 60);
		}
	}
		
}
