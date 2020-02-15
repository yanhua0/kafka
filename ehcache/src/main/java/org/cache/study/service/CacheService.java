package org.cache.study.service;


import org.cache.study.config.CacheConstant;
import org.cache.study.config.CacheKey;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "cache")
@CacheKey(CacheConstant.DEVICE_PREFIX)//key的前缀
public class CacheService {
    public static String cache="cc";

    /**
     * 查询时候的调用方法
     * @param ss
     * @return
     */
    @Cacheable
    public String ss(String ss) {
        return cache;

    }

    /**
     * 修改时调用的方法
     * @param ss
     * @return
     */
    @CachePut
    public String update(String ss) {
        return ss;
    }

    /**
     * 根据key删除一个
     * @param ss
     */
    @CacheEvict
    public void delete(String ss){

    }
    //删除所有的
    @CacheEvict(allEntries=true)
    public void deleteAll(){

    }
}
