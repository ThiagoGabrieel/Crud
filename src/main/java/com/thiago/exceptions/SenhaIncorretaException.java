package com.thiago.exceptions;

public class SenhaIncorretaException extends UsuarioException {
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
