package org.socket.entity;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args){
    Map<String,String> map=new HashMap<>();
    map.put("123","!");
    String s2=map.get("123");
    map.clear();
    System.out.println(s2);
    }
}
