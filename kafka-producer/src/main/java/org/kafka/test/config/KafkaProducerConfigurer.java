package org.kafka.test.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaProducerConfigurer
 *
 * @author lvpanfeng [lv.panfeng@unisinsight.com]
 * @date 2019/3/4 16:57
 * @since 1.0
 */
@Configuration
@EnableKafka
public class KafkaProducerConfigurer {

    @Value("${kafka.bootstrap-servers}")
    private String servers;
    @Value("${kafka.byte-bootstrap-servers}")
    private String byteArrayServers;
    @Value("${kafka.producer.retries}")
    private int retries;
    @Value("${kafka.producer.batch-size}")
    private int batchSize;
    @Value("${kafka.producer.buffer-memory}")
    private int bufferMemory;
    @Value("${kafka.producer.max-request-size:10485760}")
    private int maxRequestSize;
    @Value(("${kafka.connections-max-idle-ms:60000}"))
    private int idleMs;


    /**
     * 微云kafka生产者
     * @return
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory(servers));
    }


    private Map<String, Object> producerConfigs(String server) {
        return  producerConfigs(server,retries,batchSize,bufferMemory, maxRequestSize,StringSerializer.class,
                StringSerializer.class);
    }

    private ProducerFactory<String, String> producerFactory(String server) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(server));
    }

    private Map<String, Object> producerConfigs(String servers, Integer retries, Integer batchSize, Integer bufferMemory,
                                                int maxRequestSize,Class... clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, clazz[0]);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, clazz[1]);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize);
        props.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, idleMs);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "2000");
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, "2000");
        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "1000");
        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, "10000");
        return props;
    }

}
