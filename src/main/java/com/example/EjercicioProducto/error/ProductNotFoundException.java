package com.example.EjercicioProducto.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43876691117560211L;
    public ProductNotFoundException(Integer id) {
        super("Producto con id "+id+ " no encontrado");
    }
}