package com.projeto.intentsimplicitas.classes;

import android.content.Context;
import android.graphics.Color;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.bean.CasalBean;
import com.projeto.intentsimplicitas.bean.QuemEMaisBean;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.componentes.CustomAlertDialog;
import com.projeto.intentsimplicitas.interfaces.Action0;

import java.util.ArrayList;
import java.util.List;

public class Global {
    private static Global instance;

    private CasalBean casalBean;

    private List<CasalBean> lstCasaisBean;

    private List<QuemEMaisBean> lstQuemEMaisBean;



    private static final String DEFAULT_COLOR = "#B80E0E";

    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public int getDefaultColorRed() {
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
        return lstCasaisBean;
    }

    public void setLstCasaisBean(List<CasalBean> lstCasaisBean) {
        this.lstCasaisBean = lstCasaisBean;
    }

    public CasalBean getCasalBean() {
        return casalBean;
    }

    public void setCasalBean(CasalBean casalBean) {
        this.casalBean = casalBean;
    }

    public List<QuemEMaisBean> getLstQuemEMaisBean() {

        return lstQuemEMaisBean;
    }

    public void setLstQuemEMaisBean(List<QuemEMaisBean> lstQuemEMaisBean) {
        this.lstQuemEMaisBean = lstQuemEMaisBean;
    }

    public void initListaCasaisBean(){
        lstCasaisBean = new ArrayList<>();

        lstCasaisBean.add(new CasalBean("Casal de Porcos", R.drawable.porcos, R.drawable.porcoicon));
        lstCasaisBean.add(new CasalBean("Casal de Cavalos",
                R.drawable.cavalos, R.drawable.cavaloicon));
        lstCasaisBean.add(new CasalBean("Casal de Corujas",
                R.drawable.corujas, R.drawable.corujaicon));
        lstCasaisBean.add(new CasalBean("Casal de Ratos",
                R.drawable.ratos, R.drawable.ratoicon));
        lstCasaisBean.add(new CasalBean("Casal de Patos",
                R.drawable.patos, R.drawable.patoicon));
        lstCasaisBean.add(new CasalBean("Casal de Tigres",
                R.drawable.tigres, R.drawable.tigreicon));
    }

    public void initListaQuemEMais(){
        lstQuemEMaisBean = new ArrayList<>();
        lstQuemEMaisBean.add(new QuemEMaisBean(1,"Quem é mais Atencioso?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(2,"Quem é mais Bagunceiro?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(3,"Quem é mais Estressado?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(4,"Quem é mais Engraçado?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(5,"Quem é mais Carinhoso?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(6,"Quem é mais Ciumento?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(7,"Quem é mais Dorminhoco?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(8,"Quem é mais Charmoso?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(9,"Quem é mais Tagarela?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(10,"Quem é mais Indeciso?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(11,"Quem é mais Comilão?"));
        lstQuemEMaisBean.add(new QuemEMaisBean(12,"Quem ama mais?"));
    }

    public void dialogReiniciarProcesso(Context context, String title, String message, Action0 onConfirmResult, Action0 onCanceledResult){
        CustomAlertDialog.create(context).setTitle(title).setMessage(message)
                .setPositiveListener(onConfirmResult).setNegativeListener(onCanceledResult).show();
    }


}
