package org.kafka.test.zeromq;

import org.zeromq.ZMQ;

public class Subscriber {
    public void start(){
        System.out.println("===========subscriber start=============");
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.SUB);
        socket.connect("tcp://127.0.0.1:9999");//绑定地址
       socket.subscribe("".getBytes());
        while(true){
            byte[] res =socket.recv(0);
            String resStr = new String(res);
            System.out.println("substring is ="+resStr);
            if("END".equalsIgnoreCase(resStr)){
                break;
            }
        }
        System.out.println("===========subscriber end=============");
        socket.close();
        context.term();
    }
}
