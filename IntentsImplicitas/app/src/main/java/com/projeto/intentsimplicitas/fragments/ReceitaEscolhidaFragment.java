package com.projeto.intentsimplicitas.fragments;


import static com.projeto.intentsimplicitas.fragments.ModulosTipoReceitasFragment.RECEITA_ESCOLHIDA_KEY;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.adapter.IngredientesPopUpReceitasAdapter;
import com.projeto.intentsimplicitas.adapter.IngredientesReceitaAdapter;
import com.projeto.intentsimplicitas.bean.ModoPreparoBean;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.utils.Utils;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReceitaEscolhidaFragment extends Fragment {

    ExpandableListView expandableListViewIngredientes, expandableListViewModoPreparo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Base_Theme_IntentsImplicitas);

        View view = inflater.inflate(R.layout.receita, container, false);

        if(getArguments() != null) {
            ReceitasResponseBean tipoReceita = (ReceitasResponseBean) getArguments().get(RECEITA_ESCOLHIDA_KEY);
            if(tipoReceita != null){
                initComponents(view, tipoReceita);

                expandableListViewIngredientes = view.findViewById(R.id.expandableListView_ingredientes_necessarios);

                List<String> nomeIngredientesGroup = tipoReceita.getIngredientesBase().get(0).getNomesIngrediente();

                List<String>  lstIngredientesSeparadosChild = Utils.splitStringVirgula(tipoReceita.getIngredientes());

                IngredientesReceitaAdapter adapter = new IngredientesReceitaAdapter(getContext(), nomeIngredientesGroup, lstIngredientesSeparadosChild);
                expandableListViewIngredientes.setAdapter(adapter);

                expandableListViewModoPreparo = view.findViewById(R.id.expandableListView_modo_preparo);

                ModoPreparoBean modoPreparoBean =  Utils.splitStringModoPreparo(tipoReceita.getModo_preparo());

                if((!Utils.isEmpty(modoPreparoBean.getPasso()) && !Utils.isEmpty(modoPreparoBean.getDescricao()))){

                IngredientesReceitaAdapter adapterModoPreparo = new IngredientesReceitaAdapter(getContext(), modoPreparoBean.getPasso(), modoPreparoBean.getDescricao());
                    expandableListViewModoPreparo.setAdapter(adapterModoPreparo);

                }

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
     //   TextView textModoPreparo = view.findViewById(R.id.text_modo_preparo_receita);

        textReceita.setText(receitaEscolhida.getReceita());
       // textModoPreparo.setText(receitaEscolhida.getModo_preparo());

        ImageView popUp = view.findViewById(R.id.pop_up_receitas);

        popUp.setOnClickListener(v -> {
            chamarPopUpReceitas(receitaEscolhida.getIngredientes());
        });

        ImageView eyeOpenIngredientes = view.findViewById(R.id.eye_open_ingredientes);

        ImageView eyeClosedIngredientes = view.findViewById(R.id.eye_closed_ingredientes);

        ImageView eyeOpenPreparo = view.findViewById(R.id.eye_open_preparo);

        ImageView eyeClosedPreparo = view.findViewById(R.id.eye_closed_preparo);

        LinearLayout layoutExpandIngredientes = view.findViewById(R.id.linear2);

        LinearLayout layoutExpandModoPreparo = view.findViewById(R.id.linear4);

        eyeOpenIngredientes.setOnClickListener(v -> {
            eyeClosedIngredientes.setVisibility(View.VISIBLE);
            eyeOpenIngredientes.setVisibility(View.GONE);
            layoutExpandIngredientes.setVisibility(View.GONE);

        });

        eyeClosedIngredientes.setOnClickListener(v -> {
            eyeOpenIngredientes.setVisibility(View.VISIBLE);
            eyeClosedIngredientes.setVisibility(View.GONE);
            layoutExpandIngredientes.setVisibility(View.VISIBLE);
        });

        eyeOpenPreparo.setOnClickListener(v -> {
            eyeClosedPreparo.setVisibility(View.VISIBLE);
            eyeOpenPreparo.setVisibility(View.GONE);
            layoutExpandModoPreparo.setVisibility(View.INVISIBLE);

        });

        eyeClosedPreparo.setOnClickListener(v -> {
            eyeOpenPreparo.setVisibility(View.VISIBLE);
            eyeClosedPreparo.setVisibility(View.GONE);
            layoutExpandModoPreparo.setVisibility(View.VISIBLE);
        });

    }

    public void chamarPopUpReceitas(String receitaEscolhida){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this.getContext(), R.style.RoundedDialog));
        builder.setTitle("Ingredientes necessários:");

        View viewInflated = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_show_ingredientes, null, false);
        RecyclerView LstIngredientes =  viewInflated.findViewById(R.id.lista_ingredientes);

        String[] lstIngredientesSeparados = receitaEscolhida.split(",");

        IngredientesPopUpReceitasAdapter adapter = new IngredientesPopUpReceitasAdapter(getContext(), Arrays.asList(lstIngredientesSeparados));

        LstIngredientes.setLayoutManager(new LinearLayoutManager(getContext()));
        LstIngredientes.setAdapter(adapter);

        builder.setView(viewInflated);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

}