package org.rpc.service;

import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.*;

public class DealFactory implements BeanPostProcessor {
   private static   Map<String,DealService> dealServiceMap=new HashMap<>();

    public  DealService getDealServiceMap(String code) {
        return dealServiceMap.get(code);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(bean instanceof DealService){
            dealServiceMap.put((String) ((DealService)bean).source(),(DealService)bean);
        }
        return bean;
    }

}
