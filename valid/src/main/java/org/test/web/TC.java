package org.test.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TC {
    @GetMapping("/in")
    public void print(){
        System.out.println("6666");
    }
}