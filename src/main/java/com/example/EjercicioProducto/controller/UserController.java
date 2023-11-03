package com.example.EjercicioProducto.controller;

import com.example.EjercicioProducto.error.UserNotFoundException;
import com.example.EjercicioProducto.model.User;
import com.example.EjercicioProducto.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserByID(@PathVariable Integer id) throws Throwable {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userRepository.findById(id);
    }
}
