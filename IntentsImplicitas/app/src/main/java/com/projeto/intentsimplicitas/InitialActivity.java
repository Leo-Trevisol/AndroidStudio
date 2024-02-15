package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitialActivity extends AppCompatActivity {

    String itemSelecionado = "";

    static final String CHAVE_EXTRA_NOME_INITIAL_ACTIVITY = "CHAVE_EXTRA_NOME_INITIAL_ACTIVITY";
    static final String CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY = "CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        EditText text = findViewById(R.id.edit1);

        Spinner spin = findViewById(R.id.tipo_spin);

        Map<String, Integer> casaisMap = new HashMap<>();

        ArrayList<String> itens = new ArrayList<String>();
       // casaisMap.put("Casal de Porcos" );
        itens.add("Casal de Cavalos");
        itens.add("Casal de Corujas");
        itens.add("Casal de Ratos");
        itens.add("Casal de Patos");
        itens.add("Casal de Tigres");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, itens);
        spin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red)));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String hint = "Selecione uma opção";
        dataAdapter.insert(hint, 0);
        spin.setAdapter(dataAdapter);

        // Define um listener para tratar a seleção
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Ignora a seleção se o hint for selecionado
                if (position == 0) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                } else {
                    // Execute a lógica normalmente para as outras opções
                    itemSelecionado = parentView.getItemAtPosition(position).toString();
                    ((TextView) parentView.getChildAt(0)).setTextColor(getResources().getColor(android.R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Método chamado quando nada está selecionado
            }
        });

        Button bt = findViewById(R.id.bt_confirm);

        bt.setOnClickListener(v -> {
            if(itemSelecionado != null && (!text.getText().toString().isBlank()
                    && !text.getText().toString().isEmpty())){
                Intent i = new Intent(InitialActivity.this, MainActivity.class);
                i.putExtra(CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY, itemSelecionado );
                i.putExtra(CHAVE_EXTRA_NOME_INITIAL_ACTIVITY, text.getText().toString());
                startActivity(i);

            }
        });



    }
}