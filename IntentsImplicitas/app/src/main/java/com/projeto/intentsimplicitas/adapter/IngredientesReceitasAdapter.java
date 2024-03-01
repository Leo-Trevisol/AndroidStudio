package com.projeto.intentsimplicitas.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.bean.QuemEMaisBean;

import java.util.List;

public class IngredientesReceitasAdapter extends RecyclerView.Adapter<IngredientesReceitasAdapter.ViewHolder> {

    private Context context;
    private List<String> lstIngredientes;

    public IngredientesReceitasAdapter(Context context, List<String> lstIngredientes) {
        this.context = context;
        this.lstIngredientes = lstIngredientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.ingrediente_dialog_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingrediente = lstIngredientes.get(position);
        holder.bind(ingrediente, position);
    }

    @Override
    public int getItemCount() {
        return lstIngredientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_resumo_ingrediente);
        }

        public void bind(String ingrediente, int position) {


            // Adiciona o texto do ingrediente
            textView.setText(ingrediente);
        }
    }
}
