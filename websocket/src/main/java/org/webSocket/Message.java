package org.webSocket;

import lombok.Getter;
import lombok.Setter;

import javax.websocket.Session;
import java.util.function.Consumer;

@Getter
@Setter
public class Message {
    private Session session;
    private String message;
    private Consumer<Message> consumer;
}
