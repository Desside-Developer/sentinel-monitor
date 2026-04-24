package com.desside.sentinel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String PREFIX = "status:";

    public void setStatus(String serviceId, ServiceStatus status) {
        redisTemplate.opsForValue().set(PREFIX + serviceId, status.name());
        return;
    }

    public String getStatus(String serviceId) {
        String val = redisTemplate.opsForValue().get(PREFIX + serviceId);
        return val != null ? val : ServiceStatus.UNKNOWN.name();
    }
}