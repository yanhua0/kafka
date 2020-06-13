package org.kafka.test.zeromq;

import org.zeromq.ZMQ;

public class Request2 {
    public static void main(String args[]) throws InterruptedException {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REQ);

        // System.out.println("Connecting to hello world server...");
        socket.connect("tcp://localhost:9999");

        String requestString = "getSingle";
        //byte[] request = requestString.getBytes();
        socket.send(requestString, 0);
        Thread.sleep(100);
        byte[] reply = socket.recv(0);
        System.out.println("客户端接收的是: [" + new String(reply) + "]");
    }

}
