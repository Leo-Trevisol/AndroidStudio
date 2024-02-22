package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.intentsimplicitas.classes.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.componentes.CustomAlertDialog;
import com.projeto.intentsimplicitas.fragments.ModulosFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CHAVE_EXTRA_CASAL_MAIN_ACTIVITY = "CHAVE_EXTRA_CASAL_MAIN_ACTIVITY";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
       // super.onBackPressed();
        CustomAlertDialog.create(this).setTitle("Atenção").setMessage("Deseja reiniciar o processo?")
                .setPositiveListener(() ->{
                    setResult(RESULT_CANCELED);
                     finish();
                        }).setNegativeListener(null).show();

    }
}