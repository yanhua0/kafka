package org.kafka.test.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Message {
    private Map<String,String> data;
}
