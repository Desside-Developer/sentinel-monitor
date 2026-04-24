package com.desside.sentinel.messaging;

import com.desside.sentinel.dto.AlertEvent;
import com.desside.sentinel.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlertConsumer {
    private final AlertService alertService;

    @KafkaListener(topics = "service-alerts", groupId = "sentinel-group")
    public void consume(AlertEvent event) {
        log.info("Alert received: {} -> {}", event.serviceUrl(), event.status());
        alertService.save(event);
    }
}