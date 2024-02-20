package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.classes.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;

public class QuemEMaisActivity extends AppCompatActivity {

    TextView textPergunta;

    Button btPessoa1, btPessoa2, btContinuar, btProxima;

    ImageView btVoltar, btAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_emais);

        textPergunta = findViewById(R.id.text_pergunta);

         btPessoa1 = findViewById(R.id.bt_pessoa1);

         btPessoa2 = findViewById(R.id.bt_pessoa2);

         btProxima = findViewById(R.id.bt_proxima);

         btVoltar = findViewById(R.id.bt_voltar);

         btAvancar = findViewById(R.id.bt_avancar);


            CasalBean casalBean = Global.getInstance().getCasaisBean();

            btPessoa1.setText(casalBean.getPessoa1());

            btPessoa2.setText(casalBean.getPessoa2());

    }
}