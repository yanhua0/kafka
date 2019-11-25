package org.visitor.service;

public class HelloChildren2 implements Say {
    @Override
    public void hello(Children1 children1) {
        System.out.println("你好HelloChildren2");
    }

    @Override
    public void hello(Children2 children2) {
        System.out.println("你好HelloChildren2");
    }
}
