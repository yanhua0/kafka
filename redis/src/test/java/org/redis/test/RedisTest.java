package org.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redis.test.service.RedisService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisService redisUtils;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
    //   ExecutorService executorService= Executors.newFixedThreadPool(1);
        for (int i = 0; i <1; i++) {
           // executorService.execute(()->{
                redisUtils.set("redis_key", "redis_vale");
           // });
        }

    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
//      ExecutorService executorService= Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
//            executorService.execute(()->{
                redisUtils.set("redis_key", "redis_vale");
                String value = redisUtils.get("redis_key");
                System.out.println("redis_key="+value);
         //   });
        }



    }

}
