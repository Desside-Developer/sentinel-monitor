package com.desside.sentinel.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CreateServiceDto(
        @NotBlank String name,
        @NotBlank @URL String url
) {}
