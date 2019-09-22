package org.cookie.fx;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> s = new ArrayList<>();
        User user = new User();
        user.setPassword("123123");
        user.setUsername1("124");
        s.add(user);
        User1<String> user1 = new User1<String>();
        BeanUtils.copyProperties(user, user1);
        String user2 = user1.getUsername1();
        System.out.println(user2);
    }
}
