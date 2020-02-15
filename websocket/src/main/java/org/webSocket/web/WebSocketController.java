package org.webSocket.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webSocket.Test;
import org.webSocket.service.server.WebSocketServer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class WebSocketController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    @Resource
    private WebSocketServer webSocketServer;

    @RequestMapping(value = "/pushMsgToHtml", method = RequestMethod.GET)
    @ResponseBody
    public void pushMsgToHtml() throws IOException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(30);
        Runnable runnable = () -> webSocketServer.sendMessage("测试消息----测试消息----测试消息----测试消息----测试消息----测试" +
                "测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试消息----测试\" +\n" +
                "                \"测试消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----测试消息----消息----测试消息----测试消息----测试消息----");
        scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);

    }

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
     //   simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
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
    public String sendToUser(String message) {
      simpMessagingTemplate.convertAndSendToUser("1", "/topic/webSocketTopic", message);
        System.out.println("Send-Topic-Msg:" + message);
        return message;
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
    public Test gameInfo(String message) {
        //simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
        Test test=new Test();
        test.setMs(message);
        System.out.println("群聊{}" + message);
       return test;
    }

}
