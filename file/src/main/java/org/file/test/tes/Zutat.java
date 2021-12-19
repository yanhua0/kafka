package org.file.test.tes;

/**
 * @author zoujulin [zou.julin@unisinsight.com]
 * @date 2021/01/14 19:50
 * @since 1.0
 */

public class Zutat {
    private String name;
    private double quantity;
    private String unit;

    public Zutat(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        String s = "";
        if (unit != null && unit.length() > 0) {
            s = String.format("%.1f %s %s", quantity, unit, name);
        } else {
            s = String.format("%.1f  %s", quantity, name);
        }
        return s;
    }
}
