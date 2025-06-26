package com.ayd2.imporcomgua.dto.user;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserRequestDTO(

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre de debe ser menor a 31 caracteres")
    String firstName,

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El apellido de debe ser menor a 31 caracteres")
    String lastName,

    @NotBlank(message = "El email es obligatorio")
    @Size(max = 255, message = "El email de debe ser menor a 256 caracteres")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 25, message = "La contraseña de debe ser menor a 26 caracteres")
    String password,

    @NotNull(message = "El rol es obligatorio")
    UUID roleId

) { }
