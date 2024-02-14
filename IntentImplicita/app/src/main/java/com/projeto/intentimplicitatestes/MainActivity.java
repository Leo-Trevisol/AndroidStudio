package com.projeto.intentimplicitatestes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.button);

        bt.setOnClickListener(view -> {
            Intent intent = new Intent("com.example.intentimplicitareceiver");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("data","data string");

            if(intent != null){
                startActivity(intent);
            }else{
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
            }


        });
    }
}