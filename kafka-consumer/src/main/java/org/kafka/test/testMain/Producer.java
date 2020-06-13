package org.kafka.test.testMain;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.*;
import java.util.Properties;

public class Producer {
    public static String topic = "test";//定义主题
 public static byte[] b2;
    public static void main(String[] args) throws InterruptedException, IOException {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        File file=new File("C:\\Users\\Administrator\\Desktop\\下载.jpg");



            byte[] xml= File2byte(file);
        byte[] b= new byte[]{0,1,2,3,4};
        User user=new User();
        user.setName("2在");
        user.setAge("123");
        String s="ZGHZ";
      String json= JSONObject.toJSONString(user);
   //   byte[] s= ArrayUtils.addAll(b,json.getBytes());
//      b2=s;
        try {
            while (true) {


                ProducerRecord<String,  String> record = new ProducerRecord<>(topic,s+new String(b)+json);
                kafkaProducer.send(record);
                System.out.println("消息发送成功:" + xml);
                Thread.sleep(1000);
            }
        } finally {
            kafkaProducer.close();
        }

    }
    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

}
