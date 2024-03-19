package com.projeto.intentsimplicitas.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IngredientesBaseBean implements Serializable {
    private int id;
    private List<String> nomesIngrediente;
    private int receita_id;
    private String created_at;


    public IngredientesBaseBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getNomesIngrediente() {

        if(nomesIngrediente == null)
            nomesIngrediente = new ArrayList<>();

        return nomesIngrediente;
    }

    public void setNomesIngrediente(List<String> nomesIngrediente) {
        this.nomesIngrediente = nomesIngrediente;
    }

    public int getReceita_id() {
        return receita_id;
    }

    public void setReceita_id(int receita_id) {
        this.receita_id = receita_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
