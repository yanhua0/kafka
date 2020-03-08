import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.cache.study.config.CacheConstant;

public class Cache {
    public static CacheConfiguration build(String name,Integer maxEntries,Integer time){
        CacheConfiguration cacheConfiguration = new CacheConfiguration(name, maxEntries);
        cacheConfiguration.timeToLiveSeconds(time);
        cacheConfiguration.eternal(true);
        cacheConfiguration.persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP));
        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
        cacheConfiguration.setMaxEntriesLocalDisk(0);
        cacheConfiguration.setTimeToIdleSeconds(3600);
        return cacheConfiguration;
    }
    public static void main(String[] args) throws InterruptedException {
      Configuration configuration = new  Configuration();
        configuration.setName(CacheConstant.DEFAULT);
        //如果要设置不同的最大和失效时间这里多次addCache就行了。
        configuration.addCache(build("test1",1,1000000));
        CacheManager cacheManager=CacheManager.newInstance(configuration);
        Configuration c = new  Configuration();
        c.setName("123");
       // c.addCache(build("test2",1,20));



        cacheManager.getCache("test1").put(new Element("1","2"));
        cacheManager.getCache("test1").put(new Element("3","2"));
        cacheManager.getCache("test1").put(new Element("4","2"));
        Thread.sleep(60000);
System.out.println(cacheManager.getCache("test1").get("4").getObjectValue());
        //cacheManager.getCache("test1").removeAll();
//        CacheManager cc=CacheManager.newInstance(c);
//        cc.getCache("test2").put(new Element("3","4"));
//        cc.getCache("test2").put(new Element("5","4"));
//        cc.getCache("test2").put(new Element("6","4"));
//      //  cc.getCache("test2").removeAll();
//        System.out.println(cc.getCache("test2").get("6"));
    }
}
