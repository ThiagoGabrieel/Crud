package com.thiago.service;

import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository = new UsuarioRepository();

    public Usuario cadastrar(String nome, String email, String senha) {

        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome inválido");
        }
        if (repository.emailJaExistente(email)) {
            throw new IllegalArgumentException("Email já Existente no momento!");
        }
        if (senha == null || !senha.matches("[a-zA-Z0-9@#]{1,10}")) {
            throw new IllegalArgumentException("Senha invalida");
        }

        Usuario Novousuario = new Usuario(nome, email, senha);
        repository.salvar(Novousuario);

        System.out.println("Usuario cadastrado com sucesso!");
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
            throw new IllegalArgumentException("Inválido. Esse email já foi cadastrado!");
        }

        repository.atualizarEmail(id, email);
        System.out.println("Email atualizado com sucesso!");

        return repository.buscarPorId(id);
    }

    public Usuario atualizarSenha(long id, String senha, String senhaDigitada) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if (!usuario.verificarSenha(senhaDigitada)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if (!senha.matches("[a-zA-Z0-9@#]{1,10}")) {
            throw new IllegalArgumentException("Senha invalida. Maximo 10 caracteres, caracteres especiais permitidos: @ e #");
        }
        repository.atualizarSenha(id, senha);
        System.out.println("Senha atualizada com sucesso!");

        return repository.buscarPorId(id);
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
        System.out.println("Conta deletada com sucesso!");

        return usuario;
    }
}