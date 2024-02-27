package com.projeto.intentsimplicitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.projeto.intentsimplicitas.classes.Global;

public class ReceitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_receitas);

        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setTitle("Menu principal");
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        final int mainColor = Global.getInstance().getDefaultColorRed();
        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));
    }

    @Override
    public void onBackPressed() {
        Global.getInstance().dialogReiniciarProcesso(this,"Atenação", "Deseja voltar para o menu principal?", () ->{
            setResult(RESULT_CANCELED);
            finish();
        }, null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}