package org.cookie.copy;

public class Stu1 {
    private String s1;
    private String s2;
    private String ss;

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "Stu1{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}
