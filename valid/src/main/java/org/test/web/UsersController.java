package org.test.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String test2(@ModelAttribute Users users, Model model) {
        //@ModelAttribute 告诉spring这个是模型数据,自动model.addAttribute
        users.setPassword("123456");
        System.out.println(users);
        return "index";
    }

    /**
     * 会在该类其他url执行前执行一次
     */
    @ModelAttribute("users")
    public Users print(Users users) {
        users.setPassword("邹");//这里赋值会被下个url方法的请求数据所获取到
        return users;
    }
}
