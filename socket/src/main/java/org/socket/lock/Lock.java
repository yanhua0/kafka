package org.socket.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {
    public static void main(String[] args){
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        AtomicReference<String> ss= new AtomicReference<>("1232131");
        //如果一个线程获取了读锁，那么另外的线程想要获取写锁则需要等待释放；
        // 而如果一个线程已经获取了写锁，则另外的线程想获取读锁或写锁都需要等待写锁被释放。
        //读锁共享，写锁互斥
        while (true){
            executorService.execute(()->{

                readWriteLock.readLock().lock();
                System.out.println("读锁"+System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ss.get());

                readWriteLock.readLock().unlock();
            });
            ExecutorService executorService1=Executors.newFixedThreadPool(3);
            executorService1.execute(()->{

                try {
                    readWriteLock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName()+"写锁");
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ss.set("hello");
                readWriteLock.writeLock().unlock();
            });
        }

    }
}
