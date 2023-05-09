package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.button);

        bt.setOnClickListener(v -> {
            trocaTela();
        });
    }

    public void trocaTela(){
        Intent in = new Intent(MainActivity.this, SegundaActivity.class);
        in.putExtra("key", "leozin");
        startActivity(in);

    }
}