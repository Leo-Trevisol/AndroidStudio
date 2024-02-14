package com.projeto.intentimplicitareceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivedIntent = getIntent();
        Uri uri = receivedIntent.getData();
        URL webPage = null;

        if(uri != null){
            try {

                webPage = new URL(uri.getScheme(), uri.getHost(), uri.getPath());


            }catch(Exception e){
                Toast.makeText(this, "ERRO " + e.getMessage() , Toast.LENGTH_SHORT).show(); e.printStackTrace();

            }

            WebView web = findViewById(R.id.web);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl(webPage.toString());
        }
    }
}