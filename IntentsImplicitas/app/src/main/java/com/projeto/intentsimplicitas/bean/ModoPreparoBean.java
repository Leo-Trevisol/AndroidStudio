package com.projeto.intentsimplicitas.bean;

import java.util.List;

public class ModoPreparoBean {

    private List<String> passo;

    private List<String> descricao;

    public ModoPreparoBean(List<String> passo, List<String> descricao) {
        this.passo = passo;
        this.descricao = descricao;
    }

    public ModoPreparoBean() {
    }

    public List<String> getPasso() {
        return passo;
    }

    public void setPasso(List<String> passo) {
        this.passo = passo;
    }

    public List<String> getDescricao() {
        return descricao;
    }

    public void setDescricao(List<String> descricao) {
        this.descricao = descricao;
    }
}
