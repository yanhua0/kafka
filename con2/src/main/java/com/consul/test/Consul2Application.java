package com.consul.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableDiscoveryClient
public class Consul2Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Consul2Application.class, args);
    }
}

