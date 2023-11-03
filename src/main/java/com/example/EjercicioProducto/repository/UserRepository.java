package com.example.EjercicioProducto.repository;

import com.example.EjercicioProducto.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
}
