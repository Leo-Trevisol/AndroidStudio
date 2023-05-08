package com.example.cardslistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        adapter = new ClassAdapter(getApplicationContext(), R.layout.card);

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

                criarDialog(dadosPersonagem);
            }
        });
    }

    static class ViewPersonagem{
        ImageView icone;
        TextView titulo;
        TextView descricao;
    }

    public void criarDialog(DadosPersonagem dadosPersonagem){
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(MainActivity.this);

        alert.setTitle(dadosPersonagem.getTitulo());
        alert.setMessage(dadosPersonagem.getDescricao());
        alert.setCancelable(true);
        alert.setIcon(dadosPersonagem.getIcone());
        alert.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Confirmado!", Toast.LENGTH_SHORT).show();
            }
        });

        alert.create();
        alert.show();

    }
}