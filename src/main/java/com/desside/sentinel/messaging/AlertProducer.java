package com.desside.sentinel.messaging;

import com.desside.sentinel.dto.AlertEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlertProducer {
    private final KafkaTemplate<String, AlertEvent> kafkaTemplate;

    public void send(AlertEvent event) {
        kafkaTemplate.send("service-alerts", event.serviceId(), event);
        log.info("Alert sent for: {}", event.serviceUrl());
    }
}