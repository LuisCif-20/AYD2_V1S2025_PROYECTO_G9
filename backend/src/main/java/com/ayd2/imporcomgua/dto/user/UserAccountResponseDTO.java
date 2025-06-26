package com.ayd2.imporcomgua.dto.user;

import java.util.UUID;

public record UserAccountResponseDTO(

    UUID id,
    String firstName,
    String lastName,
    String email,
    Boolean isActive,
    RoleResponseDTO role

) { }
