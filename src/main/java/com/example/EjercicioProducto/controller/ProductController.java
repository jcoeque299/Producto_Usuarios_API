package com.example.EjercicioProducto.controller;

import com.example.EjercicioProducto.model.Product;
import com.example.EjercicioProducto.error.ProductNotFoundException;
import com.example.EjercicioProducto.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductByID(@PathVariable Integer id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productRepository.findById(id);
    }
}
