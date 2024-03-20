package com.projeto.intentsimplicitas.bean;

import java.util.ArrayList;
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
        if (passo == null)
            passo = new ArrayList<>();

        return passo;
    }

    public void setPasso(List<String> passo) {
        this.passo = passo;
    }

    public List<String> getDescricao() {
        if(descricao == null)
            descricao = new ArrayList<>();

        return descricao;
    }

    public void setDescricao(List<String> descricao) {
        this.descricao = descricao;
    }
}
