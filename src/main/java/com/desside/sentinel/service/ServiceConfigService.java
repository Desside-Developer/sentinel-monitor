package com.desside.sentinel.service;

import com.desside.sentinel.domain.ServiceConfig;
import com.desside.sentinel.dto.CreateServiceDto;
import com.desside.sentinel.dto.ServiceResponseDto;
import com.desside.sentinel.repository.ServiceConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceConfigService {
    private final ServiceConfigRepository repo;
    private final StatusService StatusService;

    public ServiceResponseDto create(CreateServiceDto dto) {
        var entity = new ServiceConfig();
        //entity.setName(dto.name());
        //entity.setUrl(dto.url());
        repo.save(entity);
        return toDto(entity);
    }

    public List<ServiceResponseDto> findAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public List<ServiceConfig> findAllActive() {
        return repo.findAllByActiveTrue();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private ServiceResponseDto toDto(ServiceConfig e) {
        String status = StatusService.getStatus(e.getId());
        return new ServiceResponseDto(e.getId(), e.getName(), e.getUrl(), e.isActive(), status);
    }
}