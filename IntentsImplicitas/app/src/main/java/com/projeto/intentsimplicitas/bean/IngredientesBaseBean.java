package com.projeto.intentsimplicitas.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IngredientesBaseBean implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("nomesIngrediente")
    private List<String> nomesIngrediente;
    @SerializedName("receita_id")
    private Integer receitaId;

    public IngredientesBaseBean(Integer id, List<String> nomesIngrediente, Integer receitaId) {
        this.id = id;
        this.nomesIngrediente = nomesIngrediente;
        this.receitaId = receitaId;
    }

    public IngredientesBaseBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getNomesIngrediente() {
        return nomesIngrediente;
    }

    public void setNomesIngrediente(List<String> nomesIngrediente) {
        this.nomesIngrediente = nomesIngrediente;
    }

    public Integer getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(Integer receitaId) {
        this.receitaId = receitaId;
    }

    @Override
    public String toString() {
        return "IngredientesBaseBean{" +
                "id=" + id +
                ", nomesIngrediente=" + nomesIngrediente +
                ", receitaId=" + receitaId +
                '}';
    }
}
