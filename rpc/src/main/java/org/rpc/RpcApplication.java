package org.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableAutoConfiguration

//@EnableAsync
public class RpcApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class, args);
    }
}

