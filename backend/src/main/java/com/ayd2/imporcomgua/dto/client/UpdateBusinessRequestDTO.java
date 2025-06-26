package com.ayd2.imporcomgua.dto.client;

import jakarta.validation.constraints.Size;

public record UpdateBusinessRequestDTO(

    @Size(max = 100, message = "El nombre del negocio debe ser meno a 101 caracteres")
    String name,

    Boolean isActive

) { }
