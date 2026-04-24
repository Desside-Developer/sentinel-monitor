package com.desside.sentinel.dto;

public record ServiceResponseDto(
        String id,
        String name,
        String url,
        boolean active,
        String currentStatus  // из Redis
) {}