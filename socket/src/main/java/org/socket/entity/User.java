package org.socket.entity;

import lombok.Getter;
import lombok.Setter;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.pool.sizeof.AgentSizeOf;
import org.apache.lucene.util.RamUsageEstimator;

import java.io.Serializable;
@Getter
@Setter
public class User implements Serializable{
    private String userId;
    private String name;
    private Double s;
    private Double t;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static void main(String[] args) throws InterruptedException {
        Configuration configuration=new Configuration();
        CacheConfiguration cacheConfiguration=new CacheConfiguration("test",0);
        cacheConfiguration.eternal(true);
        configuration.addCache(cacheConfiguration);
        CacheManager cacheManager= CacheManager.create(configuration);
        Cache cache=cacheManager.getCache("test");

        User u=new User();
        u.setName("112312312312312311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232312312311231231231231231231231231231231231232311231231231231231231231231231231231232311231231231231231231231231231231231232312312312312312323");
        u.setUserId("112312312312312312312311231231231231231231211231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232311231231231231231231231123123123123123123123123123123123123231123123123123123123123123123123123123231123123123123123123123123123123123123231231231231231232331231231231231232311231231231231231231231231231231231232311231231231231231231231231231231231232312312312312312323");
        u.setS(123.0);
        u.setT(12312312.12312);
        for (int i = 0; i < 100000; i++) {
          cache.put(new Element(i,u));
        }
        System.out.println(cache.getMemoryStoreSize());
        System.out.println(new AgentSizeOf().sizeOf(cache));
        System.out.println(RamUsageEstimator.humanSizeOf(cache.get(0)));


    }
}
