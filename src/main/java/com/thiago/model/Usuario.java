package com.thiago.model;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(long id,  String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        setEmail(email);
        setSenha(senha);
    }
}
