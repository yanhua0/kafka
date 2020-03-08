package org.cache.study.controller;


import net.sf.ehcache.CacheManager;
import org.cache.study.config.CacheConstant;
import org.cache.study.entity.Users;
import org.cache.study.service.CacheService;
import org.cache.study.service.CacheService2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private CacheService cacheService;
    @Resource
    private CacheManager ehCacheManagerBean;
    @Resource
    private CacheService2 cacheService2;

    @GetMapping("/cache")
    public String ss(String s) {
        Users users=new Users();
        users.setId(1);
        users.setSs(222);
        System.out.println(ehCacheManagerBean.getCache(CacheConstant.CACHE).getMemoryStoreSize());
        System.out.println(ehCacheManagerBean.getCache(CacheConstant.CACHE).getDiskStoreSize());
        System.out.println(ehCacheManagerBean.getDiskStorePathManager());
        System.out.println(ehCacheManagerBean.getCache(CacheConstant.CACHE).get(CacheConstant.DEVICE_PREFIX+s));
        cacheService2.ss(s);
        System.out.println(cacheService.ss(s));
        return cacheService.ss(s);
    }

    @GetMapping("/update")
    public String ss2(String s) {

        return cacheService.update(s);
    }

    @GetMapping("/del")
    public void ss3(String s) {
//        Cache cache=ehCacheManager.getCache("cache");
//
//        System.out.println(cache.get("my_12"));
//        System.out.println(cache.get("12"));
//        cacheService.deleteAll();
        ehCacheManagerBean.removeAllCaches();
      //  cacheService.delete(s);
    }
}
