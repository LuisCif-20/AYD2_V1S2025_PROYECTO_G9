package com.ayd2.imporcomgua.dto.user;

import java.util.UUID;

import jakarta.validation.constraints.Size;

public record UpdateUserRequestDTO(

    @Size(max = 30, message = "El nombre de debe ser menor a 31 caracteres")
    String firstName,

    @Size(max = 30, message = "El apellido de debe ser menor a 31 caracteres")
    String lastName,

    @Size(max = 255, message = "El email de debe ser menor a 256 caracteres")
    String email,

    @Size(max = 25, message = "La contraseña de debe ser menor a 26 caracteres")
    String password,

    Boolean isActive,

    UUID roleId

) { }
