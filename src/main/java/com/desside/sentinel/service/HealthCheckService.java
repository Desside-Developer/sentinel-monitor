package com.desside.sentinel.service;

import com.desside.sentinel.domain.ServiceConfig;
import com.desside.sentinel.dto.AlertEvent;
import com.desside.sentinel.messaging.AlertProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthCheckService {
    private final ServiceConfigService serviceConfigService;
    private final AlertProducer alertProducer;
    private final StatusService statusService;

    @Scheduled(fixedDelay = 30_000)
    public void checkAll() {
        var services = serviceConfigService.findAllActive();
        log.info("Checking {} services...", services.size());

        // jdk21-virtual-threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            services.forEach(s -> executor.submit(() -> checkOne(s)));
        }
    }

    private void checkOne(ServiceConfig service) {
        try {
            var client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(service.getUrl()))
                    .timeout(Duration.ofSeconds(5))
                    .GET().build();

            var response = client.send(request, HttpResponse.BodyHandlers.discarding());
            var status = response.statusCode() < 500 ? ServiceStatus.UP : ServiceStatus.DOWN;

            statusService.setStatus(service.getId(), status);

            if (status == ServiceStatus.DOWN) {
                alertProducer.send(new AlertEvent(
                        service.getId(), service.getUrl(), status, LocalDateTime.now()
                ));
            }
        } catch (Exception e) {
            log.warn("Service {} is DOWN: {}", service.getUrl(), e.getMessage());
            statusService.setStatus(service.getId(), ServiceStatus.DOWN);
            alertProducer.send(new AlertEvent(
                    service.getId(), service.getUrl(), ServiceStatus.DOWN, LocalDateTime.now()
            ));
        }
    }
}