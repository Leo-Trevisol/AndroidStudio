package com.projeto.intentsimplicitas.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceitasResponseBean implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("receita")
    private String receita;
    @SerializedName("modo_preparo")
    private String modoPreparo;
    @SerializedName("link_imagem")
    private String linkImagem;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("ingredientesBase")
    private IngredientesBaseBean ingredientesBase;

    public ReceitasResponseBean(Integer id, String receita, String modoPreparo, String linkImagem, String tipo, IngredientesBaseBean ingredientesBase) {
        this.id = id;
        this.receita = receita;
        this.modoPreparo = modoPreparo;
        this.linkImagem = linkImagem;
        this.tipo = tipo;
        this.ingredientesBase = ingredientesBase;
    }

    public ReceitasResponseBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getLinkImagem() {
        return linkImagem;
    }

    public void setLinkImagem(String linkImagem) {
        this.linkImagem = linkImagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public IngredientesBaseBean getIngredientesBase() {
        return ingredientesBase;
    }

    public void setIngredientesBase(IngredientesBaseBean ingredientesBase) {
        this.ingredientesBase = ingredientesBase;
    }

    @Override
    public String toString() {
        return "ReceitasBean{" +
                "id=" + id +
                ", receita='" + receita + '\'' +
                ", modoPreparo='" + modoPreparo + '\'' +
                ", linkImagem='" + linkImagem + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ingredientesBase=" + ingredientesBase +
                '}';
    }
}
