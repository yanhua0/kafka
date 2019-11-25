package org.visitor.entity;


public interface AccountBookViewer {

    //查看消费的单子
    void view(ConsumeBill bill);

    //查看收入的单子
    void view(IncomeBill bill);

}