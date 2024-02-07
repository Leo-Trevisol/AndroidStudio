package com.example.testesonactivityresult;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

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
}