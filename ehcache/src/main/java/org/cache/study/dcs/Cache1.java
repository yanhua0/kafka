package org.cache.study.dcs;

import com.alibaba.fastjson.JSONObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

public class Cache1 {
    /**
     * * @param port hostName=127.0.0.1,port=8080,socketTimeoutMillis=2000
     * * @param rmi peerDiscovery=manual,rmiUrls=//127.0.0.1:8081/cache_test
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        String name = "cache_test";
        CacheManager cacheManager = CacheManagerFactory.createDCS(name, "hostName=127.0.0.1,port=8080,socketTimeoutMillis=2000", "peerDiscovery=manual,rmiUrls=//127.0.0.1:8080/cache_test");
        Cache cache = cacheManager.getCache(name);
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        ManagementService.registerMBeans(cacheManager, mBeanServer, true, true,
//                true, true);
        while (true) {
            List list = cache.getKeys();
            System.out.println("list=" + JSONObject.toJSONString(list));
            for (int i = 0; i < 100; i++) {
                try {
                    cache.put(new Element(String.valueOf(i), String.valueOf(i)));
//                    Element element = cache.get(String.valueOf(i));
//                    System.out.println(element.getObjectValue());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            Thread.sleep(10000);
        }
    }
}
