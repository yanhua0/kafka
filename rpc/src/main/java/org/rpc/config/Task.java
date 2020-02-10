package org.rpc.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @Scheduled(cron ="*/5 * * * * ?")
    public void s1() throws InterruptedException {
        System.out.println("任务11"+Thread.currentThread().getName());
        Thread.sleep(10000);
        System.out.println("任务1"+Thread.currentThread().getName());
    }
    @Scheduled(cron ="*/5 * * * * ?")
    public void s2() throws InterruptedException {

       System.out.println("任务2"+Thread.currentThread().getName());
    }
    @Scheduled(cron ="*/5 * * * * ?")
    public void s3() throws InterruptedException {
        System.out.println("任务3"+Thread.currentThread().getName());
    }
    @Scheduled(cron ="*/5 * * * * ?")
    public void s42() throws InterruptedException {
        System.out.println("任务4"+Thread.currentThread().getName());
    }
}
