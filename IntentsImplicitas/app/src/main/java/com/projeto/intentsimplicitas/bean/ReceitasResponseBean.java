package com.projeto.intentsimplicitas.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceitasResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String receita;
    private String ingredientes;
    private String modo_preparo;
    private String link_imagem;
    private String tipo;
    private String created_at;
    private List<IngredientesBaseBean> IngredientesBase;

    public ReceitasResponseBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModo_preparo() {
        return modo_preparo;
    }

    public void setModo_preparo(String modo_preparo) {
        this.modo_preparo = modo_preparo;
    }

    public String getLink_imagem() {
        return link_imagem;
    }

    public void setLink_imagem(String link_imagem) {
        this.link_imagem = link_imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<IngredientesBaseBean> getIngredientesBase() {
        return IngredientesBase;
    }

    public void setIngredientesBase(List<IngredientesBaseBean> ingredientesBase) {
        IngredientesBase = ingredientesBase;
    }

    @Override
    public String toString() {
        return "ReceitasResponseBean{" +
                "id=" + id +
                ", receita='" + receita + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", modo_preparo='" + modo_preparo + '\'' +
                ", link_imagem='" + link_imagem + '\'' +
                ", tipo='" + tipo + '\'' +
                ", created_at='" + created_at + '\'' +
                ", IngredientesBase=" + IngredientesBase +
                '}';
    }
}
