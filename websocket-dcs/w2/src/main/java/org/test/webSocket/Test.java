package org.test.webSocket;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Test {
    private String ms;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<byte[]> responseEntityxx=restTemplate.exchange("http://localhost/xx.jpg", HttpMethod.GET, null, new ParameterizedTypeReference<byte[]>() {
//        });
        ResponseEntity<String> responseEntity2=restTemplate.exchange("http://localhost/1.jpg", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        URL url=new URL("http://localhost/xx.jpeg");
        Image image = ImageIO.read(url);
        System.out.println(image);
      //  System.out.println(new String(responseEntityxx.getBody()));
        System.out.println(responseEntity2.getBody());
    }
}
