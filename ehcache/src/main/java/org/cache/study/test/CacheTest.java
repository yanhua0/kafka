package org.cache.study.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CacheManagerPeerProviderFactory;
import net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.cache.study.config.CacheConstant;

import java.util.Properties;

public class CacheTest {
    public static CacheManagerPeerProvider init(CacheManager cacheManager,String port){
        CacheManagerPeerProviderFactory cacheManagerPeerProviderFactory=new RMICacheManagerPeerProviderFactory();
        CacheManagerPeerProvider cacheManagerPeerProvider=cacheManagerPeerProviderFactory.createCachePeerProvider(cacheManager,getProperties(port));
        cacheManagerPeerProvider.init();
       return cacheManagerPeerProvider;
    }
    private static Properties getProperties(String port){
        Properties properties=new Properties();
        properties.setProperty("hostName","192.168.1.4");
        properties.setProperty("port",port);
        properties.setProperty("socketTimeoutMillis","2000");
        properties.setProperty("peerDiscovery","manual");
        properties.setProperty("rmiUrls","//192.168.1.4:40002/test|//192.168.1.4:40003/test");
        return properties;
    }
    public static CacheConfiguration cacheConfiguration(String name,Integer maxEntries,Integer time) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
        cacheConfiguration.timeToLiveSeconds(time);
        cacheConfiguration.eternal(false);
        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
        cacheConfiguration.setMaxEntriesLocalDisk(100000);
        cacheConfiguration.setTimeToIdleSeconds(3600);
//        CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration bootstrapCacheLoaderFactoryConfiguration=new CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration();
//        bootstrapCacheLoaderFactoryConfiguration.setClass("net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory");
        //cacheConfiguration.addBootstrapCacheLoaderFactory(bootstrapCacheLoaderFactoryConfiguration);
//        CacheConfiguration.CacheEventListenerFactoryConfiguration cacheEventListenerFactory=new CacheConfiguration.CacheEventListenerFactoryConfiguration();
//        cacheEventListenerFactory.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
//        cacheEventListenerFactory.setProperties("replicateAsynchronously=true,replicatePuts=true,replicateUpdates=true,replicateUpdatesViaCopy=false,replicateRemovals=true");
//        cacheEventListenerFactory.setPropertySeparator(",");
//        cacheConfiguration.addCacheEventListenerFactory(cacheEventListenerFactory);

        return cacheConfiguration;
    }
    public static void main(String[] args) throws InterruptedException {

        Configuration configuration = new Configuration();
        configuration.setName(CacheConstant.DEFAULT);
        //如果要设置不同的最大和失效时间这里多次addCache就行了。
        configuration.addCache(cacheConfiguration("test", 1, 1000000));
        CacheManager cacheManager = CacheManager.newInstance(configuration);
        CacheManagerPeerProvider cacheManagerPeerProvider= init(cacheManager,"40001");
        Cache cache=cacheManager.getCache("test");
        cache.put(new Element("1","123"));
        System.out.println(cache.get("1").getObjectValue());

        while (true){
            Thread.sleep(5000);
            cache.put(new Element("1","123"));
         System.out.println(   cacheManagerPeerProvider.listRemoteCachePeers(cache).size());
        }
    }
}
