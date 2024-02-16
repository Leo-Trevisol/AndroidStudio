package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.intentsimplicitas.classes.CasaisBean;
import com.projeto.intentsimplicitas.classes.Teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialActivity extends AppCompatActivity {

    public EditText textNome, textAmor;

    public Spinner spin;
    CasaisBean itemSelecionado = null;
    static final String CHAVE_EXTRA_NOME_INITIAL_ACTIVITY = "CHAVE_EXTRA_NOME_INITIAL_ACTIVITY";

    static final String CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY = "CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY";
    static final String CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY = "CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY";

    public List<CasaisBean> lstCasaisBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

         textNome = findViewById(R.id.edit1);
         textNome.setHighlightColor(getResources().getColor(R.color.red));

         textAmor = findViewById(R.id.edit2);

         spin = findViewById(R.id.tipo_spin);

        lstCasaisBean = new ArrayList<>();

        lstCasaisBean.add(new CasaisBean("Casal de Porcos", R.drawable.porcos));
        lstCasaisBean.add(new CasaisBean("Casal de Cavalos",
                R.drawable.cavalos));
        lstCasaisBean.add(new CasaisBean("Casal de Corujas",
                R.drawable.corujas));
        lstCasaisBean.add(new CasaisBean("Casal de Ratos",
                R.drawable.ratos));
        lstCasaisBean.add(new CasaisBean("Casal de Patos",
                R.drawable.patos));
        lstCasaisBean.add(new CasaisBean("Casal de Tigres",
                R.drawable.tigres));

        ArrayAdapter<CasaisBean> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, lstCasaisBean);
        spin.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red)));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String hint = "Selecione uma opção";
        dataAdapter.insert(new CasaisBean(hint, 0), 0);
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
                    itemSelecionado = (CasaisBean) parentView.getItemAtPosition(position);
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

                Intent i = new Intent(InitialActivity.this, MainActivity.class);
                i.putExtra(CHAVE_EXTRA_NOME_INITIAL_ACTIVITY, textNome.getText().toString());
                i.putExtra(CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY, textAmor.getText().toString());
                i.putExtra(CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY, itemSelecionado);

                startActivity(i);
            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}