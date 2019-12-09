package org.rpc.web;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            try{
                int finalI = i;
                executorService.execute(()->{
                            System.out.println("第一个");
                            if(finalI ==3){
                                throw new RuntimeException("错误");
                            }
                        }
                );
            }catch(Exception e){
                System.out.println(i);
          //   e.printStackTrace();
            }


        }
    }
}
