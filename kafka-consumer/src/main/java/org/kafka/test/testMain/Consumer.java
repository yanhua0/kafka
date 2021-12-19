package org.kafka.test.testMain;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer {
    public static void main(String[] args) {
        List<String> kafkaBootstapServer = new ArrayList<>();
        for (String s : kafkaBootstapServer) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                Properties p = new Properties();
                String groupId = "mg-scene-alarm-be" + UUID.randomUUID().toString();//存在多个的情况
                p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, s);
                p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                p.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
                p.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, groupId);
                p.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, groupId);
                p.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, groupId);
                p.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, groupId);
                p.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, groupId);
                p.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, groupId);
                KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);
                kafkaConsumer.subscribe(Collections.singletonList(Producer.topic));// 订阅消息
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        consumer(record.value());
                    }

                }
            });

        }

    }
    private static void consumer(String value) {

    }
}
