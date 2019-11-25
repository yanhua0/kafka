package org.visitor.entity;

public class ConsumeBill implements Bill {
    private double amount;

    private String item;

    public ConsumeBill(double amount, String item) {
        super();
        this.amount = amount;
        this.item = item;
    }

    public void accept(AccountBookViewer viewer) {
        viewer.view(this);//不同实体调用不同实体的view方法
    }

    public double getAmount() {
        return amount;
    }

    public String getItem() {
        return item;
    }

}
