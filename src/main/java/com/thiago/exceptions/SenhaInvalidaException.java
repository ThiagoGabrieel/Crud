package com.thiago.exceptions;

public class SenhaInvalidaException extends UsuarioException {
    public SenhaInvalidaException(String message) {
        super(message);
    }
}
