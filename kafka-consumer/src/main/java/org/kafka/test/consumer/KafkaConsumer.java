package org.kafka.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    /**
     * 监听Topic主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"SafetyPlatform"},groupId = "xx")
    public void receiveMessage(String message){
        System.out.println("消费者1"+message);
      //  ConsumerConfig.Max

    }

}
