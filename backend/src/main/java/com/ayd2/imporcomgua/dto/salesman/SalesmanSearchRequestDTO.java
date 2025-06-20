package com.ayd2.imporcomgua.dto.salesman;

import java.util.UUID;

public record SalesmanSearchRequestDTO(
    UUID code,
    String name,
    Boolean active
) {}
