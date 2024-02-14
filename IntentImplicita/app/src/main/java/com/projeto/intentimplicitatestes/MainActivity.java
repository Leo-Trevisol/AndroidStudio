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

        bt.setOnClickListener(v -> {
//            Uri uri = Uri.parse("http://www.youtube.com/watch?v=xGPeNN9S0Fg");
//            Intent pesquisarNoBrowser = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(pesquisarNoBrowser);

            Intent acess = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

            if(acess != null){
                startActivity(acess);
                
            }else{
                Toast.makeText(this, "F LEOZIN", Toast.LENGTH_SHORT).show();
            }
        });
    }
}