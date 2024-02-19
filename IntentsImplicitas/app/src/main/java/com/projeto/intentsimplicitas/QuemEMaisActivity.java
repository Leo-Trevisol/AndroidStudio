package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuemEMaisActivity extends AppCompatActivity {

    TextView textPergunta;

    Button btPessoa1, btPessoa2, btContinuar;

    ImageView btVoltar, btAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_emais);

        textPergunta = findViewById(R.id.text_pergunta);

        Button btPessoa1 = findViewById(R.id.bt_pessoa1);

        Button btPessoa2 = findViewById(R.id.bt_pessoa2);

        Button btProxima = findViewById(R.id.bt_proxima);

        ImageView btVoltar = findViewById(R.id.bt_voltar);

        ImageView btAvancar = findViewById(R.id.bt_avancar);



    }
}