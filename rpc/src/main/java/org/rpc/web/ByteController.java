package org.rpc.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;

@RestController
public class ByteController {
    @PostMapping("/post")
    public void ss(@RequestBody byte[] bytes) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\下载.jpg");
        byte[] xml = File2byte(file);
        File F = new File("C:\\Users\\Administrator\\Desktop\\" + UUID.randomUUID().toString() + ".jpg");
        FileOutputStream outputStream = new FileOutputStream(F);
        outputStream.write(bytes);
        outputStream.close();
        System.out.println(Arrays.equals(xml,bytes));
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        File file = new File("C:\\Users\\Administrator\\Desktop\\下载.jpg");
        byte[] xml = File2byte(file);
        restTemplate.exchange("http://localhost/post", HttpMethod.POST, new HttpEntity<>(xml), String.class);
    }

    public static byte[] File2byte(File tradeFile) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
