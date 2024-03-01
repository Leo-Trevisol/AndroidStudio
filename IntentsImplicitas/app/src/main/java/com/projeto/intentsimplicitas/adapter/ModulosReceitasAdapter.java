package com.projeto.intentsimplicitas.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.utils.ModuloReceitas;

import java.util.List;

public class ModulosReceitasAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final Context context;
    private List<ModuloReceitas> modulos;

    public ModulosReceitasAdapter(List<ModuloReceitas> modulos, Context context) {
        this.modulos = modulos;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return modulos.size();
    }

    @Override
    public Object getItem(int i) {
        return modulos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.modulos_layout, parent, false);
        }

        final ModuloReceitas modulo = modulos.get(i);

        final int mainColor = modulo.isEnabled() ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.colorC);
        final ImageView imageViewModule = convertView.findViewById(R.id.imageView_module);
        imageViewModule.setImageTintList(ColorStateList.valueOf(mainColor));
        imageViewModule.setImageResource(modulo.getResourceId());

        final TextView titleTextView = convertView.findViewById(R.id.textView_module_name);
        titleTextView.setTextColor(mainColor);
        titleTextView.setText(modulo.getTitle());

        ((ImageView) convertView.findViewById(R.id.imageView_setinha)).setImageTintList(ColorStateList.valueOf(mainColor));
        convertView.findViewById(R.id.view_bottom).setBackgroundTintList(ColorStateList.valueOf(mainColor));

//        imageButton.setEnabled(modulo.isEnabled());

        convertView.setOnClickListener(modulo.getOnClickListener());

        return convertView;
    }
}