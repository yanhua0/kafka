package org.cookie.copy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 泛型方法使用
 */
public class Copy {
    public static <T> Object test(T t) throws IllegalAccessException, InstantiationException {
        Class clazz=t.getClass();
        System.out.println(clazz.getName());
        Field[] f=clazz.getDeclaredFields();
        for(Field ff: f) {
            System.out.println(ff.getName());
            if (ff.getName().equals("ss")) {
                ff.setAccessible(true);
                ff.set(clazz.newInstance(), "test");

            }
        }


        return clazz;
    }
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
    Stu1 s=new Stu1();
    s.setS1("123");
        Stu2 s2=new Stu2();
        s2.setS3("1234");
        Class clazz=Copy.class;
Copy copy= (Copy) clazz.newInstance();
        Stu2 stu2= (Stu2) Copy.test(s2);
    System.out.println(stu2);
    }
}
