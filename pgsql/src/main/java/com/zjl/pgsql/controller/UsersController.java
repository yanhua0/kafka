package com.zjl.pgsql.controller;

import com.zjl.pgsql.dto.UsersReqDTO;
import com.zjl.pgsql.dto.UsersResDTO;
import com.zjl.pgsql.entity.Users;
import com.zjl.pgsql.mapper.UsersMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UsersController {
    @Resource
    private UsersMapper usersMapper;
    @PostMapping("/group")
    public List<UsersResDTO> find(@RequestBody  UsersReqDTO usersReqDTO){
        return usersMapper.groupByTime(usersReqDTO);
    }
    @GetMapping("/all")
    public List<Users> find2(){
        return usersMapper.findAll();
    }
}
