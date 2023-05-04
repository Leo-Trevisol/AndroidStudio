package com.example.cardslistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String[] listaNomes ={"Naruto", "Jojos", "Haikyuu", "Fullmetal alchemist", "Yuyu Hakusho"};

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listaNomes);

        listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    class viewPersonagem{
        ImageView icone;
        TextView titulo;
        TextView descricao;
    }

    class dadosPersonagem{
        int icone;
        String titulo;
        String descricao;

        public int getIcone() {
            return icone;
        }

        public void setIcone(int icone) {
            this.icone = icone;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }
}