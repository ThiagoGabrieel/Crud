package com.thiago.service;

import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public Usuario cadastrar(String nome, String email, String senha){

        if(repository.emailJaExistente(email)){
            throw new IllegalArgumentException("Email já Existente no momento!");
        }
        if(senha == null || !senha.matches("[a-zA-Z0-9@#]{10}")){
            throw new IllegalArgumentException("Senha invalida");
        }

        Usuario Novousuario = new Usuario(nome, email, senha);
        repository.salvar(Novousuario);

        System.out.println("Usuario cadastrado com sucesso!");
        return Novousuario;
    }
}
