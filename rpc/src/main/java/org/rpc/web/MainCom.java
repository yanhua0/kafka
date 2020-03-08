package org.rpc.web;

import org.rpc.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class MainCom {
    @Resource
    private User user;

    @GetMapping("/r")
    public User r(User user) {
        System.out.println(user);
        return user;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        User u = new User();
        u.setUsername("666");
        ResponseEntity<User> user = restTemplate.getForEntity("http://localhost/r", User.class, u);
        System.out.println(user.getBody());
    }
}
