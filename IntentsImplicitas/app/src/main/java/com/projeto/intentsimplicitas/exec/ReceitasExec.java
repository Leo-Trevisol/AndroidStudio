package com.projeto.intentsimplicitas.exec;

import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;

import java.io.Serializable;
import java.util.List;

public class ReceitasExec implements Serializable {

    private static ReceitasExec instance;

    private List<ReceitasResponseBean> lstReceitasSalgadas, lstReceitasDoces, lstReceitasAgridoces;

    ReceitasResponseBean receitasResponseBean;

    public static ReceitasExec getInstance() {
        if (instance == null) {
            instance = new ReceitasExec();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }

    public boolean isReceitasSalgadasAtivas(){




        return false;
    }

    public List<ReceitasResponseBean> getLstReceitasSalgadas() {
        return lstReceitasSalgadas;
    }

    public void setLstReceitasSalgadas(List<ReceitasResponseBean> lstReceitasSalgadas) {
        this.lstReceitasSalgadas = lstReceitasSalgadas;
    }

    public List<ReceitasResponseBean> getLstReceitasDoces() {
        return lstReceitasDoces;
    }

    public void setLstReceitasDoces(List<ReceitasResponseBean> lstReceitasDoces) {
        this.lstReceitasDoces = lstReceitasDoces;
    }

    public List<ReceitasResponseBean> getLstReceitasAgridoces() {
        return lstReceitasAgridoces;
    }

    public void setLstReceitasAgridoces(List<ReceitasResponseBean> lstReceitasAgridoces) {
        this.lstReceitasAgridoces = lstReceitasAgridoces;
    }


}
