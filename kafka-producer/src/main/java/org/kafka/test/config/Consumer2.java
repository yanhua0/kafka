package org.kafka.test.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {
     @KafkaListener(topicPartitions = { @TopicPartition(topic = "test2", partitions = { "0" }) })
    public void receiveMessage2(String message) throws InterruptedException {
        //收到通道的消息之后执行秒杀操作

        System.out.println(Thread.currentThread().getName()+"[消费者id0="+message+"]");
        Thread.sleep(1000);
    }
}
