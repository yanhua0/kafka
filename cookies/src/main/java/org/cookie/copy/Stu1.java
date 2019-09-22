package org.cookie.copy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stu1 {
    private String s1;
    private String s2;
    private String ss;


    @Override
    public String toString() {
        return "Stu1{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Stu1 stu1 = new Stu1();
        stu1.setS1("123");
        System.out.println(stu1.getS1());
    }
}
