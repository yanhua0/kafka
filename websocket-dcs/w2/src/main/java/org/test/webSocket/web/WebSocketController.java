package org.test.webSocket.web;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.webSocket.Test;
import org.test.webSocket.service.MessageSender;

import javax.annotation.Resource;
import java.util.Map;

/**
 * stomp连接断开的判断
 */
@Controller
public class WebSocketController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    @Resource
    private MessageSender messageSender;

    /**
     * 一对多
     *
     * @param message
     * @return
     */
    @RequestMapping("/sendMessageTopic")
    //@SendTo("/topic/webSocketTopic")
    @ResponseBody
    public String sendToTopic(String message) {
  simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
        System.out.println("Send-Topic-Msg:" + message);
        return message;
    }

    /**
     * 一对一
     *
     * @param message
     * @return
     */
    @RequestMapping("/sendUser")
    //@SendTo("/topic/webSocketTopic")
    @ResponseBody
    public void sendToUser(String message) {
        messageSender.sendTopicMessage(message);
    }

    /**
     * 接受前端消息
     * 需要配置/app
     * 必须写messageMapping才能监听前端
     *
     * @param message
     */
    @MessageMapping("/v2/chat")
    //有点类似于群聊
    @SendTo("/topic/webSocketTopic")
    public Test gameInfo(String mes,
                         StompHeaderAccessor accessor, //所有消息头信息
                         @Headers Map<String, Object> headers, //所有头部值
                         @Header(name="simpSessionId") String sessionId, //指定头部的值 ，这里指sessionId
                         Message message,   //完整消息，包含消息头和消息体（即header和body）
                         @Payload String body) {
        //simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
        System.out.println(message);
        System.out.println(sessionId);
        System.out.println(body);
        Test test=new Test();
        test.setMs(mes);
        System.out.println("群聊{}" + message);
       return test;
    }

}
