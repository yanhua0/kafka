package org.kafka.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            ExecutorService executorService= Executors.newSingleThreadExecutor();
            String finalI = String.valueOf(i);
            executorService.execute(()->{
                synchronized (finalI.intern()){
                    System.out.println("打印"+finalI);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
