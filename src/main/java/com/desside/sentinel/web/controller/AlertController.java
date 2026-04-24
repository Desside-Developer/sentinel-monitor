package com.desside.sentinel.web.controller;

import com.desside.sentinel.domain.Alert;
import com.desside.sentinel.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts") @RequiredArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @GetMapping
    public List<Alert> getRecent() { return alertService.getRecent(); }
}