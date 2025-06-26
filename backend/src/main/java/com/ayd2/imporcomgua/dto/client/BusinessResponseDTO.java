package com.ayd2.imporcomgua.dto.client;

import java.util.UUID;

public record BusinessResponseDTO(

    UUID id,
    String name,
    Boolean isActive

) {}
