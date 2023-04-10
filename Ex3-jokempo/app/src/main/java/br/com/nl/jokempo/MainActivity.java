package br.com.nl.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1, jogador2;
    ImageButton pedra, papel, tesoura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogador1 = findViewById(R.id.j1);
        jogador2 = findViewById(R.id.j2);
        pedra = findViewById(R.id.btpedra);
        tesoura = findViewById(R.id.bttesoura);
        papel = findViewById(R.id.btpapel);

        pedra.setOnClickListener(v -> {
            tocouBotao(pedra);
        });

        papel.setOnClickListener(v -> {
            tocouBotao(papel);

        });

        tesoura.setOnClickListener(v -> {
            tocouBotao(tesoura);

        });

    }

    public void tocouBotao(View view){
        switch (view.getId()){
            case(R.id.btpedra) :
                jogador1.setImageResource(R.drawable.pedra);
                break;
            case(R.id.btpapel) :
                jogador1.setImageResource(R.drawable.papel);
                break;
            case(R.id.bttesoura) :
                jogador1.setImageResource(R.drawable.tesoura);
                break;
        }
    }
}