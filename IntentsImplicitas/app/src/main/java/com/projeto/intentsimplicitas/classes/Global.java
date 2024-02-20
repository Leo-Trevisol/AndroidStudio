package com.projeto.intentsimplicitas.classes;

import android.graphics.Color;

import com.projeto.intentsimplicitas.R;

import java.util.ArrayList;
import java.util.List;

public class Global {
    private static Global instance;

    private CasalBean casalBean;

    private List<CasalBean> lstCasaisBean;

    private static final String DEFAULT_COLOR = "#B80E0E";

    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public int getDefaultColorred() {
        try {
            return Color.parseColor(DEFAULT_COLOR);
        } catch (Exception e) {
            return Color.parseColor(DEFAULT_COLOR);
        }
    }

    public static void setInstance(Global instance) {
        Global.instance = instance;
    }

    public CasalBean getCasaisBean() {
        return casalBean;
    }

    public void setCasaisBean(CasalBean casalBean) {
        this.casalBean = casalBean;
    }

    public List<CasalBean> getLstCasaisBean() {

        if(lstCasaisBean == null)
            lstCasaisBean = new ArrayList<>();

        lstCasaisBean.add(new CasalBean("Casal de Porcos", R.drawable.porcos));
        lstCasaisBean.add(new CasalBean("Casal de Cavalos",
                R.drawable.cavalos));
        lstCasaisBean.add(new CasalBean("Casal de Corujas",
                R.drawable.corujas));
        lstCasaisBean.add(new CasalBean("Casal de Ratos",
                R.drawable.ratos));
        lstCasaisBean.add(new CasalBean("Casal de Patos",
                R.drawable.patos));
        lstCasaisBean.add(new CasalBean("Casal de Tigres",
                R.drawable.tigres));

        return lstCasaisBean;
    }

    public void setLstCasaisBean(List<CasalBean> lstCasaisBean) {
        this.lstCasaisBean = lstCasaisBean;
    }
}
