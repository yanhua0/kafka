package org.redis.test.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service

public class CacheService {
    public static String ss = "123";

    @Cacheable(value = "cache", key = "#ss+''")
    public String ss(String ss) {
        return ss;
    }

    @CachePut(value = "cache", key = "#ss+''")
    public String update(String ss) {
        return "修改";
    }
}
