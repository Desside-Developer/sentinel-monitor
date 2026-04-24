package com.desside.sentinel.domain;

import com.desside.sentinel.service.ServiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
public class Alert {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String serviceId;

    @Column(nullable = false)
    private String serviceUrl;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @Column(nullable = false)
    private LocalDateTime triggeredAt;
}