package org.test.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Service
@Validated
public class UsersService {
    public void u(@NotBlank(message = "错的") String s2){
        System.out.println("s2");
    }
}
