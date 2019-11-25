package org.visitor.entity;


public interface Bill {

    void accept(AccountBookViewer viewer);

}
