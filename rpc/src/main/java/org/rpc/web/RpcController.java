package org.rpc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RpcController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rpc")
    public Object r() {
       // restTemplate.setErrorHandler(new CustomErrorHandler());
        try {
            ParameterizedTypeReference responseType = new ParameterizedTypeReference<String>() {
            };
            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/valid/s33?s2=123", HttpMethod.GET, null, responseType);
            return response.getBody();

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return null;
    }
}
