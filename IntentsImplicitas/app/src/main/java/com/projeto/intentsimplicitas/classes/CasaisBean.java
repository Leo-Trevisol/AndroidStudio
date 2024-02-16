package com.projeto.intentsimplicitas.classes;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CasaisBean implements Serializable {

    private String nomeCasal;

    private int imagemCasal;

    public CasaisBean(String nomeCasal, int imagemCasal) {
        this.nomeCasal = nomeCasal;
        this.imagemCasal = imagemCasal;
    }

    public CasaisBean() {

    }

    protected CasaisBean(Parcel in) {
        nomeCasal = in.readString();
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

    @Override
    public String toString() {
        return  nomeCasal ;
    }

}
