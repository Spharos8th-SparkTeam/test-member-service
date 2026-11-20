package com.example.spring.test.kafka.controller;

import com.example.spring.test.kafka.dto.ProductCreateKafkaDto;
import com.example.spring.test.kafka.producer.ProductCreateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
@RequestMapping("/api/test/kafka")
@RequiredArgsConstructor
public class KafkaTestController {

    private final ProductCreateProducer productCreateProducer;

    @PostMapping
    public ResponseEntity<String> publish(@RequestBody ProductCreateKafkaDto event) {
        productCreateProducer.send(event);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("product_created 이벤트 발행을 요청했습니다.");
    }
}
