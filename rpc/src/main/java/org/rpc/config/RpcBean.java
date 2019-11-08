package org.rpc.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "rpc")
@ToString
@Getter
@Setter
public class RpcBean {
   private Map<String,String> service;

  public void tt(){

  }
}
