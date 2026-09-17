package com.thiago.exceptions;

public abstract class UsuarioException extends RuntimeException {

    public UsuarioException(String message) {
        super(message);
    }
}
