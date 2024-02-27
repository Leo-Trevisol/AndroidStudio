package com.projeto.intentsimplicitas.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.projeto.intentsimplicitas.QuemEMaisActivity;
import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.ReceitasActivity;
import com.projeto.intentsimplicitas.Utils.Modulo;
import com.projeto.intentsimplicitas.Utils.ModulosAdapter;
import com.projeto.intentsimplicitas.bean.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;

import java.util.ArrayList;
import java.util.List;

public class ReceitasFragment extends Fragment {

    public static final String CHAVE_EXTRA_CASAL_MODULOS_FRAGMENT = "CHAVE_EXTRA_CASAL_MODULOS_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextThemeWrapper = new android.view.ContextThemeWrapper(getActivity(), R.style.Base_Theme_IntentsImplicitas);

        View view = inflater.inflate(R.layout.fragment_receitas, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}