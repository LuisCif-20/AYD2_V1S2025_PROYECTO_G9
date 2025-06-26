package com.ayd2.imporcomgua.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewBusinessRequestDTO(

    @NotBlank(message = "El nombre del negocio es obligatorio")
    @Size(max = 100, message = "El nombre del negocio debe ser meno a 101 caracteres")
    String name

) {}
