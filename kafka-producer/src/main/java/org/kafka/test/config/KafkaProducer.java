package org.kafka.test.config;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Wayne
 * @date 2018/5/25
 * <p>
 * desc: kafka消息生产端
 */
@Component
public class KafkaProducer  {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendMessage(String topic, String message) {
        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        // you can register a callback with the listener to receive the result
        // success回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                ProducerRecord<String, String> producerRecord = result.getProducerRecord();
                String key = producerRecord.key();
                String value = producerRecord.value();
                RecordMetadata recordMetadata = result.getRecordMetadata();
                String topic = recordMetadata.topic();
                logger.info("send kafka message='{}' with offset={}", message, result.getRecordMetadata().offset());
                logger.info("key = '{}'" , key);
                logger.info("value = '{}'",value);
                logger.info("topic = '{}'",topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.debug("unable to send kafka message='{}'", message, ex);
            }
        });
    }
}