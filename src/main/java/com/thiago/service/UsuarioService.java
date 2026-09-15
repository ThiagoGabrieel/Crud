package com.thiago.service;

import com.thiago.exceptions.*;
import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Usuario cadastrar(String nome, String email, String senha) {

        if(nome == null || nome.isEmpty()){
            throw new NomeInvalidoException("Nome inválido");
        }
        if (repository.emailJaExistente(email)) {
            throw new EmailJaCadastradoException("Email já Existente no momento!");
        }
        if (senha == null || !senha.matches("[a-zA-Z0-9@#]{1,10}")) {
            throw new SenhaInvalidaException("Senha inválida");
        }

        Usuario Novousuario = new Usuario(nome, email, senha);
        repository.salvar(Novousuario);

        System.out.println("Usuario cadastrado com sucesso!");
        return Novousuario;
    }

    public Usuario login(String email, String senha) {
        Usuario encontrado = repository.buscarPorEmail(email);

        if (encontrado == null) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
        if (!encontrado.verificarSenha(senha)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        return encontrado;
    }

    public Usuario atualizarEmail(long id, String email, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
        if (!usuario.verificarSenha(senha)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        if (repository.emailJaExistente(email)) {
            throw new EmailJaCadastradoException("Inválido. Esse email já foi cadastrado!");
        }

        repository.atualizarEmail(id, email);
        System.out.println("Email atualizado com sucesso!");

        return repository.buscarPorId(id);
    }

    public Usuario atualizarSenha(long id, String senhaDigitada, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
        if (!usuario.verificarSenha(senhaDigitada)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        if (!senha.matches("[a-zA-Z0-9@#]{1,10}")) {
            throw new SenhaInvalidaException("Senha invalida. Maximo 10 caracteres, caracteres especiais permitidos: @ e #");
        }
        repository.atualizarSenha(id, senha);
        System.out.println("Senha atualizada com sucesso!");

        return repository.buscarPorId(id);
    }

    public Usuario deletar(long id, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
        if(senha == null || !usuario.verificarSenha(senha)){
            throw new SenhaIncorretaException("Senha incorreta!");
        }

        repository.deletePorId(id);
        System.out.println("Conta deletada com sucesso!");

        return usuario;
    }
}