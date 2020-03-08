package org.rpc.web;

import org.rpc.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainCom {
    @Resource
    private User user;

    @GetMapping("/r")
    public void r() {
        user.setUsername("9");
        System.out.println(user);

    }
}
