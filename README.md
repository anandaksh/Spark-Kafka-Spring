Steps to run:

1. cd <Kafka>/bin,run windows\zookeeper-server-start.bat config\zookeeper.properties
2. cd <Kafka>/bin, run windows\kafka-server-start.bat config\server.properties
3. Make sure topic name test is created already
4. Create C:\kafka\KafkaSparkStreamingOutput folder
5. Run SparkKafkaProducerApplication.java
6. Run SparkKafkaStreamReaderApplication.java
7. Run SparkDataConsumerApplication.java
8.Hit http://localhost:8080/records in browser
9.Hit http://localhost:8091/result
