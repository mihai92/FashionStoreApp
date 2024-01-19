package com.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.business.repositories.ProductRepository;
import com.business.services.ProductService;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        this.productService = new ProductService(productRepository);
    }

    @Test
    void addProduct_shouldThrowIllegalArgumentExceptionWhenProductIsNull() {
        try {
            productService.addProduct(null);
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Product cannot be null", e.getMessage());
        }
    }
}

