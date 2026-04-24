package com.desside.sentinel.web.controller;

import com.desside.sentinel.dto.CreateServiceDto;
import com.desside.sentinel.dto.ServiceResponseDto;
import com.desside.sentinel.service.ServiceConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services") @RequiredArgsConstructor
public class ServiceController {
    private final ServiceConfigService service;

    @GetMapping
    public List<ServiceResponseDto> getAll() { return service.findAll(); }

    @PostMapping
    public ServiceResponseDto create(@Valid @RequestBody CreateServiceDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}