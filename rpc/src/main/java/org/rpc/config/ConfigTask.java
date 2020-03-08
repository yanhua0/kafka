package org.rpc.config;

import org.rpc.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTask {
    /**
     * 指定一个@Scheduled方法的异步执行的线程数量,非异步执行配置无用
     * @return
     */
//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor(){
////        TaskScheduler taskScheduler=new ThreadPoolTaskExecutor();
////        taskScheduler.set
//        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(1);
//        threadPoolTaskExecutor.setMaxPoolSize(1);
//        threadPoolTaskExecutor.setThreadNamePrefix("test");
//        threadPoolTaskExecutor.setQueueCapacity(1);
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//      //  ((ThreadPoolTaskScheduler) taskScheduler).setPoolSize(10);
//        return threadPoolTaskExecutor;
//    }

    /**
     * taskScheduler指定@Scheduled注解方法的线程数量
     * @return
     */
//    @Bean
//    public TaskScheduler taskScheduler(){
//        TaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
//       ((ThreadPoolTaskScheduler) taskScheduler).setThreadNamePrefix("123");
//       ((ThreadPoolTaskScheduler) taskScheduler).setPoolSize(1);
//
//        return taskScheduler;
//    }
    @Bean
    public User user(){
        User user=new User();
        return user;
    }
}
