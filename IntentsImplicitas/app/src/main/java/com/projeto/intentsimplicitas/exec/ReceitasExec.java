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
import com.projeto.intentsimplicitas.interfaces.Action0;
import com.projeto.intentsimplicitas.utils.Utils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class ReceitasExec implements Serializable {

    private static ReceitasExec instance;

    private List<ReceitasResponseBean> lstReceitasSalgadas, lstReceitasDoces, lstReceitasAgridoces;

    private boolean isReceitasAtivas;

    public static ReceitasExec getInstance() {
        if (instance == null) {
            instance = new ReceitasExec();
        }
        return instance;
    }

    public static void setInstance(ReceitasExec instance) {
        ReceitasExec.instance = instance;
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

    public void getLstReceitasTipos(Context context, String tipoReceita, Action0 onCompletionListener, Action0 onCancelListener ){

        if(isLstReceitasCarregadas(tipoReceita)){
            onCompletionListener.call();
            return;
        }

        CustomAsyncTask task = new CustomAsyncTask(context, "") {

            @Override
            public void customOnPostExecute() {

                if (this.getConteudoRetorno() != null && !this.getConteudoRetorno().trim().isEmpty()) {

                    Gson gson = new Gson();
                    Type receitaListType = new TypeToken<List<ReceitasResponseBean>>() {
                    }.getType();
                    List<ReceitasResponseBean> lstReceitas = gson.fromJson(this.getConteudoRetorno(), receitaListType);
                    if (!Utils.isEmpty(lstReceitas)) {

                        addItensListaReceitas(tipoReceita, lstReceitas);

                        onCompletionListener.call();

                    } else {
                        onCancelListener.call();
                    }
                }
            }

        };

        task.execute("https://gold-anemone-wig.cyclic.app/receitas/tipo/"+tipoReceita);
    }

    public void addItensListaReceitas(String tipoReceita, List<ReceitasResponseBean> lstReceitas){

        if(tipoReceita.equals(salgadoKey)){
            setLstReceitasSalgadas(lstReceitas);
        }else if(tipoReceita.equals(doceKey)){
            setLstReceitasDoces(lstReceitas);
        }else if(tipoReceita.equals(agriDoceKey)){
            setLstReceitasAgridoces(lstReceitas);
        }
    }

    public boolean isLstReceitasCarregadas(String tipoReceita){

        if(tipoReceita.equals(salgadoKey)){
            if(Utils.isEmpty(getLstReceitasSalgadas())){
                return false;
            }
        }else if(tipoReceita.equals(doceKey)){

            if(Utils.isEmpty(getLstReceitasDoces())){
                return false;
            }
        }else if(tipoReceita.equals(agriDoceKey)){

            if(Utils.isEmpty(getLstReceitasAgridoces())){
                return false;
            }
        }

        return true;

    }

    public List<ReceitasResponseBean> getLstTipoReceitasInformadas(String tipoReceita){
        if(tipoReceita.equals(salgadoKey)){
           return getLstReceitasSalgadas();
        }else if(tipoReceita.equals(doceKey)){
            return getLstReceitasDoces();
        }else if(tipoReceita.equals(agriDoceKey)){
            return getLstReceitasAgridoces();
        }
        return null;
    }
}
