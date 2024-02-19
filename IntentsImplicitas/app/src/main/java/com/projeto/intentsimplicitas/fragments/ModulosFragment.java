package com.projeto.intentsimplicitas.fragments;

import static com.projeto.intentsimplicitas.InitialActivity.CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY;
import static com.projeto.intentsimplicitas.InitialActivity.CHAVE_EXTRA_NOME_INITIAL_ACTIVITY;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.projeto.intentsimplicitas.QuemEMaisActivity;
import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.Utils.Modulo;
import com.projeto.intentsimplicitas.Utils.ModulosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModulosFragment extends Fragment {

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

        lstModulos.add( new Modulo(R.drawable.question, "Quem é mais...", v -> openQuemEMaisActivity()));
        gridView.setAdapter(new ModulosAdapter(lstModulos, getActivity()));
    }

    public void openQuemEMaisActivity(){
        Intent i = new Intent(getActivity(), QuemEMaisActivity.class);
             //  i.putExtra(CHAVE_EXTRA_NOME_INITIAL_ACTIVITY, textNome.getText().toString());
             //   i.putExtra(CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY, textAmor.getText().toString());
        startActivity(i);
    }

}