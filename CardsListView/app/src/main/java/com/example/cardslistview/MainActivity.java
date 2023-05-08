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

    private final String[] listaNomes ={"Naruto", "Jojos", "Haikyuu", "Fullmetal alchemist", "Yuyu Hakusho"};

    private final int[] listaImagens ={R.drawable.cards_foreground, R.drawable.cards_foreground,
            R.drawable.cards_foreground, R.drawable.cards_foreground, R.drawable.cards_foreground};

    private final String[] listaDescricao ={"Naruto", "Jojos", "Haikyuu", "Fullmetal alchemist", "Yuyu Hakusho"};

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//FORMA SIMPLES
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, listaNomes);
//
//        listView.setAdapter(adapter);


        listView = findViewById(R.id.list);

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

    static class DadosPersonagem{
        int icone;
        String titulo;
        String descricao;

        public DadosPersonagem(int icone, String titulo, String descricao) {
            this.icone = icone;
            this.titulo = titulo;
            this.descricao = descricao;
        }

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

    static class ClassAdapter extends ArrayAdapter{
        public ClassAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }

        @Override
        public void add(@Nullable Object object) {
            super.add(object);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view;
            view = convertView;
            ViewPersonagem viewPersonsagem;

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) this.getContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.card,parent,false);


                viewPersonsagem = new ViewPersonagem();
                viewPersonsagem.icone = (ImageView) view.findViewById(R.id.icone);
                viewPersonsagem.titulo = (TextView) view.findViewById(R.id.titulo);
                viewPersonsagem.descricao = (TextView) view.findViewById(R.id.descricao);

                view.setTag(viewPersonsagem);

                DadosPersonagem dadosPersonagem;
                dadosPersonagem = (DadosPersonagem) this.getItem(position);
                viewPersonsagem.icone.setImageResource(dadosPersonagem.getIcone());
                viewPersonsagem.titulo.setText(dadosPersonagem.getTitulo());
                viewPersonsagem.descricao.setText(dadosPersonagem.getDescricao());
            }


            return view;
        }
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