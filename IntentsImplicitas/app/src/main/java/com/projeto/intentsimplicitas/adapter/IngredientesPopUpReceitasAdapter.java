package com.projeto.intentsimplicitas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.intentsimplicitas.R;

import java.util.List;

public class IngredientesPopUpReceitasAdapter extends RecyclerView.Adapter<IngredientesPopUpReceitasAdapter.ViewHolder> {

    private Context context;
    private List<String> lstIngredientes;

    public IngredientesPopUpReceitasAdapter(Context context, List<String> lstIngredientes) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_resumo_ingrediente);
        }

        public void bind(String ingrediente, int position) {

            if(position == 0){
                ingrediente = " " + ingrediente;
            }

            textView.setText(ingrediente);
        }
    }
}
