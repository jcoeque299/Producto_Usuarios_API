package com.example.EjercicioProducto.controller;

import com.example.EjercicioProducto.error.ProductAlreadyExistsException;
import com.example.EjercicioProducto.model.Product;
import com.example.EjercicioProducto.error.ProductNotFoundException;
import com.example.EjercicioProducto.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductByID(@PathVariable Integer id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productRepository.findById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        HttpHeaders locationHeader = new HttpHeaders();
        locationHeader.setLocation(URI.create("/api/products/"+product.getId()));
        if (productRepository.existsById(product.getId())) {
            throw new ProductAlreadyExistsException(product.getId());
        }
        productRepository.save(product);
        return new ResponseEntity<>(product, locationHeader, HttpStatus.CREATED);
    }
}
