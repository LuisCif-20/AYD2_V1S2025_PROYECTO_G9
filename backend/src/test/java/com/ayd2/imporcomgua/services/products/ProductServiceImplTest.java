package com.ayd2.imporcomgua.services.products;

import com.ayd2.imporcomgua.dto.products.*;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.product.ProductMapper;
import com.ayd2.imporcomgua.models.product.Presentation;
import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.product.PresentationRepository;
import com.ayd2.imporcomgua.repositories.product.ProductRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    private static final String PRODUCT_CODE = "ABCD1234";
    private static final String PRODUCT_NAME = "Producto de prueba";
    private static final Long PRESENTATION_ID = 1L;
    private static final Integer UNITS_PER_PRESENTATION = 10;
    private static final Double PRICE_PER_PRESENTATION = 100.50;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private PresentationRepository presentationRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private ProductServiceImpl serviceToTest;

    @Test
    void testCreateProductSuccessfully() throws Exception {
        // Arrange
        NewProductRequestDTO requestDTO = new NewProductRequestDTO(
                PRODUCT_CODE, PRODUCT_NAME, PRESENTATION_ID, 
                UNITS_PER_PRESENTATION, PRICE_PER_PRESENTATION);
        
        Presentation presentation = new Presentation();
        presentation.setId(PRESENTATION_ID);
        
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        product.setName(PRODUCT_NAME);
        product.setPresentation(presentation);
        product.setUnitsPerPresentation(UNITS_PER_PRESENTATION);
        product.setPricePerPresentation(PRICE_PER_PRESENTATION);
        
        Product savedProduct = new Product();
        savedProduct.setCode(PRODUCT_CODE);
        savedProduct.setIsActive(true);
        
        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        
        ProductResponseDTO responseDTO = new ProductResponseDTO(
                PRODUCT_CODE, PRODUCT_NAME, null, 
                UNITS_PER_PRESENTATION, PRICE_PER_PRESENTATION, true);

        when(productRepository.existsById(PRODUCT_CODE)).thenReturn(false);
        when(presentationRepository.findById(PRESENTATION_ID)).thenReturn(Optional.of(presentation));
        when(productMapper.toProduct(requestDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        when(productMapper.toProductResponseDTO(savedProduct)).thenReturn(responseDTO);

        // Act
        ProductResponseDTO result = serviceToTest.createProduct(requestDTO);

        // Assert
        assertAll(
                () -> assertEquals(PRODUCT_CODE, result.code()),
                () -> assertEquals(PRODUCT_NAME, result.name()),
                () -> assertEquals(UNITS_PER_PRESENTATION, result.unitsPerPresentation()),
                () -> assertEquals(PRICE_PER_PRESENTATION, result.pricePerPresentation()),
                () -> assertTrue(result.isActive())
        );

        verify(productRepository, times(1)).existsById(PRODUCT_CODE);
        verify(presentationRepository, times(1)).findById(PRESENTATION_ID);
        verify(productMapper, times(1)).toProduct(requestDTO);
        verify(productRepository, times(1)).save(product);
        
        ArgumentCaptor<Inventory> inventoryCaptor = ArgumentCaptor.forClass(Inventory.class);
        verify(inventoryRepository, times(1)).save(inventoryCaptor.capture());
        assertEquals(savedProduct, inventoryCaptor.getValue().getProduct());
        assertEquals(0, inventoryCaptor.getValue().getTotalQuantity());
        
        verify(productMapper, times(1)).toProductResponseDTO(savedProduct);
    }

    @Test
    void testCreateProductWhenCodeDuplicated() {
        // Arrange
        NewProductRequestDTO requestDTO = new NewProductRequestDTO(
                PRODUCT_CODE, PRODUCT_NAME, PRESENTATION_ID, 
                UNITS_PER_PRESENTATION, PRICE_PER_PRESENTATION);

        when(productRepository.existsById(PRODUCT_CODE)).thenReturn(true);

        // Act & Assert
        DuplicatedEntityException exception = assertThrows(
                DuplicatedEntityException.class,
                () -> serviceToTest.createProduct(requestDTO));

        assertEquals("Un producto con el codigo '" + PRODUCT_CODE + "' ya existe.", exception.getMessage());

        verify(productRepository, times(1)).existsById(PRODUCT_CODE);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testCreateProductWhenPresentationNotFound() {
        // Arrange
        NewProductRequestDTO requestDTO = new NewProductRequestDTO(
                PRODUCT_CODE, PRODUCT_NAME, PRESENTATION_ID, 
                UNITS_PER_PRESENTATION, PRICE_PER_PRESENTATION);

        when(productRepository.existsById(PRODUCT_CODE)).thenReturn(false);
        when(presentationRepository.findById(PRESENTATION_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.createProduct(requestDTO));

        assertEquals("Presentación no encontrada con el ID: " + PRESENTATION_ID, exception.getMessage());

        verify(productRepository, times(1)).existsById(PRODUCT_CODE);
        verify(presentationRepository, times(1)).findById(PRESENTATION_ID);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testGetProductSuccessfully() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        product.setName(PRODUCT_NAME);
        product.setIsActive(true);

        ProductResponseDTO responseDTO = new ProductResponseDTO(
                PRODUCT_CODE, PRODUCT_NAME, null, null, null, true);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.of(product));
        when(productMapper.toProductResponseDTO(product)).thenReturn(responseDTO);

        // Act
        ProductResponseDTO result = serviceToTest.getProduct(PRODUCT_CODE);

        // Assert
        assertAll(
                () -> assertEquals(PRODUCT_CODE, result.code()),
                () -> assertEquals(PRODUCT_NAME, result.name()),
                () -> assertTrue(result.isActive())
        );

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(productMapper, times(1)).toProductResponseDTO(product);
    }

    @Test
    void testGetProductWhenNotFound() {
        // Arrange
        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.getProduct(PRODUCT_CODE));

        assertEquals("Producto no encontrado con el código: " + PRODUCT_CODE, exception.getMessage());

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(productMapper, never()).toProductResponseDTO(any());
    }

    @Test
    void testUpdateProductSuccessfully() throws Exception {
        // Arrange
        UpdateProductRequestDTO requestDTO = new UpdateProductRequestDTO(
                "Nuevo nombre", PRESENTATION_ID, 20, 200.0, null);
        
        Product existingProduct = new Product();
        existingProduct.setCode(PRODUCT_CODE);
        existingProduct.setName("Nombre antiguo");
        
        Presentation newPresentation = new Presentation();
        newPresentation.setId(PRESENTATION_ID);
        
        Product updatedProduct = new Product();
        updatedProduct.setCode(PRODUCT_CODE);
        updatedProduct.setName("Nuevo nombre");
        updatedProduct.setPresentation(newPresentation);
        updatedProduct.setUnitsPerPresentation(20);
        updatedProduct.setPricePerPresentation(200.0);
        
        ProductResponseDTO responseDTO = new ProductResponseDTO(
                PRODUCT_CODE, "Nuevo nombre", null, 20, 200.0, true);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.of(existingProduct));
        when(presentationRepository.findById(PRESENTATION_ID)).thenReturn(Optional.of(newPresentation));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
        when(productMapper.toProductResponseDTO(updatedProduct)).thenReturn(responseDTO);

        // Act
        ProductResponseDTO result = serviceToTest.updateProduct(PRODUCT_CODE, requestDTO);

        // Assert
        assertAll(
                () -> assertEquals(PRODUCT_CODE, result.code()),
                () -> assertEquals("Nuevo nombre", result.name()),
                () -> assertEquals(20, result.unitsPerPresentation()),
                () -> assertEquals(200.0, result.pricePerPresentation())
        );

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(presentationRepository, times(1)).findById(PRESENTATION_ID);
        verify(productMapper, times(1)).updateProductFromDTO(requestDTO, existingProduct);
        verify(productRepository, times(1)).save(existingProduct);
        verify(productMapper, times(1)).toProductResponseDTO(updatedProduct);
    }

    @Test
    void testUpdateProductWhenProductNotFound() {
        // Arrange
        UpdateProductRequestDTO requestDTO = new UpdateProductRequestDTO(
                "Nuevo nombre", PRESENTATION_ID, 20, 200.0, null);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.updateProduct(PRODUCT_CODE, requestDTO));

        assertEquals("Producto no encontrado por el código: " + PRODUCT_CODE, exception.getMessage());

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testUpdateProductWhenPresentationNotFound() {
        // Arrange
        UpdateProductRequestDTO requestDTO = new UpdateProductRequestDTO(
                "Nuevo nombre", PRESENTATION_ID, 20, 200.0, null);
        
        Product existingProduct = new Product();
        existingProduct.setCode(PRODUCT_CODE);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.of(existingProduct));
        when(presentationRepository.findById(PRESENTATION_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.updateProduct(PRODUCT_CODE, requestDTO));

        assertEquals("Presentación no encontrada con el ID: " + PRESENTATION_ID, exception.getMessage());

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(presentationRepository, times(1)).findById(PRESENTATION_ID);
        verify(productRepository, never()).save(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetAllProductsWithFilters() {
        // Arrange
        ProductSearchRequestDTO searchDTO = new ProductSearchRequestDTO(PRODUCT_CODE, PRODUCT_NAME, true);
        
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        product.setName(PRODUCT_NAME);
        product.setIsActive(true);
        
        ProductResponseDTO responseDTO = new ProductResponseDTO(
                PRODUCT_CODE, PRODUCT_NAME, null, null, null, true);

        when(productRepository.findAll(any(Specification.class))).thenReturn(List.of(product));
        when(productMapper.toProductResponseDTO(product)).thenReturn(responseDTO);

        // Act
        List<ProductResponseDTO> result = serviceToTest.getAllProducts(searchDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(PRODUCT_CODE, result.get(0).code()),
                () -> assertEquals(PRODUCT_NAME, result.get(0).name()),
                () -> assertTrue(result.get(0).isActive())
        );

        verify(productRepository, times(1)).findAll(any(Specification.class));
        verify(productMapper, times(1)).toProductResponseDTO(product);
    }

    @Test
    void testDeleteProductSuccessfully() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        product.setIsActive(true);
        
        Inventory inventory = new Inventory();
        inventory.setReservedQuantity(0);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.of(product));
        when(inventoryRepository.findByProduct(product)).thenReturn(Optional.of(inventory));

        // Act
        serviceToTest.deleteProduct(PRODUCT_CODE);

        // Assert
        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(inventoryRepository, times(1)).findByProduct(product);
        assertFalse(product.getIsActive());
    }

    @Test
    void testDeleteProductWhenNotFound() {
        // Arrange
        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.deleteProduct(PRODUCT_CODE));

        assertEquals("Producto no encotrado con el código: " + PRODUCT_CODE, exception.getMessage());

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(inventoryRepository, never()).findByProduct(any());
    }

    @Test
    void testDeleteProductWhenHasReservedInventory() {
        // Arrange
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        
        Inventory inventory = new Inventory();
        inventory.setReservedQuantity(5);

        when(productRepository.findById(PRODUCT_CODE)).thenReturn(Optional.of(product));
        when(inventoryRepository.findByProduct(product)).thenReturn(Optional.of(inventory));

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> serviceToTest.deleteProduct(PRODUCT_CODE));

        assertEquals("No se puede eliminar el producto porque tiene inventario reservado en ventas efectuadas.", 
                exception.getMessage());

        verify(productRepository, times(1)).findById(PRODUCT_CODE);
        verify(inventoryRepository, times(1)).findByProduct(product);
    }
}