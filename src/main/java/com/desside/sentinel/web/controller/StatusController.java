package com.desside.sentinel.web.controller;

import com.desside.sentinel.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/status") @RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/{serviceId}")
    public Map<String, String> getStatus(@PathVariable String serviceId) {
        return Map.of("serviceId", serviceId, "status", statusService.getStatus(serviceId));
    }
}