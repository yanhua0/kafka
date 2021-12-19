package org.redis.test.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        String value="";
        try {
           value= redisTemplate.opsForValue().get(key);
            JSONObject.toJSONString(value);
            JSONObject.toJSONString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }finally {
//            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
//        }
       return value;
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,60, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            //更新并且返回旧的value;
           String v= redisTemplate.opsForValue().getAndSet(key, value);
           System.out.println(v);
           System.out.println("Va="+redisTemplate.opsForValue().get(key));
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}