package org.kafka.test.zeromq;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

public class Response2 {
    public static void main(String[] args) throws InterruptedException {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        String url = "tcp://*:9999";
        socket.bind(url);//绑定地址
        while (true) {//服务器一直循环
            byte[] request;
            try {
                request = socket.recv(0);//接收的客户端数据
                String getData=new String(request);
                if (getData.equals("getSingle")) {
                    socket.send("OK",1);
                }else{
                    socket.send("error",1);
                }

            } catch (ZMQException e) {
                throw e;
            }
        } // while(wait)
    }

}
