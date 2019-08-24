package org.webSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
public class WebSocketApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
