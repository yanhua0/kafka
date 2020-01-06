package org.test.web;

import org.cookie.test.HeaderUtis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.entity.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CookieController {
    public static ExecutorService executorService= Executors.newFixedThreadPool(3);
    @GetMapping("/cook")
    public Object print() {
        return HeaderUtis.getCookies();
    }

    @GetMapping("/header")
    public String getHeader1() {
        return HeaderUtis.getHeader();

    }

    @PostMapping("/post")
    public String post(Users users) {
      return users.toString();
    }

 /*   public static void main(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:8000/valid/post");
         //   URLConnection rulConnection   = url.openConnection();
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5*1000);
            httpURLConnection.setReadTimeout(5*1000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();

            System.out.println(httpURLConnection.getResponseCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    public static void main(String[] args){

      String ss="123";
      String s= ss;
      System.out.println(ss==s);
    }

}
