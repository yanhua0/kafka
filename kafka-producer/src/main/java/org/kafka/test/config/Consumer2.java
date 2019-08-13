package org.kafka.test.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {
    @KafkaListener(topics = {"zjl"},groupId = "xx")
    public void receiveMessage2(String message){
        //收到通道的消息之后执行秒杀操作
        System.out.println("消费者2"+message);

    }
}
