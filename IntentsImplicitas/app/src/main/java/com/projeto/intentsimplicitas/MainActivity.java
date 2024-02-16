package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.intentsimplicitas.classes.CasaisBean;
import com.projeto.intentsimplicitas.classes.Teste;
import com.projeto.intentsimplicitas.fragments.ModulosFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    TextView textCasalTop = findViewById(R.id.text_casal_top);

    TextView textCasalBottom = findViewById(R.id.text_casal_bottom);

    ImageView imageCasal = findViewById(R.id.imageCasal);

    if(getIntent().getExtras() != null){

        String nome = getIntent().getExtras().getString(InitialActivity.CHAVE_EXTRA_NOME_INITIAL_ACTIVITY);
        String amor = getIntent().getExtras().getString(InitialActivity.CHAVE_EXTRA_AMOR_INITIAL_ACTIVITY);
        CasaisBean casalBean = (CasaisBean )getIntent().getExtras().get(InitialActivity.CHAVE_EXTRA_CASAL_INITIAL_ACTIVITY);

        textCasalTop.setText(nome.toUpperCase() + " e " + amor.toUpperCase());

        textCasalBottom.setText(nome.toUpperCase() + " e " + amor.toUpperCase());

        Drawable drawable = ResourcesCompat.getDrawable(getApplicationContext().getResources(), casalBean.getImagemCasal(), null);

        imageCasal.setBackground(drawable);
    }

    showModulos();

    }
    private void showModulos(){
        ModulosFragment modulosFragment = new ModulosFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, modulosFragment);

        ft.commit();
    }
}