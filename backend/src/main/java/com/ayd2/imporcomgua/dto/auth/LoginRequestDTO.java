package com.ayd2.imporcomgua.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

    @NotBlank(message = "El email es obligatorio")
    @Size(max = 255, message = "El email de debe ser menor a 256 caracteres")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 25, message = "La contraseña de debe ser menor a 26 caracteres")
    String password

) { }
