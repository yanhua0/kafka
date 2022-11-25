package org.test.dynamic.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.test.dynamic.mapper.TestMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class DynamicService {
    @Resource
    private TestMapper testMapper;

    @PostConstruct
    public void init() {

        String tableName = "test";
        List<Map<String, Object>> list = new ArrayList<>();
        int k=30;
        for (int i = 15; i < 17; i++) {
            Map<String, Object> linkedHashMap = new LinkedHashMap<>();

            linkedHashMap.put("device_name",String.valueOf(i));

            list.add(linkedHashMap);
            k++;
        }

        testMapper.insertListMapMysql(tableName, list);
//
//
//        dynamicMapper.updateListMap(tableName,list,"DEPT_ID");

//        List<Integer> ids=new ArrayList<>();
//        ids.add(30);
//        ids.add(31);
//        Map<String, Object> linkedHashMap = new LinkedHashMap<>();

//        testMapper.deleteByIds(tableName, ids,"DEPT_ID",linkedHashMap);

    }

    public static String s = "linkedHashMap.put(\"%s\",);";

    @SneakyThrows
    public static void main(String[] args) {
        String inputPath = "D:\\JavaProject\\kafka\\1.txt";
        String outputPath = "D:\\JavaProject\\kafka\\linkedHashMap.txt";
        FileInputStream fileInputStream = new FileInputStream(inputPath);
        String text = inputStream2String(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
        fileOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();

    }

    @SneakyThrows
    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(String.format(s, line) + "\n");
        }
        return buffer.toString();
    }
}


