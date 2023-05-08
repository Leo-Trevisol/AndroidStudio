package com.example.cardslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

 class ClassAdapter extends ArrayAdapter {
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
        MainActivity.ViewPersonagem viewPersonsagem;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card,parent,false);


            viewPersonsagem = new MainActivity.ViewPersonagem();
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