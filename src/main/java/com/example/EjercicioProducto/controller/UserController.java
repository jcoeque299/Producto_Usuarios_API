package com.example.EjercicioProducto.controller;

import com.example.EjercicioProducto.error.UserAlreadyExistsException;
import com.example.EjercicioProducto.error.UserNotFoundException;
import com.example.EjercicioProducto.error.UserToOverwriteNotFoundException;
import com.example.EjercicioProducto.model.User;
import com.example.EjercicioProducto.repository.UserRepository;
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
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserByID(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> addProduct(@Valid @RequestBody User user) {
        HttpHeaders locationHeader = new HttpHeaders();
        locationHeader.setLocation(URI.create("/api/users/"+user.getId()));
        if (userRepository.existsById(user.getId())) {
            throw new UserAlreadyExistsException(user.getId());
        }
        userRepository.save(user);
        return new ResponseEntity<>(user, locationHeader, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, @PathVariable Integer id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user.getId() == 0 || id.equals(user.getId())) {
            user.setId(id);
            userRepository.save(user);
        }
        else {
            userRepository.findById(user.getId()).orElseThrow(() -> new UserToOverwriteNotFoundException(user.getId()));
            userRepository.deleteById(id);
            userRepository.save(user);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
