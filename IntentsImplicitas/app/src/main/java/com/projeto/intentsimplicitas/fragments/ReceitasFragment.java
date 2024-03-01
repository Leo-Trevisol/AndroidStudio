package com.projeto.intentsimplicitas.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.utils.Modulo;
import com.projeto.intentsimplicitas.adapter.ModulosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReceitasFragment extends Fragment {

    public static final String TIPO_RECEITA_KEY = "TIPO_RECEITA_KEY";
    public static final String salgadoKey = "salgadoKey";
    public static final String doceKey = "doceKey";
    public static final String agriDoceKey = "agriDoceKey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Base_Theme_IntentsImplicitas);

        View view = inflater.inflate(R.layout.fragment_modulos, container, false);

        chamarModulos(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void chamarModulos(View view) {
        final GridView gridView = view.findViewById(R.id.gridView_modulos);
        List<Modulo> lstModulos = new ArrayList<Modulo>();
        lstModulos.add(new Modulo(R.drawable.salgado, "Salgado", v -> openReceitas(salgadoKey)));
        lstModulos.add(new Modulo(R.drawable.doce, "Doce", v -> openReceitas(doceKey)));
        lstModulos.add(new Modulo(R.drawable.receita, "Agridoce", v -> openReceitas(agriDoceKey)));
        gridView.setAdapter(new ModulosAdapter(lstModulos, getActivity()));
    }

    public void openReceitas(String key) {

        ModulosTipoReceitasFragment receitasFragment = new ModulosTipoReceitasFragment();

        Bundle b = new Bundle();
        b.putString(TIPO_RECEITA_KEY, key);
        receitasFragment.setArguments(b);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, receitasFragment);

        ft.commit();

    }

}