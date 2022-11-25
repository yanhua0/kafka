package org.webSocket.config2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * websocket拦截器
 *
 * @author
 * @date 2020/1/4 17:11
 * @since 1.0
 */
@Component
@Slf4j
public class PresenceChannelInterceptor implements ChannelInterceptor {
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        Map nativeHeadersMap = (Map) message.getHeaders().get("nativeHeaders");
        System.out.println();
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
        if (sha.getCommand()!=null&&sha.getCommand()==StompCommand.SUBSCRIBE) {
            Object o = message.getHeaders().get("simpDestination");
            System.out.println(o);
        }
    }
}

