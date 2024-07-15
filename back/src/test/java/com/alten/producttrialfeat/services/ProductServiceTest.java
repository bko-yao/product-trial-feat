package com.alten.producttrialfeat.services;

import com.alten.producttrialfeat.MockBuilder.CategoryMockBuilder;
import com.alten.producttrialfeat.MockBuilder.ProductDtoMockBuilder;
import com.alten.producttrialfeat.MockBuilder.ProductMockBuilder;
import com.alten.producttrialfeat.dto.ProductDTO;
import com.alten.producttrialfeat.entity.Category;
import com.alten.producttrialfeat.entity.Product;
import com.alten.producttrialfeat.exception.CategoryException;
import com.alten.producttrialfeat.exception.ProductException;
import com.alten.producttrialfeat.factory.ProductFactory;
import com.alten.producttrialfeat.repository.CategoryRepository;
import com.alten.producttrialfeat.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private final ProductFactory productFactory = mock(ProductFactory.class);
    private ProductService productService;
    ProductDTO productDTOMockBuilder;
    Category categoryMockBuilder;

    @BeforeEach
    void setUp() {
        // Créer une instance de la classe à tester
        this.productService = new ProductService(categoryRepository, productRepository,
                productFactory);
        productDTOMockBuilder = new ProductDtoMockBuilder()
                .setCode("xx1010x")
                .setName("produit 1")
                .setDescription("Produit pour le test 1")
                .setPrice(10)
                .setInventoryStatus("IN_STOCK")
                .setQuantity(10)
                .setCategory("Accessories")
                .setImage("http://images")
                .build();
        categoryMockBuilder = new CategoryMockBuilder()
                .setId(1L)
                .setCode("ACCESSORIES")
                .setDesignation("Accessories")
                .build();
    }

    @Test
    void ShouldCreateProduct_WhenCreateProduct_WithCategoryExist() {
        // WHEN
        when(categoryRepository.findByCode(productDTOMockBuilder.getCategory().toUpperCase().trim())).thenReturn(Optional.of(categoryMockBuilder));
        ResponseEntity<?> response = productService.createProduct(productDTOMockBuilder);

        // THEN
        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals(ResponseEntity.ok().body("Produit enregistré avec succès."), response);
    }

    @Test
    void ShouldReturnThrow_WhenCreateProduct_WithCategoryNotExist() {
        when(categoryRepository.findByCode(productDTOMockBuilder.getCategory().toUpperCase().trim())).thenReturn(Optional.empty());

        // WHEN & THEN
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            productService.createProduct(productDTOMockBuilder);
        });

        assertEquals("La Catégorie ACCESSORIES n'existe pas.", exception.getMessage());
        assertEquals(NOT_FOUND.value(), exception.getErrorCode());
    }

    @Test
    void ShouldReturnAllProduct_WhenGetAllProducts() {
        // GIVEN
        Product productMockBuilder1 = new ProductMockBuilder()
                .setCode("code 2")
                .setName("produit 2")
                .setDescription("Produit pour le test 2")
                .setPrice(10)
                .setInventoryStatus("IN_STOCK")
                .setQuantity(10)
                .setCategory(categoryMockBuilder)
                .setImage("http://images")
                .build();
        Product productMockBuilder2 = new ProductMockBuilder()
                .setCode("code 2")
                .setName("produit 2")
                .setDescription("Produit pour le test 2")
                .setPrice(10)
                .setInventoryStatus("IN_STOCK")
                .setQuantity(10)
                .setCategory(categoryMockBuilder)
                .setImage("http://images")
                .build();
        List<Product> products = Arrays.asList(
                productMockBuilder1,
                productMockBuilder2
        );
        ProductDTO productDTOMockBuilder2 = new ProductDtoMockBuilder()
                .setCode("code 2")
                .setName("produit 2")
                .setDescription("Produit pour le test 2")
                .setPrice(10)
                .setInventoryStatus("IN_STOCK")
                .setQuantity(10)
                .setCategory("Accessories")
                .setImage("http://images")
                .build();
        Set<ProductDTO> productDTOs = new HashSet<>(Arrays.asList(
                productDTOMockBuilder, productDTOMockBuilder2
        ));

        when(productRepository.findAll()).thenReturn(products);
        when(productFactory.mapProductsToProductDtos(products)).thenReturn(productDTOs);

        // WHEN
        Set<ProductDTO> result = productService.getAllProducts();

        // THEN
        assertEquals(productDTOs, result);
        verify(productRepository, times(1)).findAll();
        verify(productFactory, times(1)).mapProductsToProductDtos(products);
    }

    @Test
    void ShouldReturnEmptyList_WhenGetAllProducts() {
        // GIVEN

        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        when(productFactory.mapProductsToProductDtos(Collections.emptyList())).thenReturn(Collections.emptySet());

        // WHEN
        Set<ProductDTO> result = productService.getAllProducts();

        // THEN
        assertEquals(Collections.emptySet(), result);
        verify(productRepository, times(1)).findAll();
        verify(productFactory, times(1)).mapProductsToProductDtos(Collections.emptyList());
    }

    @Test
    void ShouldReturnProductById_WhenGetProductById() {
        // GIVEN
        long productId = 1L;
        Product product = new ProductMockBuilder()
                .setId(1L)
                .build();
        ProductDTO productDTO = new ProductDtoMockBuilder()
                .setId(1L)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productFactory.mapProductToProductDto(product)).thenReturn(productDTO);

        // WHEN
        ProductDTO result = productService.getProductById(productId);

        // THEN
        assertNotNull(result);
        assertEquals(productDTO, result);
        verify(productRepository).findById(productId);
        verify(productFactory).mapProductToProductDto(product);
    }

    @Test
    void ShouldReturnThrow_WhenGetProductById_WithProductNotExist() {
        // GIVEN
        long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // WHEN & THEN
        ProductException exception = assertThrows(ProductException.class, () -> {
            productService.getProductById(productId);
        });

        assertEquals("Le Produit avec l'id " + productId + " n'existe pas.", exception.getMessage());
        verify(productRepository).findById(productId);
        verify(productFactory, never()).mapProductToProductDto(any());
    }

    @Test
    void ShouldDeleteProductById_WhenDeleteProduct() {
        // GIVEN
        long productId = 1L;
        long expectedCount = 5L;

        doNothing().when(productRepository).deleteById(productId);
        when(productRepository.count()).thenReturn(expectedCount);

        // WHEN
        productService.deleteProduct(productId);

        // THEN
        verify(productRepository).deleteById(productId);
    }

    @Test
    void ShouldUpdateProductName_WhenUpdateProduct_WithProductExist() {
        // GIVEN
        long productId = 1L;
        Product existingProduct = new ProductMockBuilder()
                .setName("Ancien nom du produit")
                .build();
        Product updatedProduct = new ProductMockBuilder()
                .setName("Nouveau nom du produit")
                .build();
        ProductDTO productDTO = new ProductDtoMockBuilder()
                .setName("Nouveau nom du produit")
                .build();

        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "Nouveau nom du produit");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
        when(productFactory.mapProductToProductDto(updatedProduct)).thenReturn(productDTO);

        // WHEN
        ProductDTO result = productService.updateProduct(productId, fields);

        // THEN
        assertNotNull(result);
        assertEquals("Nouveau nom du produit", result.getName());
        verify(productRepository).findById(productId);
        verify(productRepository).save(existingProduct);
        verify(productFactory).mapProductToProductDto(updatedProduct);
    }

    @Test
    void ShouldReturnThrow_WhenUpdateProduct_WithProductNotExist() {
        // GIVEN
        long productId = 1L;
        Map<String, Object> fields = new HashMap<>();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // WHEN & THEN
        ProductException exception = assertThrows(ProductException.class, () -> {
            productService.updateProduct(productId, fields);
        });

        assertEquals("Le Produit avec l'id " + productId + " n'existe pas.", exception.getMessage());
        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(any());
        verify(productFactory, never()).mapProductToProductDto(any());
    }

    @Test
    public void ShouldUpdateProductName_WhenUpdateProduct_WithProductExistAndCategoryNotExist() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("category", "NON_EXISTENT");
        Product existingProduct = new ProductMockBuilder()
                .setName("Ancien nom du produit")
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(categoryRepository.findByCode("NON_EXISTENT")).thenReturn(Optional.empty());

        CategoryException exception = assertThrows(CategoryException.class, () -> {
            productService.updateProduct(1L, fields);
        });

        assertEquals("La Catégorie NON_EXISTENT n'existe pas.", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findByCode("NON_EXISTENT");
    }
}

