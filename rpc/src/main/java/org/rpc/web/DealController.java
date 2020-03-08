package org.rpc.web;

import org.rpc.service.DealFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DealController {
    @Resource
    private DealFactory dealFactory;
    @GetMapping("/ge")
    public void ss(){
        dealFactory.getDealServiceMap("1").print();
    }
}
