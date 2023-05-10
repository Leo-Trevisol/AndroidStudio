package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    private TextView titulo, descricao;
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda2);

        titulo = findViewById(R.id.titulo);
        descricao = findViewById(R.id.descricao);
        icone = findViewById(R.id.icone);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            titulo.setText(extras.getString("titulo"));
            descricao.setText(extras.getString("descricao"));
            icone.setImageResource(extras.getInt("icone"));

        }
    }
}