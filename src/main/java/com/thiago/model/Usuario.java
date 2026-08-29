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

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setId(long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) {
        if(email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }
    public void setSenha(String senha) {
        if(senha == null || senha.matches("[a-zA-Z0-9@#]")){
            throw new IllegalArgumentException("Senha inválida");
        }
        this.senha = senha;
    }
}
