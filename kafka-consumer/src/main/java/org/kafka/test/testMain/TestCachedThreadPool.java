package org.kafka.test.testMain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author leizhimin 2008-11-25 14:28:59
 */
public class TestCachedThreadPool {
    public static int j=0;

        public static void main(String[] args) {

           Test test= test1 -> System.out.println("111");
           test.print(() -> System.out.println("213"));
           Test1 test1=null;

//                ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//         ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
        executorService.execute(()->{

            try {
                synchronized(TestCachedThreadPool.class)
                {
                    Thread.sleep(5000);
                    j++;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程被调用了。"+j);
            executorService.shutdown();
            executorService.execute(()->{
                System.out.println("第二个");
            });
//            while (true) {
//                try {
//
//                    System.out.println(Thread.currentThread().getName());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        });
        System.out.println("************* a" + i + " *************");
        }

        }
        }

//class TestRunnable implements Runnable {
//    public void run() {
//
//    }
//}