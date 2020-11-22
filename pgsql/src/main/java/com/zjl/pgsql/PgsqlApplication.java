package com.zjl.pgsql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.zjl.pgsql.mapper"})
public class PgsqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(PgsqlApplication.class, args);
    }
}
