package com.ayd2.imporcomgua.services.products;

import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.mappers.product.PresentationMapper;
import com.ayd2.imporcomgua.models.product.Presentation;
import com.ayd2.imporcomgua.repositories.product.PresentationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PresentationServiceImplTest {

    private static final Long PRESENTATION_ID = 1L;
    private static final String PRESENTATION_NAME = "Caja";

    @Mock
    private PresentationRepository presentationRepository;

    @Mock
    private PresentationMapper presentationMapper;

    @InjectMocks
    private PresentationServiceImpl serviceToTest;

    @Test
    void testGetAllPresentationsWhenListIsEmpty() {
        // Arrange
        when(presentationRepository.findAll()).thenReturn(List.of());

        // Act
        List<PresentationResponseDTO> result = serviceToTest.getAllPresentations();

        // Assert
        assertTrue(result.isEmpty());
        verify(presentationRepository, times(1)).findAll();
        verify(presentationMapper, never()).toPresentationResponseDTO(any());
    }

    @Test
    void testGetAllPresentationsWhenListHasItems() {
        // Arrange
        Presentation presentation1 = new Presentation();
        presentation1.setId(PRESENTATION_ID);
        presentation1.setName(PRESENTATION_NAME);

        Presentation presentation2 = new Presentation();
        presentation2.setId(2L);
        presentation2.setName("Bolsa");

        PresentationResponseDTO dto1 = new PresentationResponseDTO(PRESENTATION_ID, PRESENTATION_NAME);
        PresentationResponseDTO dto2 = new PresentationResponseDTO(2L, "Bolsa");

        when(presentationRepository.findAll()).thenReturn(List.of(presentation1, presentation2));
        when(presentationMapper.toPresentationResponseDTO(presentation1)).thenReturn(dto1);
        when(presentationMapper.toPresentationResponseDTO(presentation2)).thenReturn(dto2);

        // Act
        List<PresentationResponseDTO> result = serviceToTest.getAllPresentations();

        // Assert
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(PRESENTATION_ID, result.get(0).id()),
                () -> assertEquals(PRESENTATION_NAME, result.get(0).name()),
                () -> assertEquals(2L, result.get(1).id()),
                () -> assertEquals("Bolsa", result.get(1).name())
        );

        verify(presentationRepository, times(1)).findAll();
        verify(presentationMapper, times(1)).toPresentationResponseDTO(presentation1);
        verify(presentationMapper, times(1)).toPresentationResponseDTO(presentation2);
    }
}