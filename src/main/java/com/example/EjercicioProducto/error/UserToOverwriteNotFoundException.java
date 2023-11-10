package com.example.EjercicioProducto.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserToOverwriteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43876691117560211L;
    public UserToOverwriteNotFoundException(Integer id) {
        super("No se puede sobreescribir el usuario con ID "+id+", ya que no existe");
    }
}
