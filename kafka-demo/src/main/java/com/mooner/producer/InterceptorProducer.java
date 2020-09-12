package com.mooner.producer;

import com.mooner.interceptor.CounterInterceptor;
import com.mooner.interceptor.TimeInterceptor;
import com.mooner.partition.MyPartition;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

public class InterceptorProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        // kafka 集群， broker-list

        props.put("bootstrap.servers", "123.57.204.249:9092");
//        props.put("bootstrap.servers", "localhost:9092");

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
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartition.class);
        // 添加拦截器
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, Arrays.asList(
                "com.mooner.interceptor.CounterInterceptor",
                "com.mooner.interceptor.TimeInterceptor"));
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("second", "777", "test-" + i));
        }
        producer.close();
    }
}
