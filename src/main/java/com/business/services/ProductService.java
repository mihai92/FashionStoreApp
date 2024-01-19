package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business.entities.Product;
import com.business.repositories.ProductRepository;

@Component
public class ProductService {
	private final ProductRepository productRepository;

    @Autowired
		public ProductService(ProductRepository productRepository) {
        	this.productRepository = productRepository;
    }

    //add product
    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.productRepository.save(p);
    }

    //getAll product
    public List<Product> getAllProducts() {
        return (List<Product>) this.productRepository.findAll();
    }

    //get single product
    public Product getProduct(int id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    //update product
	public void updateProduct(Product p, int id) {
        p.setPid(id);
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product prod = optionalProduct.get();
            if (prod.getPid() == id) {
                this.productRepository.save(p);
            }
        }
    }

    //delete product
    public void deleteProduct(int id) {
        this.productRepository.deleteById(id);
    }
}
