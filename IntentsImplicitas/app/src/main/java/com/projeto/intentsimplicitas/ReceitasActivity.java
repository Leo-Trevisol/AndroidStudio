package com.projeto.intentsimplicitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.fragments.ModulosFragment;
import com.projeto.intentsimplicitas.fragments.ReceitasFragment;

public class ReceitasActivity extends AppCompatActivity {

    final static String RECEITAS_FRAGMENT_BACKSTACK = "RECEITAS_FRAGMENT_BACKSTACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_receitas);

        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setTitle("Receitas");
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        final int mainColor = Global.getInstance().getDefaultColorRed();
        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));

        chamarFragment();

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            Global.getInstance().dialogReiniciarProcesso(this,"Atenção", "Deseja voltar para o menu principal?", () ->{
                setResult(RESULT_CANCELED);
                finish();
            }, null);
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void chamarFragment(){
        ReceitasFragment receitasFragment = new ReceitasFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, receitasFragment);
        ft.addToBackStack(RECEITAS_FRAGMENT_BACKSTACK);

        ft.commit();
    }

}