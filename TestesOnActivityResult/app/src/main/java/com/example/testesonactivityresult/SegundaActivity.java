package com.example.testesonactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        if(getIntent().hasExtra(MainActivity.MAIN_ACTIVITY)){
            Toast.makeText(this, "VALOR: " + getIntent().getExtras().getString(MainActivity.MAIN_ACTIVITY), Toast.LENGTH_SHORT).show();
        }

        Button btok = findViewById(R.id.btok);

        btok.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        Button btnok = findViewById(R.id.btnok);

        btnok.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}