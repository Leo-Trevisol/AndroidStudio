package com.projeto.intentsimplicitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.bean.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.bean.QuemEMaisBean;

import java.util.List;

public class QuemEMaisActivity extends AppCompatActivity {

    TextView textPergunta, textPgs;

    Button btPessoa1, btPessoa2, btMostrarResultados, btProxima;

    ImageView btVoltar;

    int idxPerguntaTela = 0;

    List<QuemEMaisBean> lstQuemEMaisBean = Global.getInstance().getLstQuemEMaisBean();

    QuemEMaisBean currentPergunta;

    CasalBean casalBean = Global.getInstance().getCasalBean();


    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_emais);

        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setTitle("Quem é mais...");
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        final int mainColor = Global.getInstance().getDefaultColorRed();
        getWindow().setStatusBarColor(mainColor);
        supportActionBar.setBackgroundDrawable(new ColorDrawable(mainColor));

        textPergunta = findViewById(R.id.text_pergunta);


        textPgs = findViewById(R.id.text_pgs);

        textPgs.setText("" + (idxPerguntaTela + 1) + "/" + lstQuemEMaisBean.size());

         btPessoa1 = findViewById(R.id.bt_pessoa1);

         btPessoa2 = findViewById(R.id.bt_pessoa2);

        CasalBean casalBean = Global.getInstance().getCasaisBean();


        /*  BOTÕES E AÇÕES  */

        btPessoa1.setText(casalBean.getPessoa1());

        btPessoa1.setOnClickListener(v -> {
            responder(1, btPessoa1.getText().toString());
            updateInformacoes(false, false);
        });

        btPessoa2.setText(casalBean.getPessoa2());

        btPessoa2.setOnClickListener(v -> {
            responder(2, btPessoa2.getText().toString());
            updateInformacoes(false,false);
        });

        btProxima = findViewById(R.id.bt_proxima);

        btProxima.setOnClickListener(v -> {
            updateInformacoes(true, false);
        });

        btVoltar = findViewById(R.id.bt_voltar);
        btVoltar.setColorFilter(getResources().getColor(R.color.white));

        btVoltar.setOnClickListener(v -> {
            updateInformacoes(false, true);
        });

        btMostrarResultados = findViewById(R.id.bt_mostrar_resultados);

        btMostrarResultados.setOnClickListener(v -> {
            Global.getInstance().setLstQuemEMaisBean(lstQuemEMaisBean);
            Intent i = new Intent(this, QuemEMaisResultadoActivity.class);
            startActivity(i);
        });

        currentPergunta = lstQuemEMaisBean.get(0);

        updateInformacoes(false, false);

    }

    public void responder(int escolha, String nome){
        getCurrentPergunta().setEscolha(escolha);
        getCurrentPergunta().setNome(nome);
    }

    public QuemEMaisBean getCurrentPergunta(){
        return lstQuemEMaisBean.get(idxPerguntaTela);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    public void updateInformacoes(boolean proximaPergunta, boolean voltarPergunta){

        if(proximaPergunta){
            idxPerguntaTela++;
        }else if(voltarPergunta){
            idxPerguntaTela--;
        }

        if(getCurrentPergunta().getEscolha() == 0){
            btProxima.setEnabled(false);
        }else{
            btProxima.setEnabled(true);
        }

        if(getCurrentPergunta().getSeq() == 1){
            btVoltar.setEnabled(false);
        }else{
            btVoltar.setEnabled(true);
        }

        if(getCurrentPergunta().getSeq() == (lstQuemEMaisBean.size())) {
            btProxima.setEnabled(false);
            btProxima.setVisibility(View.GONE);
            btMostrarResultados.setVisibility(View.VISIBLE);
            if(getCurrentPergunta().getEscolha() != 0)
                btMostrarResultados.setEnabled(true);
        }else{
            if(getCurrentPergunta().getEscolha() != 0){
                btProxima.setEnabled(true);
            }

            btProxima.setVisibility(View.VISIBLE);
            btMostrarResultados.setVisibility(View.GONE);
        }

        int CORES_BOTOES = 0;

        if(getCurrentPergunta().getEscolha() == 1){
            CORES_BOTOES = 1;
        }else if(getCurrentPergunta().getEscolha() == 2){
            CORES_BOTOES = 2;
        }

        updateCoresBotoes(CORES_BOTOES);
        textPergunta.setText(getCurrentPergunta().getPergunta());

        Drawable iconCasal = ResourcesCompat.getDrawable(getApplicationContext().getResources(), casalBean.getIconCasal(), null);
        Drawable iconCasalInv = mirrorDrawable(iconCasal);
        textPergunta.setCompoundDrawablesWithIntrinsicBounds(iconCasal, null, iconCasalInv, null);
        textPgs.setText("" + (idxPerguntaTela + 1) + "/" + lstQuemEMaisBean.size());
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    public void updateCoresBotoes(int indCor){

        if(indCor == 0){
            btPessoa1.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_red, null));
            btPessoa2.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_red, null));
        } else if (indCor == 1) {
            btPessoa1.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_3, null));
            btPessoa2.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_red, null));
        } else if (indCor == 2) {
            btPessoa1.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_red, null));
            btPessoa2.setBackground(ResourcesCompat.getDrawable(getApplicationContext().getResources(),R.drawable.rounded_button_shadow_3, null));
        }
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

    private Drawable mirrorDrawable(Drawable drawable) {
        // Converte o Drawable para Bitmap
        Bitmap originalBitmap = ((BitmapDrawable) drawable).getBitmap();

        // Cria uma matriz de espelhamento horizontal
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);

        // Cria um novo bitmap espelhado
        Bitmap mirroredBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
                originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);

        // Converte o bitmap espelhado de volta para um Drawable
        return new BitmapDrawable(getResources(), mirroredBitmap);
    }

}