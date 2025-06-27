package com.ayd2.imporcomgua.services.products;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.products.NewProductRequestDTO;
import com.ayd2.imporcomgua.dto.products.ProductResponseDTO;
import com.ayd2.imporcomgua.dto.products.ProductSearchRequestDTO;
import com.ayd2.imporcomgua.dto.products.UpdateProductRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.product.ProductMapper;
import com.ayd2.imporcomgua.models.product.Presentation;
import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.product.PresentationRepository;
import com.ayd2.imporcomgua.repositories.product.ProductRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;
import com.ayd2.imporcomgua.specifications.product.ProductSpecs;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PresentationRepository presentationRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public ProductResponseDTO createProduct(NewProductRequestDTO productRequestDTO) throws NotFoundException, DuplicatedEntityException {
        if (productRepository.existsById(productRequestDTO.code())) {
            throw new DuplicatedEntityException(
                    "Un producto con el codigo '" + productRequestDTO.code() + "' ya existe.");
        }

        Presentation presentation = presentationRepository.findById(productRequestDTO.presentationId())
                .orElseThrow(() -> new NotFoundException("Presentación no encontrada con el ID: " + productRequestDTO.presentationId()));

        Product product = productMapper.toProduct(productRequestDTO);
        product.setPresentation(presentation);

        Product savedProduct = productRepository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        inventory.setTotalQuantity(0);
        inventory.setAvailableQuantity(0);
        inventory.setReservedQuantity(0);

        inventoryRepository.save(inventory);

        return productMapper.toProductResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(String code, UpdateProductRequestDTO productRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        Product existingproduct = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado por el código: " + code));

        if (productRequestDTO.presentationId() != null) {
            Presentation presentation = presentationRepository.findById(productRequestDTO.presentationId())
                    .orElseThrow(() -> new NotFoundException("Presentación no encontrada con el ID: " + productRequestDTO.presentationId()));
            existingproduct.setPresentation(presentation);
        }
        
        productMapper.updateProductFromDTO(productRequestDTO, existingproduct);

        Product updatedproduct = productRepository.save(existingproduct);
        return productMapper.toProductResponseDTO(updatedproduct);
    }


    @Override
    public ProductResponseDTO getProduct(String code) throws NotFoundException {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con el código: " + code));
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(ProductSearchRequestDTO productSearchRequestDTO) {
        Specification<Product> spec = Specification.anyOf(
            ProductSpecs.hasCode(productSearchRequestDTO.code()),
            ProductSpecs.nameContains(productSearchRequestDTO.name())
        ).and(
            ProductSpecs.isActive(productSearchRequestDTO.isActive())
        );
        List<Product> products = productRepository.findAll(spec);
        return products.stream()
                       .map(productMapper::toProductResponseDTO)
                       .toList(); 
    }

    @Override
    public void deleteProduct(String code) throws NotFoundException {
         Product product = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encotrado con el código: " + code));
        
        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() -> new NotFoundException("Inventario no encontrado para el producto: " + product.getCode()));
        // Verificar si el inventario tiene productos
        if (inventory.getReservedQuantity() > 0) {
            throw new IllegalStateException("No se puede eliminar el producto porque tiene inventario reservado en ventas efectuadas.");
        }

        product.setIsActive(false);
    }
}
