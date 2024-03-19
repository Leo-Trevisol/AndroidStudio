package com.projeto.intentsimplicitas.exec;

import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.agriDoceKey;
import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.doceKey;
import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.salgadoKey;

import android.content.Context;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.async.CustomAsyncTask;
import com.projeto.intentsimplicitas.async.ReceitasAsyncTask;
import com.projeto.intentsimplicitas.bean.IngredientesBaseBean;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.interfaces.Action0;
import com.projeto.intentsimplicitas.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void getLstReceitasTipos(Context context, String tipoReceita, Action0 onCompletionListener) {

        if (isLstReceitasCarregadas(tipoReceita)) {
            onCompletionListener.call();
            return;
        }

        InputStream inputStream = getTipJsonReceita(context, tipoReceita);

        if(inputStream != null) {

            String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();

            try {
                JSONArray jsonArray = new JSONArray(jsonString);

                List<ReceitasResponseBean> lstReturnReceitas = jsonToReceitasResponseBean(jsonArray);

                if (!Utils.isEmpty(lstReturnReceitas)) {
                    addItensListaReceitas(tipoReceita, lstReturnReceitas);
                } else {
                    Toast.makeText(context, "Receitas indisponiveis no momento.", Toast.LENGTH_SHORT).show();
                }

                onCompletionListener.call();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(context, "Receitas indisponiveis no momento.", Toast.LENGTH_SHORT).show();
        }

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

    public InputStream getTipJsonReceita(Context context, String tipoReceita){
        if(tipoReceita.equals(salgadoKey)){
            return context.getResources().openRawResource(R.raw.receitas_salgadas);
        }else if(tipoReceita.equals(doceKey)){
            return context.getResources().openRawResource(R.raw.receitas_doces);
        }else if(tipoReceita.equals(agriDoceKey)){
            return null;
        }
        return null;
    }

    public List<ReceitasResponseBean> jsonToReceitasResponseBean(JSONArray jsonArray) throws JSONException {

        List<ReceitasResponseBean> lstReturn = new ArrayList<>();

        for(int i=0; i <jsonArray.length();i++){

            ReceitasResponseBean receita = new ReceitasResponseBean();
            JSONObject object = jsonArray.getJSONObject(i);
            receita.setReceita(object.getString("receita"));
            receita.setId(object.getInt("id"));
            receita.setTipo(object.getString("tipo"));
            receita.setModo_preparo(object.getString("modo_preparo"));
            receita.setIngredientes(object.getString("ingredientes"));
            receita.setCreated_at(object.getString("created_at"));
            receita.setLink_imagem(object.getString("link_imagem"));

            JSONArray jsonIngredientesArray =  object.getJSONArray("IngredientesBase");

            IngredientesBaseBean ingredientes = new IngredientesBaseBean();

            List<IngredientesBaseBean> lstIngredientes = new ArrayList<>();

            for(int y=0; y <jsonIngredientesArray.length();y++){

                JSONObject objectIngrediente = jsonIngredientesArray.getJSONObject(y);

                lstIngredientes.add(ingredientes);

               ingredientes.setReceita_id(objectIngrediente.getInt("receita_id"));
               ingredientes.setId(objectIngrediente.getInt("id"));
               ingredientes.setCreated_at(objectIngrediente.getString("created_at"));

                JSONArray jsonNomesIngredientes =  objectIngrediente.getJSONArray("nomesIngrediente");

                List<String> lstNomesReceitas = new ArrayList<>();

                for(int t=0; t <jsonNomesIngredientes.length();t++){

                    lstNomesReceitas.add(jsonNomesIngredientes.getString(t));

                }

                ingredientes.setNomesIngrediente(lstNomesReceitas);
            }

            receita.setIngredientesBase(lstIngredientes);

            lstReturn.add(receita);

        }

        return lstReturn;

    }
    public void getLstReceitasTipos(Context context, String tipoReceita, Action0 onCompletionListener, Action0 onCancelListener ){

        if(isLstReceitasCarregadas(tipoReceita)){
            onCompletionListener.call();
            return;
        }

        CustomAsyncTask task = new CustomAsyncTask(context, "", 10000, "Aguarde, por favor...") {

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
}
