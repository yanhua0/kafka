package org.redis.test.entity;

import org.redis.test.cache.CacheService;
import org.redis.test.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {
    @Resource
    private RedisService redisService;
    @Resource
    private CacheService cacheService;

    @GetMapping("/redis/test")
    public void get(String key, String value) {
        redisService.getAndSet(key, value);
    }
    @GetMapping("/redis")
    public String get(String key) {
       return redisService.get(key);
    }
    @GetMapping("/cache")
    public String ss(int s) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < s; i++) {
            executorService.execute(() -> {
                long start = System.currentTimeMillis();
                cacheService.ss("s");
                long e = System.currentTimeMillis();
                System.out.println(e - start);
                if (e - start > 100)
                    System.out.println(e - start);
            });

        }
        return cacheService.ss("123");
    }

    @GetMapping("/update")
    public String ss2(String s) {

        return cacheService.update(s);
    }

    @GetMapping("/del")
    public void ss3(String s) {

        cacheService.delte(s);
    }
}
