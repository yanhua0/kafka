package org.rpc.web;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        for(int i=0;i<123;i++){
            executorService.execute(()->{
                        try {
                            System.out.println("第一个");
                            Thread.sleep(30000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
            executorService.execute(()->{

                            System.out.println("第二个"+new Date());

                    }
            );
        }
    }
}
