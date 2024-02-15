package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button bt = findViewById(R.id.button);

    EditText texto = findViewById(R.id.edit);

    TextView text = findViewById(R.id.textView);

    if(getIntent().getExtras() != null){
        String nome = getIntent().getExtras().getString(InitialActivity.CHAVE_EXTRA_NOME_INITIAL_ACTIVITY);
        String casal = getIntent().getExtras().getString(InitialActivity.CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY);

        text.setText("EU TE AMO " + nome + "!!!");


    }

    bt.setOnClickListener(v -> {
        if (texto.getText().toString().equals("")){
            Toast.makeText(this, "DIGITE ALGO MEU AMIGO!", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(MainActivity.this, FotosActivity.class);
            i.putExtra("texto", texto.getText().toString());
            startActivity(i);
        }
    });

    }
}