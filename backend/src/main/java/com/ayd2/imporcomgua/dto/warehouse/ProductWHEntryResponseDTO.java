package com.ayd2.imporcomgua.dto.warehouse;

import java.time.LocalDate;

public record ProductWHEntryResponseDTO(
    String productName,
    Integer quantityPresentation,
    Integer unitsPerPresentation,
    String containerNumber,
    String ducaNumber,
    LocalDate ducaDate,
    String rectifiedDucaNumber,
    LocalDate rectifiedDucaDate,
    String notes
) { }
