package com.projeto.intentsimplicitas.fragments;


import static com.projeto.intentsimplicitas.fragments.ModulosTipoReceitasFragment.RECEITA_ESCOLHIDA_KEY;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.adapter.IngredientesReceitasAdapter;
import com.projeto.intentsimplicitas.adapter.QuemEMaisItemAdapter;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.view.CustomEdgeEffectFactory;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReceitaEscolhidaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Base_Theme_IntentsImplicitas);

        View view = inflater.inflate(R.layout.fragment_receita_escolhida, container, false);

        if(getArguments() != null) {
            ReceitasResponseBean tipoReceita = (ReceitasResponseBean) getArguments().get(RECEITA_ESCOLHIDA_KEY);
            if(tipoReceita != null){
                initComponents(view, tipoReceita);
            }
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initComponents(View view, ReceitasResponseBean receitaEscolhida) {
        TextView textReceita = view.findViewById(R.id.text_nome_receita);
     //   TextView textIngredientes = view.findViewById(R.id.text_ingredientes_receita);
        TextView textModoPreparo = view.findViewById(R.id.text_modo_preparo_receita);

        textReceita.setText(receitaEscolhida.getReceita());
     //   textIngredientes.setText(formatarIngredientesHtml(receitaEscolhida.getIngredientes()));
        textModoPreparo.setText(receitaEscolhida.getModo_preparo());

        ImageView popUp = view.findViewById(R.id.pop_up_receitas);

        popUp.setOnClickListener(v -> {
            chamarPopUpReceitas(receitaEscolhida.getIngredientes());
        });

    }

    private String formatarIngredientesHtml(String receitaEscolhida){
        StringBuilder ingredientesFormatados = new StringBuilder();

        ingredientesFormatados.append("<ul>");

        String[] lstIngredientesSeparados = receitaEscolhida.split(",");

        for(String ingrediente : lstIngredientesSeparados){
            ingredientesFormatados.append("<li>" + ingrediente + "</li>");
        }

        ingredientesFormatados.append("</ul>");

        return Html.fromHtml(ingredientesFormatados.toString()).toString();
    }

    public void chamarPopUpReceitas(String receitaEscolhida){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this.getContext(), R.style.RoundedDialog));
        builder.setTitle("Ingredientes necessários:");

        View viewInflated = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_show_ingredientes, null, false);
        RecyclerView LstIngredientes =  viewInflated.findViewById(R.id.lista_ingredientes);

        String[] lstIngredientesSeparados = receitaEscolhida.split(",");

        IngredientesReceitasAdapter adapter = new IngredientesReceitasAdapter(getContext(), Arrays.asList(lstIngredientesSeparados));

        LstIngredientes.setLayoutManager(new LinearLayoutManager(getContext()));
        LstIngredientes.setAdapter(adapter);

        builder.setView(viewInflated);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}