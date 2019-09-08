package org.reflect.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        Class clazz=Animal.class;
        Field[] fields = Animal.class.getDeclaredFields();//获取所有字段
        String fieldName = fields[0].getName();//获取字段名
        System.out.println(fields[0]);
        System.out.println(fieldName);
        Class aClass= fields[0].getType();//判断字段类型
        Method[] method=clazz.getDeclaredMethods();//获取所有声明的方法
        System.out.println(method[0].getParameters()[0].getType());//参数的获取,getType获取类型,getName入参的名字
        System.out.println(aClass==Integer.class);
        Map<String,String> map=new HashMap<>();

        System.out.println("NAME".contains("name"));
        //Optional<Map> optionalMap=Optional.ofNullable(map).orElse(null);

    }
}
