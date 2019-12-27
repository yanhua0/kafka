package org.kafka.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaTemplate kafkaOutTemplate;

    @GetMapping("/kafkaPro")
    public void con(@RequestParam(required = false,name = "message") String message) {
    //  while(true){
          kafkaOutTemplate.send("test",message);
    //  }

     /*   ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(30);
        Runnable runnable= () -> kafkaTemplate.send("zjl","消息");
        scheduledExecutorService.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);
*/

    }
}
