package com.mooner.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("bootstrap.servers", "123.57.204.249:9092");
        props.put("group.id", "abcd");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first", "second"));

        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(100);
            poll.forEach(record ->
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));
            consumer.commitAsync();
        }

    }
}
