package com.projeto.intentsimplicitas.bean;

import java.io.Serializable;

public class QuemEMaisBean implements Serializable {

    private int seq;
    private String pergunta;
    private String nome;
    private int escolha;

    public QuemEMaisBean(int seq, String pergunta, String nome, int escolha) {
        this.seq = seq;
        this.pergunta = pergunta;
        this.nome = nome;
        this.escolha = escolha;
    }

    public QuemEMaisBean() {
    }

    public QuemEMaisBean(int seq, String pergunta) {
        this.seq = seq;
        this.pergunta = pergunta;
    }

    public QuemEMaisBean(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEscolha() {
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }
}
