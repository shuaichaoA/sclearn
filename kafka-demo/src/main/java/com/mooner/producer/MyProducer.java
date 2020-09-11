package com.mooner.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class MyProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        // kafka 集群， broker-list

        props.put("bootstrap.servers", "localhost:9092");

        //可用ProducerConfig.ACKS_CONFIG 代替 "acks"
        //props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put("acks", "all");
        // 重试次数
        props.put("retries", 1);
        // 批次大小
        props.put("batch.size", 16384);
        // 等待时间
        props.put("linger.ms", 1);
        // RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("first", "test-" + i),
                    (m, e) -> {
                        System.out.println(m.offset()+"-"+m.partition());
                    });
        }
        producer.close();
    }

}
