package com.projeto.intentsimplicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

         btVoltar = findViewById(R.id.bt_voltar);

         btAvancar = findViewById(R.id.bt_avancar);

        CasalBean casalBean = Global.getInstance().getCasaisBean();


        /*  BOTÕES E AÇÕES  */

        btPessoa1.setText(casalBean.getPessoa1());

        btPessoa1.setOnClickListener(v -> {
            responder(1, btPessoa1.getText().toString());
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoNaoEscolhida));
        });

        btPessoa2.setText(casalBean.getPessoa2());

        btPessoa2.setOnClickListener(v -> {
            responder(2, btPessoa2.getText().toString());
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoNaoEscolhida));
        });

        btProxima = findViewById(R.id.bt_proxima);

        btProxima.setOnClickListener(v -> {
            proximaPergunta();
        });

        currentPergunta = lstQuemEMaisBean.get(0);

        updateInformacoes();

    }

    public void responder(int escolha, String nome){
        lstQuemEMaisBean.get(idxPerguntaTela).setEscolha(escolha);
        lstQuemEMaisBean.get(idxPerguntaTela).setNome(nome);
    }

    @SuppressLint("SetTextI18n")
    public void proximaPergunta(){
        if(getCurrentPergunta().getEscolha() == 0){
            Toast.makeText(this, "Responda a pergunta em tela para prosseguir!", Toast.LENGTH_SHORT).show();
        }else{
            updateInformacoes();
        }

    }
    public QuemEMaisBean getCurrentPergunta(){
        return lstQuemEMaisBean.get(idxPerguntaTela);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    public void updateInformacoes(){
        if(getCurrentPergunta().getEscolha() == 0){
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.red));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        }else if(getCurrentPergunta().getEscolha() == 1){
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        }else{
            btPessoa2.setBackgroundTintList(getResources().getColorStateList(R.color.opcaoEscolhida));
            btPessoa1.setBackgroundTintList(getResources().getColorStateList(R.color.red));
        }
        textPergunta.setText(getCurrentPergunta().getPergunta());
        textPgs.setText("" + (idxPerguntaTela + 1) + "/" + lstQuemEMaisBean.size());
    }


}