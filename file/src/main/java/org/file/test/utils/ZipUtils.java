//package org.file.test.utils;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//import java.nio.channels.WritableByteChannel;
//import java.util.zip.ZipOutputStream;
//
//import static org.file.test.MappedByteBufferMain.OUT_PATH;
//
//public class ZipUtils {
//    public static void main(String[] args) throws IOException {
//        String httpUrl = "";
//        RestTemplate restTemplate = new RestTemplate();
//        FileOutputStream fileOutputStream=new FileOutputStream(OUT_PATH);
//        ZipOutputStream zipOutputStream=new ZipOutputStream(fileOutputStream);
//        WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream));
//        for (int i = 0; i < 10; i++) {
//            ResponseEntity<Resource> responseEntity = restTemplate.exchange(httpUrl, HttpMethod.GET, null,
//                    new ParameterizedTypeReference<Resource>() {
//                    });
//            InputStream inputStream = responseEntity.getBody().getInputStream();
//
//            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
//            writableByteChannel.write(readableByteChannel.)
//
//        }
//    }
//}
