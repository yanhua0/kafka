//package org.kafka.test.config;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaConfig {
//
//
//
//    public Map<String, Object> producerConfigs() {
//
//        Map<String, Object> props = new HashMap<>();
//        // list of host:port pairs used for establishing the initial connections
//        // to the Kakfa cluster
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.254.253:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        // value to block, after which it will throw a TimeoutException
//        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);
//        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 5000);
//
////        props.put(ProducerConfig.ACKS_CONFIG, "all");
////        props.put(ProducerConfig.RETRIES_CONFIG, 1);
////        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
////        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
////        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//
//        return props;
//    }
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaOutTemplate() {
//        return new KafkaTemplate<String, String>(producerFactory());
//    }
//
//
//
//
//
//
//
//
//
//}
