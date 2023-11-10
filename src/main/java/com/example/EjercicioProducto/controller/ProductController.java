package com.example.EjercicioProducto.controller;

import com.example.EjercicioProducto.error.ProductAlreadyExistsException;
import com.example.EjercicioProducto.error.ProductToOverwriteNotFoundException;
import com.example.EjercicioProducto.error.UserNotFoundException;
import com.example.EjercicioProducto.model.Product;
import com.example.EjercicioProducto.error.ProductNotFoundException;
import com.example.EjercicioProducto.model.User;
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
    public ResponseEntity<?> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
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

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, @PathVariable Integer id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        if (product.getId() == 0 || id.equals(product.getId())) {
            product.setId(id);
            productRepository.save(product);
        }
        else {
            productRepository.findById(product.getId()).orElseThrow(() -> new ProductToOverwriteNotFoundException(product.getId()));
            productRepository.deleteById(id);
            productRepository.save(product);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
