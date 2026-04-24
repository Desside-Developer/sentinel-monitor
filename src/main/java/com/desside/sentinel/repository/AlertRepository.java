package com.desside.sentinel.repository;

import com.desside.sentinel.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, String> {
    List<Alert> findTop50ByOrderByTriggeredAtDesc();
}