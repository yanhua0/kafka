package org.webSocket.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

import java.util.function.BiConsumer;

@Component
public class SessionDisconnect<Event extends AbstractSubProtocolEvent> implements ApplicationListener<Event> {
    @Override
    public void onApplicationEvent(Event event) {
        doSomeThing(event,(s1,s2)->System.out.println(s1+"="+s2));

    }
    private void doSomeThing(Event event,BiConsumer<String, String> biConsumer){
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String session = sha.getSessionId();
        biConsumer.accept("session",session);
    }
}
