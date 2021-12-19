//package org.cache.study.config;
//
//import net.sf.ehcache.CacheManager;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Component
//@EnableScheduling
//public class Task {
//    @Resource
//    private CacheManager ehCacheManager;
//
//    /**
//     * 每天点钟清楚缓存
//     */
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void remove(){
//        ehCacheManager.getCache(CacheConstant.CACHE).removeAll();
//    }
//    public static void main(String[] args){
//        ExecutorService executorService= Executors.newFixedThreadPool(10);
//        List<Integer> ls=new ArrayList<>();
//        ls.add(2);
//        ls.add(3);
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(()->{
//                         synchronized (ls){
//
//                         }
//            });
//        }
//
//
//    }
//
//}
