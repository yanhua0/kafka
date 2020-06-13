package org.kafka.test.testMain;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        p.put("request.timeout.ms", 30000000);

        p.put("fetch.max.wait.ms", "3000000");
        KafkaConsumer<String,byte[]> kafkaConsumer = new KafkaConsumer<>(p);
        kafkaConsumer.subscribe(Collections.singletonList(Producer.topic));// 订阅消息
        byte[] b1= new byte[]{0,1,2,3,4};
        User user=new User();
        user.setName("2在");
        user.setAge("123");
        String json= JSONObject.toJSONString(user);
        byte[] s= ArrayUtils.addAll(b1,json.getBytes());
        while (true) {
            ConsumerRecords<String, byte[]> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String,  byte[]> record : records) {
          byte[] b=record.value();
                System.out.println(Arrays.equals(b,s));
          String bbb=new String(b, StandardCharsets.ISO_8859_1);
                byte[] stb=bbb.getBytes(StandardCharsets.ISO_8859_1);

                File F=new File("C:\\Users\\Administrator\\Desktop\\"+ UUID.randomUUID().toString()+".jpg");
                FileOutputStream outputStream=new FileOutputStream(F);
                outputStream.write(b);
                outputStream.close();
            }
Thread.sleep(10000);

        }
    }
}
