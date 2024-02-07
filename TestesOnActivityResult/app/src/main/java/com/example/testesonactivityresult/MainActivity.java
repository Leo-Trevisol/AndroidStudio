package com.example.testesonactivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int requestCodeSegundaActivity = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.bt);

        bt.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SegundaActivity.class);
            startActivityForResult(i, requestCodeSegundaActivity);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == requestCodeSegundaActivity){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "RESULT OK", Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "RESULT NÃO OK", Toast.LENGTH_SHORT).show();
            }

        }
    }
}