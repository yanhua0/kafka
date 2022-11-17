package org.test.webSocket.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

 //开启定时器功能
@Component
public class MessageSender {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    //@Scheduled(fixedRate = 10000) //间隔 通过StringRedisTemplate对象向redis消息队列频道发布消息
    public void sendTopicMessage(String message){
        System.out.println("定时任务执行");
        stringRedisTemplate.convertAndSend("Mytopic",message);
    }

}
