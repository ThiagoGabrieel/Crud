package com.thiago.exceptions;

public class NomeInvalidoException extends UsuarioException {
    public NomeInvalidoException() {
        super("Nome inválido");
    }
}
