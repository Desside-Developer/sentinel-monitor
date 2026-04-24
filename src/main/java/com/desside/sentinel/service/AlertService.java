package com.desside.sentinel.service;

import com.desside.sentinel.domain.Alert;
import com.desside.sentinel.dto.AlertEvent;
import com.desside.sentinel.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;

    public void save(AlertEvent event) {
        var alert = new Alert();
        alert.setServiceId(event.serviceId());
        alert.setServiceUrl(event.serviceUrl());
        alert.setStatus(event.status());
        alert.setTriggeredAt(event.triggeredAt());
        alertRepository.save(alert);
    }

    public List<Alert> getRecent() {
        return alertRepository.findTop50ByOrderByTriggeredAtDesc();
    }
}