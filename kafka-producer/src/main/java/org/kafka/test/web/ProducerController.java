package org.kafka.test.web;

import org.kafka.test.config.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ProducerController {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    @GetMapping("/kafkaPro")
    public Object con(String message) {
        kafkaTemplate.send("test2",message);
     /*   ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(30);
        Runnable runnable= () -> kafkaTemplate.send("zjl","消息");
        scheduledExecutorService.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);
*/
       return message;
    }
}
