package org.test.dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class DynamicMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicMapperApplication.class, args);
    }

}
