package org.redis.test.entity;

import org.redis.test.cache.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {
//    @Resource
//    private RedisService redisService;
    @Resource
    private CacheService cacheService;

//    @GetMapping("/redis/{id}")
//    public void redis(@PathVariable("id") Integer id) {
//        System.out.println(redisService.get("7"));
//        redisService.set("redis_key","123333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
// ExecutorService executorService = Executors.newFixedThreadPool(500);
//        for (int i = 0; i < id; i++) {
//         executorService.execute(() -> {
//              long start=System.currentTimeMillis();
//              redisService.get("redis_key");
//              long e=System.currentTimeMillis();
//              if(e-start>100)
//              System.out.println(e-start);
//         });
//
//        }
//    }
   @GetMapping("/cache")
    public String ss(int  s){
ExecutorService executorService=Executors.newFixedThreadPool(1000);
       for (int i = 0; i < s; i++) {
           executorService.execute(()->{
               long start=System.currentTimeMillis();
               cacheService.ss("s");
               long e=System.currentTimeMillis();
               System.out.println(e-start);
               if(e-start>100)
                   System.out.println(e-start);
           });

       }
        return  cacheService.ss("123");
   }
    @GetMapping("/update")
    public String ss2(String s){

        return  cacheService.update(s);
    }
}
