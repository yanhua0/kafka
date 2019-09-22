package org.test.web;

import org.cookie.test.HeaderUtis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {
    @GetMapping("/cook")
    public Object print() {
        return HeaderUtis.getCookies();
    }
    @GetMapping("/header")
    public String getHeader1(){
        return HeaderUtis.getHeader();

    }
}
