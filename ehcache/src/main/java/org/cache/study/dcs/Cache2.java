package org.cache.study.dcs;

import com.alibaba.fastjson.JSONObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.util.List;

public class Cache2 {
    public static void main(String[] args) throws InterruptedException {
        String name = "cache_test";
        CacheManager cacheManager = CacheManagerFactory.createDCS(name, "hostName=127.0.0.1,port=8081,socketTimeoutMillis=2000", "peerDiscovery=manual,rmiUrls=//127.0.0.1:8080/cache_test");
        Cache cache = cacheManager.getCache(name);
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        ManagementService.registerMBeans(cacheManager, mBeanServer, true, true,
//                true, true);
        while (true) {
            List list = cache.getKeys();
            System.out.println("list=" + JSONObject.toJSONString(list));
            for (Object o : list) {
                System.out.println("key=" + o);
            }
            Thread.sleep(10000);
        }
    }
}
