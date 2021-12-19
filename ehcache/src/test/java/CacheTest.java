//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;
//import net.sf.ehcache.config.CacheConfiguration;
//import net.sf.ehcache.config.Configuration;
//import net.sf.ehcache.config.PersistenceConfiguration;
//import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
//import org.cache.study.config.CacheConstant;
//
//import java.util.concurrent.*;
//
//public class CacheTest {
//    private static CacheManager cm = null;
//    public static CacheConfiguration build(String name, Integer maxEntries, Integer time){
//        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
//        cacheConfiguration.timeToLiveSeconds(time);
//        cacheConfiguration.eternal(true);
//        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
//        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
//        cacheConfiguration.setMaxEntriesLocalDisk(0);
//        cacheConfiguration.setTimeToIdleSeconds(3600);
//        return cacheConfiguration;
//    }
//    private static ConcurrentHashMap cacheTable = new ConcurrentHashMap();
//
//    public static void init() {
//
//        Configuration configuration = new  Configuration();
//        configuration.setName(CacheConstant.DEFAULT);
//        //如果要设置不同的最大和失效时间这里多次addCache就行了。
//        configuration.addCache(build("longCache",1,1000000));
//        CacheManager cacheManager=
//        cm = CacheManager.newInstance(configuration);
//
//        String[] names = cm.getCacheNames();
//
//        for (String s : names) {
//            System.out.println("put:" + s);
//
//            cacheTable.put(s.toLowerCase(), cm.getCache(s));
//
//        }
//
//    }
//
//    private static Cache getCache(String cacheName) {
//        return ((Cache) cacheTable.get(cacheName.toLowerCase()));
//
//    }
//
//    public static Object getCacheValue(String cacheName, String key) {
//        try {
//            Element element = getCache(cacheName).get(key);
//
//            if (element != null) {
//                return element.getObjectValue();
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        return null;
//
//    }
//
//    public static void setCacheValue(String cacheName, String key, Object value) {
//        try {
//            Element element = new Element(key, value);
//
//            getCache(cacheName).put(element);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//        init();
//
//        setCacheValue("longCache", "test1", "value1");
//
//        final Object cacheValue = getCacheValue("longCache", "test1");
//
//        System.out.println(cacheValue);
//
////给jvisual连接时间，获取线程dump
//
//        try {
//            Thread.sleep(5000L);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//        }
//
//        Executor executor = new ThreadPoolExecutor(100, 320, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(50000000));
//
//        for (int i = 0; i < 100000; i++) {
//            int finalI = i;
//
//            try {
//                executor.execute(() -> {
//                    long begin = System.currentTimeMillis();
//
//                    setCacheValue("longCache", "test_INFODO_DAFASDFAFEWFEWF" + finalI, "value" + finalI);
//
//                    System.out.println("put cost:" + (System.currentTimeMillis() - begin));
//
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//
//        }
//
//        for (int i = 0; i < 100000; i++) {
//            int finalI = i;
//
//            try {
//                executor.execute(() -> {
//                    long beginTime = System.currentTimeMillis();
//
//                    final Object longCache = getCacheValue("longCache", "test_INFODO_DAFASDFAFEWFEWF" + finalI);
//
//                    System.out.println(longCache + " cost:" + (System.currentTimeMillis() - beginTime));
//
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//
//        }
//
//    }
//
//}
