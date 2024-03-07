package com.projeto.intentsimplicitas.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;
import com.projeto.intentsimplicitas.exec.ReceitasExec;
import com.projeto.intentsimplicitas.utils.Modulo;
import com.projeto.intentsimplicitas.adapter.ModulosAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReceitasFragment extends Fragment {

    public static final String TIPO_RECEITA_KEY = "TIPO_RECEITA_KEY";

    final static String MODULOS_TIPO_RECEITAS_FRAGMENT_BACKSTACK = "MODULOS_TIPO_RECEITAS_FRAGMENT_BACKSTACK";
    public static final String salgadoKey = "salgado";
    public static final String doceKey = "doce";
    public static final String agriDoceKey = "agridoce";

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
        lstModulos.add(new Modulo(R.drawable.salgado,  "Salgado", v -> openReceitas(salgadoKey)));
        lstModulos.add(new Modulo(R.drawable.doce, "Doce", v -> openReceitas(doceKey)));
        lstModulos.add(new Modulo(R.drawable.receita,  "Agridoce", v -> openReceitas(agriDoceKey)));
        gridView.setAdapter(new ModulosAdapter(lstModulos, getActivity()));
    }

    public void openReceitas(String key) {

        ReceitasExec.getInstance().getLstReceitasTipos(getActivity(),key, ()-> {

           List<ReceitasResponseBean> receitasResponseBean = ReceitasExec.getInstance().
                    getLstTipoReceitasInformadas(key);

            ModulosTipoReceitasFragment receitasFragment = new ModulosTipoReceitasFragment();

            Bundle b = new Bundle();
            b.putSerializable(TIPO_RECEITA_KEY, (Serializable) receitasResponseBean);
            receitasFragment.setArguments(b);

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.container, receitasFragment);

            ft.addToBackStack(MODULOS_TIPO_RECEITAS_FRAGMENT_BACKSTACK);

            ft.commit();

        }, ()->{

            Toast.makeText(getContext(), "Receitas tipo " + key +" indisponiveis no momento...", Toast.LENGTH_SHORT).show();


        });



    }

}