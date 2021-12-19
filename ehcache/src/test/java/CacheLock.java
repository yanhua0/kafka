import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.cache.study.config.CacheConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheLock {
    public static CacheConfiguration build(String name, Integer maxEntries, Integer time) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
        cacheConfiguration.timeToLiveSeconds(time);
        cacheConfiguration.eternal(true);
        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
        cacheConfiguration.setMaxEntriesLocalDisk(100000);
        cacheConfiguration.setTimeToIdleSeconds(3600);
        return cacheConfiguration;
    }
    public static CacheManager cacheManager=createS();
    public static CacheManager createS() {
        Configuration configuration = new Configuration();
        configuration.setName(CacheConstant.DEFAULT);
//        //如果要设置不同的最大和失效时间这里多次addCache就行了。
//        configuration.addCache(build("test1", 100, 1000000));
        CacheManager cacheManager = CacheManager.newInstance(configuration);
        Cache cache=new Cache(build("test1", 100, 1000000));
        cacheManager.addCache(cache);
//        synchronized (CacheLock.class){
//
//            if(cacheManager.getCache("test1")==null) {
//
//            }
//        }


        return cacheManager;
    }

    public static void main(String[] args) throws InterruptedException {
      //  create().removeCache("test1");
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
     //   CacheManager cacheManager = create();
//        Cache cache = cacheManager.getCache("test1");
//        cache.put(new Element("tt", "val"));
        ExecutorService executorService = new ThreadPoolExecutor(200, 222,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2222), new ThreadPoolExecutor.DiscardPolicy());
        executorService.execute(() -> {
            for (; ; ) {
                executorService.execute(() -> {
                    try {
                        readWriteLock.readLock().lock();
                        long st = System.currentTimeMillis();
                        Cache cache3 = cacheManager.getCache("test1");
                        //  System.out.println("缓存获取时间="+(System.currentTimeMillis()-st));
                        if(cache3==null){
                            throw new RuntimeException("错误为空!");
                        }
                        Element element = cache3.get("tt");
                        String s = element != null ? (String) element.getObjectValue() : null;
                        if (s == null) {
                            long st2 = System.currentTimeMillis();
                            cache3.put(new Element("tt", "val"));
                            // System.out.println("缓存PUT时间="+(System.currentTimeMillis()-st2));
                        }
                      readWriteLock.readLock().unlock();
                    } catch (Exception e) {
                       executorService.shutdownNow();
                    }
                    //
                });
            }
        });

        ExecutorService executorService2 = new ThreadPoolExecutor(22, 22,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1111), new ThreadPoolExecutor.DiscardPolicy());
        executorService.execute(() -> {

            for (; ; ) {
                executorService2.execute(() -> {
                  readWriteLock.writeLock().lock();

                    Long s = System.currentTimeMillis();
                    System.out.println("清除缓存开始----");
                    cacheManager.removeCache("test1");
                    Cache cache=new Cache(build("test1", 100, 1000000));
                    cacheManager.addCache(cache);
                    System.out.println("清除缓存结束----" + (System.currentTimeMillis() - s));

                 readWriteLock.writeLock().unlock();
                });
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }
}
