package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);

        TextView text = findViewById(R.id.textView2);
        Button bt = findViewById(R.id.button);

        if(getIntent().hasExtra("texto")){
            text.setText(getIntent().getExtras().getString("texto") + "... PODE DEIXAR!!!") ;
        }

        bt.setOnClickListener(v -> {

                Uri uri = Uri.parse("http://www.youtube.com/watch?v=xGPeNN9S0Fg");
                Intent pesquisarNoBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(pesquisarNoBrowser);
            });

    }
}