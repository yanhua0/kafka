package org.test.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.entity.Users;

@RestController
public class TC {
    @GetMapping("/in")
    public Users print(){
        return new Users();
    }
}