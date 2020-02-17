package org.cache.study.config;

import net.sf.ehcache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class Task {
    @Resource
    private CacheManager ehCacheManager;

    /**
     * 每天点钟清楚缓存
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void remove(){
        ehCacheManager.getCache(CacheConstant.CACHE).removeAll();
    }

}
