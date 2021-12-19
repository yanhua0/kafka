package com.zjl.pgsql;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.zjl.pgsql.mapper"})
public class PgsqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(PgsqlApplication.class, args);
    }
}
