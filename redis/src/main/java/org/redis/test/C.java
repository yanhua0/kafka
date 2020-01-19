package org.redis.test;


import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class C {
    @Bean
    public CacheManager cacheManager(){
        CacheManager cacheManager=new ConcurrentMapCacheManager();
      return new ConcurrentMapCacheManager();
    }

}
