package com.example.spring.test.kafka.producer;

import com.example.spring.test.kafka.dto.ProductCreateKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCreateProducer {

    static final String TOPIC = "product_created";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(ProductCreateKafkaDto event) {
        kafkaTemplate.send(TOPIC, event).whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Kafka 이벤트 발행 실패. topic={}, payload={}", TOPIC, event, exception);
                return;
            }
            log.info(
                    "Kafka 이벤트 발행 성공. topic={}, partition={}, offset={}, payload={}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset(),
                    event
            );
        });
    }
}
