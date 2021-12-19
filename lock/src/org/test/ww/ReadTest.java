package org.test.ww;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadTest {
    public void ss() throws InterruptedException {
        wait();
    }
    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(100);
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        for (int i = 0; i <20000 ; i++) {
            executorService.execute(()->{
                long s=System.currentTimeMillis();
               readWriteLock.readLock().lock();
                System.out.println("lock-----------");
              readWriteLock.readLock().unlock();
                long e=System.currentTimeMillis();
                System.out.println(e-s+"ms");

            });
        }
    }
}
