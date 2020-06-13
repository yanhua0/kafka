package org.kafka.test.zeromq;

import org.zeromq.ZMQ;

public class Publisher {
    public void start() throws InterruptedException {
//        System.out.println("===========publisher start=============");
//        ZMQ.Context context = ZMQ.context(1);
//        ZMQ.Socket socket = context.socket(ZMQ.PUB);
//        socket.bind("tcp://127.0.0.1:9999");
//        socket.send("hello", 0);
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://127.0.0.1:9999");
        while (true) {
            publisher.send(String.valueOf(System.currentTimeMillis()), 0); //发送
            Thread.sleep(3000);
        }
    }
}
