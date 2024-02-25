package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.projeto.intentsimplicitas.adapter.QuemEMaisItemAdapter;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.view.CustomEdgeEffectFactory;
import com.projeto.intentsimplicitas.view.RecyclerViewQuemEMaisResumo;

public class QuemEMaisResultadoActivity extends AppCompatActivity {

    RecyclerViewQuemEMaisResumo lstRecyclerViewQuemEMaisResumo;

    Button btVoltar, btVoltarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.DialogResultadoQuemEMais);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_emais_resultado);
        this.initComponents();
        this.setFinishOnTouchOutside(false);
    }

    public void initComponents(){

        btVoltar = findViewById(R.id.bt_voltar);

        btVoltar.setOnClickListener(v -> {
            finish();
        });

        btVoltarMenu = findViewById(R.id.bt_voltar_menu);

        btVoltarMenu.setOnClickListener(v -> {
            Global.getInstance().initListaQuemEMais();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        lstRecyclerViewQuemEMaisResumo = findViewById(R.id.lst_resumo);

        this.lstRecyclerViewQuemEMaisResumo.setEdgeEffectFactory(new CustomEdgeEffectFactory());
        this.lstRecyclerViewQuemEMaisResumo.setNestedScrollingEnabled(true);
        this.lstRecyclerViewQuemEMaisResumo.setHasFixedSize(true);
        this.lstRecyclerViewQuemEMaisResumo.setLayoutManager(new GridLayoutManager(this, 1));

        final QuemEMaisItemAdapter adapter = new QuemEMaisItemAdapter(this, Global.getInstance().getLstQuemEMaisBean());

        this.lstRecyclerViewQuemEMaisResumo.setAdapter(adapter);

    }
}