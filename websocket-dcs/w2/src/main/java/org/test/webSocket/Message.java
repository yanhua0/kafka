package org.test.webSocket;

import lombok.Getter;
import lombok.Setter;

import javax.websocket.Session;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Getter
@Setter
public class Message {
    private Session session;
    private String message;
    private Consumer<Message> consumer;
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>(1);
        blockingQueue.put("123");
        blockingQueue.put("123");
    System.out.println( blockingQueue.take());

        System.out.println("12376");
    }
}
