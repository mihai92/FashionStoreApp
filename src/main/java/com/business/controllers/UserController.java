package com.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.business.entities.Product;
import com.business.repositories.ProductRepository;

@Controller
public class UserController {

    private final ProductRepository productRepository;

    @Autowired
    public UserController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/userlogin")
    public String allProduct(Model model) {
        List<Product> product = (List<Product>) productRepository.findAll();
        model.addAttribute("products", product);
        return "All_Product";
    }

    @GetMapping("/selecting")
    public String selectedProduct() {
        return "Product_Selected";
    }
}


