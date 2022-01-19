package com.example.kaio;

public class User {
    String uid;
    String nome;
    String email;
    String senha;
    String data_nasc;

    public User(String uid, String nome, String email, String senha, String data_nasc) {
        this.uid = uid;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_nasc = data_nasc;
    }

    public String getUid() {
        return uid;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getData_nasc() {
        return data_nasc;
    }
}
