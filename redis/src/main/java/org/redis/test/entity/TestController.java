package org.redis.test.entity;

import org.redis.test.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {
    @Resource
    private RedisService redisService;

    @GetMapping("/redis/{id}")
    public void redis(@PathVariable("id") Integer id) {
 ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < id; i++) {
         executorService.execute(() -> {

                redisService.set("redis_key","123");
              System.out.println(redisService.get("redis_key"));
         });
        }
    }
}
