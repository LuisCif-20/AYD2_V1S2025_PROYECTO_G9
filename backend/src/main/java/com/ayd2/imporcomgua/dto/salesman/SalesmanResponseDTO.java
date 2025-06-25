package com.ayd2.imporcomgua.dto.salesman;

import java.util.UUID;

public record SalesmanResponseDTO (
    UUID code,
    String firstName,
    String lastName,
    String phone,
    String address,
    Double commissionPercent,
    Boolean isActive
){}
