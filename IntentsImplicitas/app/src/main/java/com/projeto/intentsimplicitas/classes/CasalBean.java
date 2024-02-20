package com.projeto.intentsimplicitas.classes;

import java.io.Serializable;

public class CasalBean implements Serializable {

    private String nomeCasal;

    private int imagemCasal;

    private String pessoa1;

    private String pessoa2;

    public CasalBean(String nomeCasal, int imagemCasal, String pessoa1, String pessoa2) {
        this.nomeCasal = nomeCasal;
        this.imagemCasal = imagemCasal;
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
    }

    public CasalBean() {

    }

    public CasalBean(String nomeCasal, int imagemCasal) {
        this.nomeCasal = nomeCasal;
        this.imagemCasal = imagemCasal;
    }

    public CasalBean(String pessoa1, String pessoa2) {
        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
    }

    public String getNomeCasal() {
        return nomeCasal;
    }

    public void setNomeCasal(String nomeCasal) {
        this.nomeCasal = nomeCasal;
    }

    public int getImagemCasal() {
        return imagemCasal;
    }

    public void setImagemCasal(int imagemCasal) {
        this.imagemCasal = imagemCasal;
    }

    public String getPessoa1() {
        return pessoa1;
    }

    public void setPessoa1(String pessoa1) {
        this.pessoa1 = pessoa1;
    }

    public String getPessoa2() {
        return pessoa2;
    }

    public void setPessoa2(String pessoa2) {
        this.pessoa2 = pessoa2;
    }

    @Override
    public String toString() {
        return  nomeCasal ;
    }

}
