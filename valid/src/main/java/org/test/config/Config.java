package org.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.entity.Users;

@Configuration
public class Config {
    @Bean
    public Users t() {
        Users u = new Users();
        u.setPassword("1231234");
        return u;
    }
}
