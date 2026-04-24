package com.desside.sentinel.dto;

import com.desside.sentinel.service.ServiceStatus;

import java.time.LocalDateTime;

public record AlertEvent(
        String serviceId,
        String serviceUrl,
        ServiceStatus status,
        LocalDateTime triggeredAt
) {}