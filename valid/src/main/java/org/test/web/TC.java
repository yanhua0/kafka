package org.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TC {
    @GetMapping("/in")
    public void print(){
        System.out.println("0000");
    }
}
