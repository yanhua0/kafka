package org.cache.study.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CacheManagerPeerProviderFactory;
import net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory;
import org.cache.study.config.CacheConstant;

import java.util.Properties;

import static org.cache.study.test.CacheTest.cacheConfiguration;

public class CacheTest1 {
    public static void init(CacheManager cacheManager){
        CacheManagerPeerProviderFactory cacheManagerPeerProviderFactory=new RMICacheManagerPeerProviderFactory();
        CacheManagerPeerProvider cacheManagerPeerProvider=cacheManagerPeerProviderFactory.createCachePeerProvider(cacheManager,getProperties());
        cacheManagerPeerProvider.init();
    }
    private static Properties getProperties(){
        Properties properties=new Properties();
        properties.setProperty("hostName","192.168.1.4");
        properties.setProperty("port","40002");
        properties.setProperty("socketTimeoutMillis","2000");
        properties.setProperty("peerDiscovery","manual");
        properties.setProperty("rmiUrls","//192.168.1.4:40001/test|//192.168.1.4:40003/test");
        return properties;
    }
    public static void main(String[] args) throws InterruptedException {
        Configuration configuration = new Configuration();
        configuration.setName(CacheConstant.DEFAULT);
        //如果要设置不同的最大和失效时间这里多次addCache就行了。
        configuration.addCache(cacheConfiguration("test", 1, 1000000));
        CacheManager cacheManager = CacheManager.newInstance(configuration);
        Cache cache=cacheManager.getCache("test");
        init(cacheManager);
        while (true){
            Thread.sleep(2000);
            try {
                System.out.println("读取值----");
                System.out.println(cache.get("1").getObjectValue());
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
        }
    }
}
