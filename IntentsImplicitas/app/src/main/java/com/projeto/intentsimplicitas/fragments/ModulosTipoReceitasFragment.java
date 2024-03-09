package com.projeto.intentsimplicitas.fragments;

import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.TIPO_RECEITA_KEY;
import static com.projeto.intentsimplicitas.fragments.ReceitasFragment.salgadoKey;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.async.CustomAsyncTask;
import com.projeto.intentsimplicitas.exec.ReceitasExec;
import com.projeto.intentsimplicitas.utils.ModuloReceitas;
import com.projeto.intentsimplicitas.adapter.ModulosReceitasAdapter;
import com.projeto.intentsimplicitas.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ModulosTipoReceitasFragment extends Fragment {

    private List<ReceitasResponseBean> receitasResponseBean;
    public static final String RECEITA_ESCOLHIDA_KEY = "RECEITA_ESCOLHIDA_KEY";

    public static final String RECEITA_ESCOLHIDA_FRAGMENT_BACKSTACK = "RECEITA_ESCOLHIDA_FRAGMENT_BACKSTACK";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Base_Theme_IntentsImplicitas);

        View view = inflater.inflate(R.layout.fragment_modulos, container, false);

        if(getArguments() != null) {
            receitasResponseBean = (List<ReceitasResponseBean>) getArguments().get(TIPO_RECEITA_KEY);

            if(!Utils.isEmpty(receitasResponseBean))
                 chamarModulosReceitas(view);
        }

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void chamarModulosReceitas(View view) {
        final GridView gridView = view.findViewById(R.id.gridView_modulos);
        List<ModuloReceitas> lstModulos = new ArrayList<ModuloReceitas>();
        for(ReceitasResponseBean receita : receitasResponseBean){
            lstModulos.add(new ModuloReceitas(receita.getTipo().equals(salgadoKey) ? R.drawable.salgado
                    : R.drawable.doce, receita.getReceita(), v->chamarReceita(receita), receita.getId()));
        }
        gridView.setAdapter(new ModulosReceitasAdapter(lstModulos, getActivity()));
    }

    private void chamarReceita(ReceitasResponseBean receitaEscolhida){

        ReceitaEscolhidaFragment receitasFragment = new ReceitaEscolhidaFragment();

        Bundle b = new Bundle();
        b.putSerializable(RECEITA_ESCOLHIDA_KEY, receitaEscolhida);
        receitasFragment.setArguments(b);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, receitasFragment);

        ft.addToBackStack(RECEITA_ESCOLHIDA_FRAGMENT_BACKSTACK);

        ft.commit();
    }


}

