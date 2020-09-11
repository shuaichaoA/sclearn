kafka-topics.sh --zookeeper localhost:2181 --list
kafka-topics.bat --zookeeper localhost:2181 --list
kafka-topics.sh --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic first
kafka-topics.bat --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic first

kafka-console-producer.sh --brokerlist localhost:9092 --topic first
kafka-console-producer.bat --brokerlist localhost:9092 --topic first
kafka-console-consumer.sh --zookeeper localhost:2181 --topic first
kafka-console-consumer.bat --zookeeper localhost:2181 --topic first

kafka-server-start.sh -daemon config/server.properties
kafka-server-start.bat -daemon config/server.properties
kafka-server-start.bat -daemon config/server-1.properties
kafka-server-start.bat -daemon config/server-2.properties