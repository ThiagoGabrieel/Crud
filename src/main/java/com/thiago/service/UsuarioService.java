package com.thiago.service;

import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Usuario cadastrar(String nome, String email, String senha) {

        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome inválido");
        }
        if (repository.emailJaExistente(email)) {
            throw new IllegalArgumentException("Email já Existente no momento!");
        }
        if (senha == null || !senha.matches("[a-zA-Z0-9@#]{4,10}")) {
            throw new IllegalArgumentException("Senhna Fora dos padrôes Exigidos");
        }

        Usuario Novousuario = new Usuario(nome, email, senha);
        repository.salvar(Novousuario);

        return Novousuario;
    }

    public Usuario login(String email, String senha) {
        Usuario encontrado = repository.buscarPorEmail(email);

        if (encontrado == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if (!encontrado.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        return encontrado;
    }

    public Usuario atualizarEmail(long id, String email, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if (!usuario.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if (repository.emailJaExistente(email)) {
            throw new IllegalArgumentException("Esse email já foi cadastrado!");
        }

        repository.atualizarEmail(id, email);
        return usuario;
    }

    public Usuario atualizarSenha(long id, String senhaDigitada, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if (!usuario.verificarSenha(senhaDigitada)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if (!senha.matches("[a-zA-Z0-9@#]{4,10}")) {
            throw new IllegalArgumentException("Senha Fora dos Padrões Exigidos");
        }

        repository.atualizarSenha(id, senha);
        return usuario;
    }

    public Usuario deletar(long id, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(senha == null || !usuario.verificarSenha(senha)){
            throw new IllegalArgumentException("Senha incorreta!");
        }

        repository.deletePorId(id);
        return usuario;
    }
}