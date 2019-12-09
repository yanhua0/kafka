package org.kafka.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    /**
     * 监听Topic主题,有消息就读取
     * @param message
     */
    private String ss(String tt,String...ss){
System.out.println(ss[3]);
return "";
    }
    @KafkaListener(topicPartitions = { @TopicPartition(topic = "test2", partitions = { "1" }) })
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("消费者id1"+message);
        //log.info("{}开始消费了",Thread.currentThread().getName(),message);


    }
public static void main(String[] args){
    KafkaConsumer kafkaConsumer=new KafkaConsumer();
    kafkaConsumer.ss("123","123","123","123","1231","123","123");
}
}
