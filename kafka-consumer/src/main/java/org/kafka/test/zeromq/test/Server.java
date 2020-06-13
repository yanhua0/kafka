package org.kafka.test.zeromq.test;

import org.zeromq.ZMQ;

import java.io.File;

import static org.kafka.test.testMain.Producer.File2byte;

public class Server {
    public static void main(String[] args) throws InterruptedException {

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://127.0.0.1:9999");
        File file=new File("C:\\Users\\Administrator\\Desktop\\下载.jpg");



        byte[] xml= File2byte(file);
        while (true){
          boolean s=  publisher.send(xml, 0); //发送
              publisher.send(xml, 1); //发送
            System.out.println(s);
            Thread.sleep(3000);
        }

    }

}
