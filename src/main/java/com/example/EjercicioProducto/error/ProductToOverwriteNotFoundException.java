package com.example.EjercicioProducto.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductToOverwriteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43876691117560211L;
    public ProductToOverwriteNotFoundException(Integer id) {
        super("No se puede sobreescribir el producto con ID "+id+", ya que no existe");
    }
}
