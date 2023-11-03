package com.example.EjercicioProducto.repository;

import com.example.EjercicioProducto.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {
}
