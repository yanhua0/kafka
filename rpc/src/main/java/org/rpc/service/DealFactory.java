package org.rpc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.*;
@Slf4j
public class DealFactory implements BeanPostProcessor {
   private static   Map<String,DealService> dealServiceMap=new HashMap<>();

    public  DealService getDealServiceMap(String code) {
        return dealServiceMap.get(code);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(bean instanceof DealService){
            dealServiceMap.put((String) ((DealService)bean).source(),(DealService)bean);
        }
        return bean;
    }
public static void main(String[] args){
    /** 创建资源 */
    Object resourceA = new Object();
    Object resourceB = new Object();
    // 创建线程
    Thread threadA = new Thread(() -> {
        synchronized (resourceA) {
            log.info(Thread.currentThread() + " get ResourceA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread() + "waiting get resourceB");
            synchronized (resourceB) {
                log.info(Thread.currentThread() + " get resourceB");
            }
        }
    });

    Thread threadB = new Thread(() -> {
        synchronized (resourceB) {
            log.info(Thread.currentThread() + " get ResourceB");
//            try {
//               // Thread.sleep(1000);
//            } catch (InterruptedException e) {
//               // e.printStackTrace();
//            }
            log.info(Thread.currentThread() + "waiting get resourceA");
            synchronized (resourceA) {
                log.info(Thread.currentThread() + " get resourceA");
            }
        }
    });
    threadA.start();
    threadB.start();

}
}
