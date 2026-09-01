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

    public Usuario login(String email, String senha){
        Usuario encontrado = repository.buscarPorEmail(email);

        if(encontrado == null){
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(!encontrado.verificarSenha(senha)){
            throw new IllegalArgumentException("Senha incorreta!");
        }
        return encontrado;
    }

    public Usuario atualizarEmail(long id, String email, String senha){
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null){
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(!usuario.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if(repository.emailJaExistente(email)){
            throw new IllegalArgumentException("Invalido. Esse email já foi cadastrado!");
        }

        repository.atualizarEmail(id, email);
        System.out.println("Email atualizado com sucesso!");

        return repository.buscarPorId(id);
    }
}
