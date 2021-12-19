//package org.cache.study.config;
//
//
//import net.sf.ehcache.config.CacheConfiguration;
//import net.sf.ehcache.config.PersistenceConfiguration;
//import net.sf.ehcache.distribution.CacheManagerPeerProvider;
//import net.sf.ehcache.distribution.CacheManagerPeerProviderFactory;
//import net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory;
//import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurer;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.cache.interceptor.CacheResolver;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Properties;
//
//@Configuration
//public class CacheConfig implements CachingConfigurer {
//    @Bean
//    public net.sf.ehcache.CacheManager ehCacheManagerBean() {
//        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
//        configuration.setName(CacheConstant.DEFAULT);
//        //如果要设置不同的最大和失效时间这里多次addCache就行了。
//        configuration.addCache(cacheConfiguration(CacheConstant.CACHE,0,5*60));
//        configuration.addCache(cacheConfiguration("cache2",0,5*60));
//
//        return net.sf.ehcache.CacheManager.create(configuration);
//    }
//
//    private CacheConfiguration cacheConfiguration(String name,Integer maxEntries,Integer time) {
//        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
//        cacheConfiguration.timeToLiveSeconds(time);
//        cacheConfiguration.eternal(false);
//        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
//        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
//        cacheConfiguration.setMaxEntriesLocalDisk(100000);
//        cacheConfiguration.setTimeToIdleSeconds(3600);
//        CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration bootstrapCacheLoaderFactoryConfiguration=new CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration();
//        bootstrapCacheLoaderFactoryConfiguration.setClass("net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory");
//        cacheConfiguration.addBootstrapCacheLoaderFactory(bootstrapCacheLoaderFactoryConfiguration);
//        CacheConfiguration.CacheEventListenerFactoryConfiguration cacheEventListenerFactory=new CacheConfiguration.CacheEventListenerFactoryConfiguration();
//        cacheEventListenerFactory.setClass("net.sf.ehcache.distribution.RMICacheReplicatorFactory");
//        cacheEventListenerFactory.setProperties("replicateAsynchronously=true,replicatePuts=true,replicateUpdates=true,replicateUpdatesViaCopy=false,replicateRemovals=true");
//        cacheEventListenerFactory.setPropertySeparator(",");
//        cacheConfiguration.addCacheEventListenerFactory(cacheEventListenerFactory);
//
//        return cacheConfiguration;
//    }
//
//    @Override
//    @Bean
//    public CacheManager cacheManager() {
//
//        CacheManagerPeerProviderFactory cacheManagerPeerProviderFactory=new RMICacheManagerPeerProviderFactory();
//        CacheManagerPeerProvider cacheManagerPeerProvider=cacheManagerPeerProviderFactory.createCachePeerProvider(ehCacheManagerBean(),getProperties());
//        cacheManagerPeerProvider.init();
//        return new EhCacheCacheManager(ehCacheManagerBean());
//
//
//
//    }
//    private Properties getProperties(){
//        Properties properties=new Properties();
//        properties.setProperty("hostName","localhost");
//        properties.setProperty("port","400001");
//        properties.setProperty("socketTimeoutMillis","2000");
//        properties.setProperty("peerDiscovery","manual");
//        properties.setProperty("rmiUrls","//192.168.1.4:400002/mobileCache|//192.168.1.4:400003/mobileCache");
//         return properties;
//    }
//
//    @Override
//    public CacheResolver cacheResolver() {
//        return null;
//    }
//
//    /**
//     * CacheKey的value和入参值 共同组成 key,入参不要用对象,对象使用的是内存地址
//     *没有定义key才会执行
//     * @return
//     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (Object target, Method method, Object... params) -> {
//            StringBuilder sb = new StringBuilder(16);
//            Class clazz=target.getClass();
//            Annotation[] annotations=clazz.getAnnotationsByType(CacheKey.class);
//            for (Annotation annotation : annotations) {
//                CacheKey cacheKey= (CacheKey) annotation;
//                sb.append(cacheKey.value());
//                sb.append("_");
//            }
//            for (int i = 0; i < params.length; i++) {
//                sb.append(params[i]);
//                if (i < params.length - 1) {
//                    sb.append("_");
//                }
//            }
//            System.out.println(sb.toString());
//            return sb.toString();
//        };
//    }
//
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return null;
//    }
//}
