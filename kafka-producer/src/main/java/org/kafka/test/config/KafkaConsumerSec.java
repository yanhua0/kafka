package org.kafka.test.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class KafkaConsumerSec {
      @Resource
      private KafkaTemplate kafkaOutTemplate;
    @KafkaListener(topics = "SafetyPlatform", groupId = "uss_ams_r")
    public void receiveMessage(ConsumerRecord<String, String> consumerRecord) {
        log.info("topic={},value={}", consumerRecord.topic(), consumerRecord.value());

       kafkaOutTemplate.send("SafetyPlatform",consumerRecord.value());
    }

//    @KafkaListener(topics = "SafetyPlatform", groupId = "uss")
//    public void receiveMessage2(ConsumerRecord<String, String> consumerRecord) {
//        log.info("topic={},value={}", consumerRecord.topic(), consumerRecord.value());
//
//        // kafkaOutTemplate.send("SafetyPlatform",consumerRecord.value());
//    }
}
