package org.kafka.test.zeromq;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Publisher publisher=new Publisher();
        Subscriber subscriber=new Subscriber();
       new Thread(() -> {
           try {
               publisher.start();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }).start();
       Thread.sleep(2000);
        subscriber.start();
    }
}
