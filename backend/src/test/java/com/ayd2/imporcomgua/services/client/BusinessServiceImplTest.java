package com.ayd2.imporcomgua.services.client;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewBusinessRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateBusinessRequestDTO;
import com.ayd2.imporcomgua.exceptions.*;
import com.ayd2.imporcomgua.mappers.client.BusinessMapper;
import com.ayd2.imporcomgua.models.client.Business;
import com.ayd2.imporcomgua.repositories.client.BusinessRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BusinessServiceImplTest {

    private static final String BUSINESS_NAME = "Tech Solutions";
    private static final UUID BUSINESS_ID = UUID.randomUUID();

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private BusinessMapper businessMapper;

    @InjectMocks
    private BusinessServiceImpl serviceToTest;

    @Test
    void testGetAllBusinessSuccessfully() {
        // Arrange
        Business business = new Business();
        business.setId(BUSINESS_ID);
        business.setName(BUSINESS_NAME);
        business.setIsActive(Boolean.TRUE);

        BusinessResponseDTO responseDTO = new BusinessResponseDTO(
                BUSINESS_ID, BUSINESS_NAME, Boolean.TRUE);

        when(businessRepository.findAll()).thenReturn(List.of(business));
        when(businessMapper.toBusinessResponseDTO(business)).thenReturn(responseDTO);

        // Act
        List<BusinessResponseDTO> result = serviceToTest.getAllBusiness();

        // Assert
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(BUSINESS_ID, result.get(0).id()),
                () -> assertEquals(BUSINESS_NAME, result.get(0).name()),
                () -> assertTrue(result.get(0).isActive()));

        verify(businessRepository, times(1)).findAll();
        verify(businessMapper, times(1)).toBusinessResponseDTO(business);
    }

    @Test
    void testCreateBusinessWhenNameNotDuplicated() throws Exception {
        // Arrange
        NewBusinessRequestDTO requestDTO = new NewBusinessRequestDTO(BUSINESS_NAME);
        Business business = new Business();
        business.setName(BUSINESS_NAME);

        Business savedBusiness = new Business();
        savedBusiness.setId(BUSINESS_ID);
        savedBusiness.setName(BUSINESS_NAME);
        savedBusiness.setIsActive(true);

        BusinessResponseDTO responseDTO = new BusinessResponseDTO(
                BUSINESS_ID, BUSINESS_NAME, Boolean.TRUE);

        ArgumentCaptor<Business> captor = ArgumentCaptor.forClass(Business.class);

        when(businessRepository.existsByName(BUSINESS_NAME)).thenReturn(false);
        when(businessMapper.toBusiness(requestDTO)).thenReturn(business);
        when(businessRepository.save(captor.capture())).thenReturn(savedBusiness);
        when(businessMapper.toBusinessResponseDTO(savedBusiness)).thenReturn(responseDTO);

        // Act
        BusinessResponseDTO result = serviceToTest.createBusiness(requestDTO);

        // Assert
        assertAll(
                () -> assertEquals(BUSINESS_NAME, captor.getValue().getName()),
                () -> assertEquals(BUSINESS_ID, result.id()),
                () -> assertEquals(BUSINESS_NAME, result.name()),
                () -> assertTrue(result.isActive()));

        verify(businessRepository, times(1)).existsByName(BUSINESS_NAME);
        verify(businessMapper, times(1)).toBusiness(requestDTO);
        verify(businessRepository, times(1)).save(business);
        verify(businessMapper, times(1)).toBusinessResponseDTO(savedBusiness);
    }

    @Test
    void testCreateBusinessWhenNameDuplicated() {
        // Arrange
        NewBusinessRequestDTO requestDTO = new NewBusinessRequestDTO(BUSINESS_NAME);

        when(businessRepository.existsByName(BUSINESS_NAME)).thenReturn(true);

        // Act & Assert
        DuplicatedEntityException exception = assertThrows(
                DuplicatedEntityException.class,
                () -> serviceToTest.createBusiness(requestDTO));

        assertEquals("Ya existe un negocio con el nombre: " + BUSINESS_NAME, exception.getMessage());

        verify(businessRepository, times(1)).existsByName(BUSINESS_NAME);
        verify(businessRepository, never()).save(any(Business.class));
    }

    @Test
    void testUpdateBusinessSuccessfully() throws Exception {
        // Arrange
        UpdateBusinessRequestDTO requestDTO = new UpdateBusinessRequestDTO(BUSINESS_NAME, null);
        Business existingBusiness = new Business();
        existingBusiness.setId(BUSINESS_ID);
        existingBusiness.setName("Old Name");
        existingBusiness.setIsActive(true);

        Business updatedBusiness = new Business();
        updatedBusiness.setId(BUSINESS_ID);
        updatedBusiness.setName(BUSINESS_NAME);
        updatedBusiness.setIsActive(true);

        BusinessResponseDTO responseDTO = new BusinessResponseDTO(
                BUSINESS_ID, BUSINESS_NAME, Boolean.TRUE);

        when(businessRepository.findById(BUSINESS_ID)).thenReturn(Optional.of(existingBusiness));
        when(businessRepository.existsByNameAndIdNot(BUSINESS_NAME, BUSINESS_ID)).thenReturn(false);
        when(businessRepository.save(existingBusiness)).thenReturn(updatedBusiness);
        when(businessMapper.toBusinessResponseDTO(updatedBusiness)).thenReturn(responseDTO);

        // Act
        BusinessResponseDTO result = serviceToTest.updateBusiness(BUSINESS_ID, requestDTO);

        // Assert
        assertAll(
                () -> assertEquals(BUSINESS_ID, result.id()),
                () -> assertEquals(BUSINESS_NAME, result.name()),
                () -> assertTrue(result.isActive()));

        verify(businessRepository, times(1)).findById(BUSINESS_ID);
        verify(businessRepository, times(1)).existsByNameAndIdNot(BUSINESS_NAME, BUSINESS_ID);
        verify(businessMapper, times(1)).UpdateBusinessFromDTO(requestDTO, existingBusiness);
        verify(businessRepository, times(1)).save(existingBusiness);
        verify(businessMapper, times(1)).toBusinessResponseDTO(updatedBusiness);
    }

    @Test
    void testUpdateBusinessWhenBusinessNotFound() {
        // Arrange
        UpdateBusinessRequestDTO requestDTO = new UpdateBusinessRequestDTO(BUSINESS_NAME, true);

        when(businessRepository.findById(BUSINESS_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.updateBusiness(BUSINESS_ID, requestDTO));

        assertEquals("No existe el negocio con id: " + BUSINESS_ID, exception.getMessage());

        verify(businessRepository, times(1)).findById(BUSINESS_ID);
    }

    @Test
    void testUpdateBusinessWhenNameDuplicated() {
        // Arrange
        UpdateBusinessRequestDTO requestDTO = new UpdateBusinessRequestDTO(BUSINESS_NAME, true);
        Business existingBusiness = new Business();
        existingBusiness.setId(BUSINESS_ID);

        // Mock en el orden correcto según la implementación del servicio
        when(businessRepository.existsByNameAndIdNot(BUSINESS_NAME, BUSINESS_ID)).thenReturn(true);

        // Nota: El findById nunca se alcanzará porque primero se lanza la excepción por
        // nombre duplicado
        // Por eso no necesitamos mockearlo en este caso

        // Act & Assert
        DuplicatedEntityException exception = assertThrows(
                DuplicatedEntityException.class,
                () -> serviceToTest.updateBusiness(BUSINESS_ID, requestDTO));

        assertEquals("Ya existe un negocio con el nombre: " + BUSINESS_NAME, exception.getMessage());

        // Verificaciones ajustadas al flujo real
        verify(businessRepository, times(1)).existsByNameAndIdNot(BUSINESS_NAME, BUSINESS_ID);
        verify(businessRepository, never()).findById(any()); // Nunca debería llegar aquí
        verify(businessRepository, never()).save(any(Business.class));
    }

    @Test
    void testDeleteBusinessSuccessfully() throws Exception {
        // Arrange
        Business business = new Business();
        business.setId(BUSINESS_ID);
        business.setIsActive(true);

        when(businessRepository.findById(BUSINESS_ID)).thenReturn(Optional.of(business));

        // Act
        serviceToTest.deleteBusiness(BUSINESS_ID);

        // Assert
        verify(businessRepository, times(1)).findById(BUSINESS_ID);
        ArgumentCaptor<Business> captor = ArgumentCaptor.forClass(Business.class);
        verify(businessRepository, times(1)).save(captor.capture());
        assertFalse(captor.getValue().getIsActive());
    }

    @Test
    void testDeleteBusinessWhenNotFound() {
        // Arrange
        when(businessRepository.findById(BUSINESS_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.deleteBusiness(BUSINESS_ID));

        assertEquals("No existe el negocio con id: " + BUSINESS_ID, exception.getMessage());

        verify(businessRepository, times(1)).findById(BUSINESS_ID);
        verify(businessRepository, never()).save(any(Business.class));
    }
}