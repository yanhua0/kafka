package org.kafka.test.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    /**
     * 监听Topic主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"zjl"},groupId = "zz")
    public void receiveMessage(String message){
        System.out.println("消费者1"+message);

    }

}
