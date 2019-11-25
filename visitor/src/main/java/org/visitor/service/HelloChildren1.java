package org.visitor.service;

public class HelloChildren1 implements Say{
    @Override
    public void hello(Children1 children1) {
        System.out.println("你好1");
    }

    @Override
    public void hello(Children2 children2) {
        System.out.println("你好2");
    }
}
