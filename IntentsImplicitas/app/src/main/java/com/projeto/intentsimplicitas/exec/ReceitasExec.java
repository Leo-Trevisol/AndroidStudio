package com.projeto.intentsimplicitas.exec;

import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.agriDoceKey;
import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.doceKey;
import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.salgadoKey;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projeto.intentsimplicitas.async.CustomAsyncTask;
import com.projeto.intentsimplicitas.async.ReceitasAsyncTask;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.utils.Utils;

import java.io.Serializable;
import java.lang.reflect.Type;
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

    public boolean isReceitasAtivas(Context context, String tipoReceita){

        chamarReceitas(context,  tipoReceita.split("Key")[0]);

        if(tipoReceita.equals(salgadoKey)){
            return Utils.isEmpty(getLstReceitasSalgadas());
        }else if (tipoReceita.equals(doceKey)){
            return Utils.isEmpty(getLstReceitasDoces());
        }else if (tipoReceita.equals(agriDoceKey)){
            return Utils.isEmpty(getLstReceitasAgridoces());
        }

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

    public void chamarReceitas(Context context, String tipoReceita){
        CustomAsyncTask task = new CustomAsyncTask(context, "", 20000, "Aguarde a consulta..." ) {

            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null && !this.getConteudoRetorno().trim().isEmpty()) {

                    Gson gson = new Gson();

                    Type receitaListType = new TypeToken<List<ReceitasResponseBean>>() {}.getType();
                    List<ReceitasResponseBean> lstReceitas = gson.fromJson(this.getConteudoRetorno(), receitaListType);
                    if(lstReceitas != null){
                        if(tipoReceita.equals(salgadoKey.split("Key")[0])){
                            lstReceitasSalgadas = lstReceitas;
                        } else if (tipoReceita.equals(doceKey.split("Key")[0])) {
                            lstReceitasDoces = lstReceitas;
                        }else if (tipoReceita.equals(agriDoceKey.split("Key")[0])) {
                            lstReceitasAgridoces = lstReceitas;
                        }
                    }
                }
            }

        };

        task.execute("https://gold-anemone-wig.cyclic.app/receitas/tipo/"+tipoReceita);
    }


}
