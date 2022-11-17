package org.test.webSocket.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.Resource;


public class TopicListener implements MessageListener {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("w1监听" + message.toString());
        simpMessagingTemplate.convertAndSendToUser("2","/topic/webSocketTopic",message.toString());

    }

}
