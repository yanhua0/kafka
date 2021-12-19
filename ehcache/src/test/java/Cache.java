//import org.ehcache.CacheManager;
//import org.ehcache.config.builders.CacheConfigurationBuilder;
//import org.ehcache.config.builders.CacheManagerBuilder;
//import org.ehcache.config.builders.ResourcePoolsBuilder;
//
//public class Cache {
////    public static CacheConfiguration build(String name, Integer maxEntries, Integer time) {
////        BaseCacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
////        cacheConfiguration.timeToLiveSeconds(time);
////        cacheConfiguration.eternal(true);
////        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
////        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
////        cacheConfiguration.setMaxEntriesLocalDisk(0);
////        cacheConfiguration.setTimeToIdleSeconds(3600);
////        cacheConfiguration.setOverflowToDisk(true);
////        return cacheConfiguration;
////    }
//
//    public static void main(String[] args) throws InterruptedException {
//        CacheManager cacheManager
//                = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
//                .build();
//        cacheManager.init();
//
//        org.ehcache.Cache<Long,String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
//        Cache<Long, String> myCache = cacheManager.createCache("myCache", acheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build());
//
//        myCache.put(1L, "da one!");
//        String value = myCache.get(1L);
//
//        cacheManager.removeCache("preConfigured");
//
//        cacheManager.close();
//
//
//    }
//}
