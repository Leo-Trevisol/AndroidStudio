package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.intentsimplicitas.classes.CasalBean;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.classes.QuemEMaisBean;

import java.util.List;

public class QuemEMaisActivity extends AppCompatActivity {

    TextView textPergunta, textPgs;

    Button btPessoa1, btPessoa2, btContinuar, btProxima;

    ImageView btVoltar, btAvancar;

    int idxPerguntaTela = 0;

    List<QuemEMaisBean> lstQuemEMaisBean = Global.getInstance().getLstQuemEMaisBean();

    QuemEMaisBean currentPergunta;


    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_emais);

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

        btVoltar.setOnClickListener(v -> {
            updateInformacoes(false, true);
        });

        btAvancar = findViewById(R.id.bt_avancar);

        btAvancar.setOnClickListener(v -> {
            updateInformacoes(true, false);
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

        if(getCurrentPergunta().getSeq() == (lstQuemEMaisBean.size() + 1)) {
            btProxima.setEnabled(false);
            btAvancar.setEnabled(false);
        }else{
            if(getCurrentPergunta().getEscolha() != 0){
                btProxima.setEnabled(true);
                btAvancar.setEnabled(true);
            }
        }

        int CORES_BOTOES = 0;

        if(getCurrentPergunta().getEscolha() == 1){
            CORES_BOTOES = 1;
        }else if(getCurrentPergunta().getEscolha() == 2){
            CORES_BOTOES = 2;
        }

        updateCoresBotoes(CORES_BOTOES);
        textPergunta.setText(getCurrentPergunta().getPergunta());
        textPgs.setText("" + (idxPerguntaTela + 1) + "/" + lstQuemEMaisBean.size());
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    public void updateCoresBotoes(int indCor){

        if(indCor == 0){
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.red));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        } else if (indCor == 1) {
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        } else if (indCor == 2) {
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.red));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
        }
    }
}