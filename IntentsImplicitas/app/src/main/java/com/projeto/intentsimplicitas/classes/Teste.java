package com.projeto.intentsimplicitas.classes;

import java.io.Serializable;

public class Teste implements Serializable {

    private String nome;

    private String senha;

    public Teste(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Teste() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
