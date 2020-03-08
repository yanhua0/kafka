package org.rpc.config;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableScheduling
@EnableAsync
public class Task {
    private Integer count=0;
   // @Scheduled(cron ="*/5 * * * * ?")
  //  @Async@EnableAsync同时使用
   // @Async
    public void s1() throws InterruptedException {
       System.out.println("当时任务");


    }
   // @Scheduled(cron ="*/5 * * * * ?")
    public void s2() throws InterruptedException {
        Thread.sleep(1000000);
       System.out.println("任务2"+Thread.currentThread().getName());
    }
   // @Scheduled(cron ="*/5 * * * * ?")
    public void s3() throws InterruptedException {
        System.out.println("任务3"+Thread.currentThread().getName());
    }
  //  @Scheduled(cron ="*/5 * * * * ?")
    public void s42() throws InterruptedException {
        System.out.println("任务4"+Thread.currentThread().getName());
    }
//    public static void main(String[] args){
//        List<Integer> is=new ArrayList<>();
//        is.add(1);is.add(2);
//        System.out.println(is.stream().filter(e->e==1).collect(Collectors.toList()));
//    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        ExecutorService executorService2= Executors.newFixedThreadPool(10);
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            executorService.execute(()->{
                list.add(3);

            });
        }

        executorService.execute(()->{
            list.remove(1);
        });
        Thread.sleep(1000);
   System.out.println(list);

    }
}
