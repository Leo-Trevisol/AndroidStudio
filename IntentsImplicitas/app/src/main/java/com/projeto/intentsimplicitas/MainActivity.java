package com.projeto.intentsimplicitas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.bean.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.fragments.ModulosFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CHAVE_EXTRA_CASAL_MAIN_ACTIVITY = "CHAVE_EXTRA_CASAL_MAIN_ACTIVITY";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setTitle("Menu principal");
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        final int mainColor = Global.getInstance().getDefaultColorRed();
        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));

        TextView textCasalTop = findViewById(R.id.text_casal_top);

        TextView textCasalBottom = findViewById(R.id.text_casal_bottom);

        ImageView imageCasal = findViewById(R.id.imageCasal);

        CasalBean casalBean = Global.getInstance().getCasaisBean();

        textCasalTop.setText(casalBean.getPessoa1().toUpperCase() + " e " + casalBean.getPessoa2().toUpperCase());

        textCasalBottom.setText(casalBean.getPessoa1().toUpperCase() + " e " + casalBean.getPessoa2().toUpperCase());

        Drawable drawable = ResourcesCompat.getDrawable(getApplicationContext().getResources(), casalBean.getImagemCasal(), null);

        imageCasal.setBackground(drawable);

        showModulos();

    }
    private void showModulos(){
        ModulosFragment modulosFragment = new ModulosFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, modulosFragment);

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Global.getInstance().dialogReiniciarProcesso(this, "Atenção", "Deseja reiniciar o processo?", () ->{
            setResult(RESULT_CANCELED);
            Intent i = new Intent(this, InitialActivity.class);
             startActivity(i);
        }, null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
           Global.getInstance().initListaQuemEMais();
        }
    }
}