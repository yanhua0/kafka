package com.zjl.pgsql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zjl.pgsql.dto.UsersReqDTO;
import com.zjl.pgsql.dto.UsersResDTO;
import com.zjl.pgsql.entity.Test;
import com.zjl.pgsql.entity.Users;
import com.zjl.pgsql.mapper.TestMapper;
import com.zjl.pgsql.mapper.UsersMapper;
import com.zjl.pgsql.repository.TestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class UsersController {
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private TestMapper testMapper;
    @Resource
    private TestRepository testRepository;

    @PostMapping("/group")
    public List<UsersResDTO> find(@RequestBody UsersReqDTO usersReqDTO) {
        return usersMapper.groupByTime(usersReqDTO);
    }

    @GetMapping("/all")
    public List<Users> find2() {
        return usersMapper.findAll();
    }

    @GetMapping("/test")
    public void ss(int i) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        for (int j = 0; j < i; j++) {
            executorService.execute(() -> {
                List<Test> testList = new ArrayList<>();
                Test test = new Test();
                testList.add(test);
                String s = UUID.randomUUID().toString();
                test.setUuid(s);
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectNode jsonNode = objectMapper.createObjectNode();
                jsonNode.put("name", s);
                test.setObj(jsonNode);
                testMapper.insertList(testList);
                //testRepository.saveAll(testList);

            });
        }
    }
}
