package org.kafka.test.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {

    @GetMapping("/test1")
    public Object con(String message) {
        return message;
    }
}
