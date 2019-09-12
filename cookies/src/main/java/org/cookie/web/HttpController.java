package org.cookie.web;

import org.cookie.test.HeaderUtis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class HttpController {
    @GetMapping("/header")
    public Object getHeader1(){
        return HeaderUtis.getHeader();

    }
    @GetMapping("/cook")
    public Object getHeader2(HttpSession session2){
        System.out.println(session2.getId());
        HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie cookie=new Cookie("User","cookie");
        resp.addCookie(cookie);
        return HeaderUtis.getCookies();
    }
}
