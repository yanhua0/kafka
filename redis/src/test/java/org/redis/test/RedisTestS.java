//package org.redis.test;
//
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//
//import java.util.concurrent.TimeUnit;
//
//public class RedisTestS {
//    public static void main(String[] args){
//        Config config = new Config();
//        config.useSentinelServers().addSentinelAddress("redis://127.0.0.1:6369")
//                .setPassword("").setDatabase(0);
//        RedissonClient redissonClient = Redisson.create();
//// 还可以getFairLock(), getReadWriteLock()
//        RLock redLock = redissonClient.getLock("REDLOCK_KEY");
//        boolean isLock;
//        try {
////            isLock = redLock.tryLock();
//            // 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
//            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
//            if (isLock) {
//                System.out.println("ttt");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            // 无论如何, 最后都要解锁
//            redLock.unlock();
//        }
//    }
//}
