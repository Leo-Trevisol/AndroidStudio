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

import com.projeto.intentsimplicitas.classes.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;

import java.util.ArrayList;
import java.util.List;

public class InitialActivity extends AppCompatActivity {

    public EditText textNome, textAmor;

    public Spinner spin;
    CasalBean itemSelecionado = null;
    public static final String CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY = "CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY";

    public List<CasalBean> lstCasalBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

         textNome = findViewById(R.id.edit1);
         textNome.setHighlightColor(getResources().getColor(R.color.red));

         textAmor = findViewById(R.id.edit2);

         spin = findViewById(R.id.tipo_spin);

        lstCasalBean = Global.getInstance().getLstCasaisBean();

        ArrayAdapter<CasalBean> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, lstCasalBean);
        spin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red)));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String hint = "Selecione uma opção";
        dataAdapter.insert(new CasalBean(hint, 0), 0);
        spin.setAdapter(dataAdapter);

        // Define um listener para tratar a seleção
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Ignora a seleção se o hint for selecionado
                if (position == 0) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                    itemSelecionado = null;
                } else {
                    // Execute a lógica normalmente para as outras opções
                    itemSelecionado = (CasalBean) parentView.getItemAtPosition(position);
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
            if((itemSelecionado != null && itemSelecionado.getNomeCasal() != null ) && (!textNome.getText().toString().isBlank()
                    && !textNome.getText().toString().isEmpty()) && (!textAmor.getText().toString().isBlank()
                    && !textAmor.getText().toString().isEmpty())){

                itemSelecionado.setPessoa1(textNome.getText().toString());
                itemSelecionado.setPessoa2(textAmor.getText().toString());

                Global.getInstance().setCasaisBean(itemSelecionado);

                Intent i = new Intent(InitialActivity.this, MainActivity.class);
                i.putExtra(CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY, itemSelecionado);

                startActivity(i);
            }else{
                Toast.makeText(InitialActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}