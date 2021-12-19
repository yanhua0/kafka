package org.kafka.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/kafka")
    public void send(@RequestParam String topic, @RequestParam String message){
        kafkaTemplate.send(topic,message);
    }
}
