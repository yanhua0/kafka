package org.webSocket.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webSocket.service.server.WebSocketServer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class WebSocketController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/pushMsgToHtml", method = RequestMethod.GET)
    @ResponseBody
    public String pushMsgToHtml() throws IOException {
        CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.webSocketSet;
        for (WebSocketServer webSocketServer : webSocketSet) {
            webSocketServer.sendMessage("你好客户端.....我是服务端");
        }

        return "200";
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
        simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
        System.out.println("Send-Topic-Msg:" + message);
        return message;
    }

    /**
     * 一对一
     * @param message
     * @return
     */
    @RequestMapping("/sendUser")
    //@SendTo("/topic/webSocketTopic")
    @ResponseBody
    public String sendToUser(String message) {
        simpMessagingTemplate.convertAndSendToUser("1","/topic/webSocketTopic", message);
        System.out.println("Send-Topic-Msg:" + message);
        return message;
    }
    /**
     * 接受前端消息
     * 需要配置/app
     * 必须写messageMapping才能监听前端
     * @param message
     */
    @MessageMapping("/v2/chat")
    //有点类似于群聊
    @SendTo("/topic/webSocketTopic")
    public String gameInfo(String message) {
        //simpMessagingTemplate.convertAndSend("/topic/webSocketTopic", message);
        System.out.println("群聊{}"+message);
        return message;
    }


}
