package org.test.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.entity.Users;
import org.test.service.UsersService;

import javax.validation.constraints.NotEmpty;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Validated//表示对入参校验
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private Users users;
    private ExecutorService executorService=Executors.newFixedThreadPool(2);

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
    public String test2(@ModelAttribute Users users) {
        //@ModelAttribute 告诉spring这个是模型数据,自动model.addAttribute
        //users.setPassword("123456");
        System.out.println(users);
        return "index";
    }
    @GetMapping("/s5")
    @ResponseBody
    public String test35(String s10) {
        /**
         * 主线程都是异步的,
         * executorService.execute的线程数量有程序定义。
         * 只有固定的线程数量，如果其他线程都没有执行完，那么必须等待其他线程执行完毕！！！！
         */
        System.out.println(Thread.currentThread().getName());
        executorService.execute(()-> {
            if(s10.equals("1")||s10.equals("2")){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
        });
       return s10;
    }
    /**
     * 会在该类其他url执行前执行一次
     */
//    @ModelAttribute("users")
//    public Users print(Users users) {
//       // users.setPassword("邹");//这里赋值会被下个url方法的请求数据所获取到
//        return users;
//    }
}
