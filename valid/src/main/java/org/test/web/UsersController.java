package org.test.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.entity.Users;
import org.test.service.UsersService;

import javax.validation.constraints.NotEmpty;

@Controller
@Validated//表示对入参校验
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/s2")
    public void test(@NotEmpty(message = "错误的参数") String s2) {
        System.out.println(s2);
    }

    @GetMapping("/s4")
    @ResponseBody
    public String test3(@RequestParam("s2") String s2) {
        usersService.u(s2);
        return s2;
    }

    @ApiOperation("测试users")
    @GetMapping("/test")
    @ResponseBody
    public void test2(@ModelAttribute Users users) {
        //@ModelAttribute 告诉spring这个是模型数据
        System.out.println(users);
    }

    /**
     * 会在该类其他url执行前执行一次
     */
    @ModelAttribute
    public void print(){
        System.out.println("11111");
    }
}
