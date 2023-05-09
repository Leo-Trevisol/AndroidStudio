package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda2);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            String valor = extras.getString("key");
            Toast.makeText(this, "Valor: " + valor, Toast.LENGTH_SHORT).show();
        }
    }
}