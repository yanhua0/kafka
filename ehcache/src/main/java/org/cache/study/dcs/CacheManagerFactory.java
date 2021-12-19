//package org.cache.study.dcs;
//
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.config.CacheConfiguration;
//import net.sf.ehcache.config.Configuration;
//import net.sf.ehcache.config.FactoryConfiguration;
//import net.sf.ehcache.config.PersistenceConfiguration;
//import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
//
///**
// * Ehcache 分布式缓存的配置方法
// */
//public class CacheManagerFactory {
//    public static CacheManager createDCS(String name,String port,String rmi){
//        CacheManager cacheManager=create(name,port,rmi);
////        CacheManagerPeerProviderFactory cacheManagerPeerProviderFactory=new RMICacheManagerPeerProviderFactory();
////        cacheManagerPeerProviderFactory.createCachePeerProvider(cacheManager,getProperties1(port,rmi));
//
//        return cacheManager;
//    }
//
//    /**
//     *
//     * @param name
//     * @param port hostName=127.0.0.1,port=8080,socketTimeoutMillis=2000
//     * @param rmi peerDiscovery=manual,rmiUrls=//127.0.0.1:8081/cache_test
//     * @return
//     */
//    public  static CacheManager create(String name,String port,String rmi) {
//        Configuration configuration = new net.sf.ehcache.config.Configuration();
//        configuration.setName(name);
//        configuration.addCache(cacheConfiguration(name));
//        FactoryConfiguration configuration1=new FactoryConfiguration();
//        configuration1.setClass("net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory");
//        //configuration1.setProperties("peerDiscovery=manual,rmiUrls=//127.0.0.1:8081/sampleCache1");
//        configuration1.setProperties(rmi);
//        configuration1.setPropertySeparator(",");
//        FactoryConfiguration configuration2=new FactoryConfiguration();
//       // configuration2.setProperties("hostName=192.168.1.103,port=40001,socketTimeoutMillis=2000");
//        configuration2.setProperties(port);
//        configuration2.setClass("net.sf.ehcache.distribution.RMICacheManagerPeerListenerFactory");
//        configuration2.setPropertySeparator(",");
//        configuration.addCacheManagerPeerListenerFactory(configuration2);
//        configuration.addCacheManagerPeerProviderFactory(configuration1);
//
//        return CacheManager.newInstance(configuration);
//    }
//
//    /**
//     * replicateUpdatesViaCopy 为false时候,某结点修改其他所有的结点的服务清楚缓存。net.sf.ehcache.distribution.RMISynchronousCacheReplicator.notifyElementUpdated()定义
//     * @param name
//     * @return
//     */
//    private static CacheConfiguration cacheConfiguration(String name) {
//        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, 0);
//        cacheConfiguration.timeToLiveSeconds(0);
//        cacheConfiguration.eternal(true);
//        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
//        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
//        cacheConfiguration.setMaxEntriesLocalDisk(100000);
//        cacheConfiguration.setTimeToIdleSeconds(3600);
//        CacheConfiguration.CacheEventListenerFactoryConfiguration configuration=new CacheConfiguration.CacheEventListenerFactoryConfiguration();
//        configuration.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
//        configuration.setProperties("replicateAsynchronously=true, replicatePuts=true, \n" +
//                "        replicateUpdates=true,\n" +
//                "        replicateUpdatesViaCopy=true, \n" +
//                "        replicateRemovals=true");
//        cacheConfiguration.addCacheEventListenerFactory(configuration);
//
//        CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration bootstrapCacheLoaderFactoryConfiguration=new CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration();
//        bootstrapCacheLoaderFactoryConfiguration.setClass("net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory");
//        cacheConfiguration.addBootstrapCacheLoaderFactory(bootstrapCacheLoaderFactoryConfiguration);
//
//        // CacheManagerPeerProviderFactoryConfiguration cacheManagerPeerProviderFactoryConfiguration=new CacheManagerPeerProviderFactoryConfiguration()
//        return cacheConfiguration;
//    }
//}
