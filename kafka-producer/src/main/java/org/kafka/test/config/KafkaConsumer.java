package org.kafka.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    /**
     * 监听Topic主题,有消息就读取
     *
     * @param message
     */
    //@KafkaListener(topicPartitions = { @TopicPartition(topic = "test2", partitions = { "1" }) })
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("消费者id1" + message);
        //log.info("{}开始消费了",Thread.currentThread().getName(),message);


    }

    //@KafkaListener(topics = "test")
    public void receiveMessageV2(String message){
        // System.out.println("消费者" + message);
        log.info("接收到{}",message);
    }

}
