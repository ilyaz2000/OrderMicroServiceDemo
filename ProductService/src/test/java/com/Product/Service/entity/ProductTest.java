package com.Product.Service.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(new BigDecimal("999.99"));
        product.setStockQuantity(10);
    }

    @Test
    void createProduct_shouldSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.createProduct(product);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals("Gaming Laptop", result.getDescription());
        assertEquals(new BigDecimal("999.99"), result.getPrice());
        assertEquals(10, result.getStockQuantity());
        verify(productRepository).save(product);
    }

    @Test
    void getProductById_shouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals("Gaming Laptop", result.getDescription());
        assertEquals(new BigDecimal("999.99"), result.getPrice());
        assertEquals(10, result.getStockQuantity());
        verify(productRepository).findById(1L);
    }

    @Test
    void updateProduct_shouldUpdateAndSaveProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Laptop");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(new BigDecimal("1099.99"));
        updatedProduct.setStockQuantity(20);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Product result = productService.updateProduct(1L, updatedProduct);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Laptop", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(new BigDecimal("1099.99"), result.getPrice());
        assertEquals(20, result.getStockQuantity());
        verify(productRepository).findById(1L);
        verify(productRepository).save(product);
    }

    @Test
    void deleteProduct_shouldDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        productService.deleteProduct(1L);
        verify(productRepository).findById(1L);
        verify(productRepository).delete(product);
    }
}