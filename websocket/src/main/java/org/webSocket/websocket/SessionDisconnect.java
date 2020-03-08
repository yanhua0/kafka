package org.webSocket.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
@Component
public class SessionDisconnect<Event extends AbstractSubProtocolEvent> implements ApplicationListener<Event> {
    @Override
    public void onApplicationEvent(Event event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String session = sha.getSessionId();
        System.out.println(session);
    }
}
