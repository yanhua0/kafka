package org.kafka.test.zeromq.test;

import org.zeromq.ZMQ;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Client {
    public static void main(String args[]){
        try {
//            ZContext context= new ZContext()
            ZMQ.Context context = ZMQ.context(1);
           // ZMQ.Socket subscriber = context.createSocket(ZMQ.SUB); //subscribe类型
            ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
            subscriber.connect("tcp://localhost:9999");

            subscriber.subscribe("".getBytes()); //只订阅Time: 开头的信息

            for (int i = 0; i < 1000; i++) {
             //   String v=subscriber.recvStr();
                byte[] bytes=subscriber.recv();
                String vv=new String(bytes, StandardCharsets.ISO_8859_1);
                byte[] b=vv.getBytes(StandardCharsets.ISO_8859_1);
                File F=new File("C:\\Users\\Administrator\\Desktop\\"+ UUID.randomUUID().toString()+".jpg");
                FileOutputStream outputStream=new FileOutputStream(F);
                outputStream.write(b);
                outputStream.close();
               // System.out.println(subscriber.recvStr()); //recvStr直接返回String，内部调用了recv，将byte数组转化为String
            }
        }catch (Exception e){

        }

    }

}
