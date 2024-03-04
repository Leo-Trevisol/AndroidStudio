package com.projeto.intentsimplicitas.response;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public abstract class ResponseAbstract implements Serializable {
    private static final long serialVersionUID = -3905292191067974740L;

    private List<String> listDesErro;

    private List<String> listDesInfo;

    private boolean sessaoExpirada;

    public ResponseAbstract() {
    }

    public List<String> getListDesErro() {
        if (this.listDesErro == null) {
            this.listDesErro = new ArrayList();
        }

        return this.listDesErro;
    }

    public void setListDesErro(List<String> listDesErro) {
        this.listDesErro = listDesErro;
    }

    public List<String> getListDesInfo() {
        if (this.listDesInfo == null) {
            this.listDesInfo = new ArrayList();
        }

        return this.listDesInfo;
    }

    public void setListDesInfo(List<String> listDesInfo) {
        this.listDesInfo = listDesInfo;
    }

    public boolean isSessaoExpirada() {
        return this.sessaoExpirada;
    }

    public void setSessaoExpirada(boolean sessaoExpirada) {
        this.sessaoExpirada = sessaoExpirada;
    }
}
