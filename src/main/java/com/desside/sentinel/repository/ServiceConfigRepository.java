package com.desside.sentinel.repository;

import com.desside.sentinel.domain.ServiceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceConfigRepository extends JpaRepository<ServiceConfig, String> {
    List<ServiceConfig> findAllByActiveTrue();
}