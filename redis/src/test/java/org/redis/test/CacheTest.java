package org.redis.test;


import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.disk.DiskStore;

public class CacheTest {
    public static void main(String[] args){
        CacheManager cacheManager= CacheManager.create();

 Element element=cacheManager.getCache("!23").get();
        CacheConfiguration cacheConfiguration=new CacheConfiguration();
cacheConfiguration.setOverflowToOffHeap(true);
      cacheConfiguration.setMaxElementsInMemory(2);

    }
}
