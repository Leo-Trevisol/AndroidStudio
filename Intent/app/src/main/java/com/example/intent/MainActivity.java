package com.example.intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String[] listaNomes ={"Naruto", "Jojos Bizare Adventure", "Haikyuu",
            "Fullmetal Alchemist", "Yuyu Hakusho"};

    private final int[] listaImagens ={R.drawable.nruto, R.drawable.jojo,
            R.drawable.haikyuu, R.drawable.fullmetal, R.drawable.yuyuhakusho,};

    private final String[] listaDescricao ={"Naruto", "Jojos", "Haikyuu", "Fullmetal", "Yuyu Hakusho"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//FORMA SIMPLES
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, listaNomes);
//
//        listView.setAdapter(adapter);


        ListView listView = findViewById(R.id.list);

        ClassAdapter adapter;
        adapter = new ClassAdapter(getApplicationContext(), R.layout.celula);

        for(int i = 0; i < listaNomes.length; i++){
            DadosPersonagem dadosPersonagem;
            dadosPersonagem = new DadosPersonagem(listaImagens[i], listaNomes[i], listaDescricao[i]);
            adapter.add(dadosPersonagem);
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosPersonagem dadosPersonagem;
                dadosPersonagem = (DadosPersonagem) adapter.getItem(position);

                Intent in = new Intent(MainActivity.this, SegundaActivity.class);
                in.putExtra("titulo", dadosPersonagem.getTitulo());
                in.putExtra("icone", dadosPersonagem.getIcone());
                in.putExtra("descricao", dadosPersonagem.getDescricao());
                startActivity(in);

            }
        });
    }

    static class ViewPersonagem{
        ImageView icone;
        TextView titulo;

    }


    public void trocaTela(){
        Intent in = new Intent(MainActivity.this, SegundaActivity.class);
        in.putExtra("key", "leozin");
        startActivity(in);

    }
}