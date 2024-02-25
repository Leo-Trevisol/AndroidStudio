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
import com.projeto.intentsimplicitas.classes.QuemEMaisBean;

import java.util.List;

public class QuemEMaisItemAdapter extends RecyclerView.Adapter<QuemEMaisItemAdapter.ViewHolder> {

    private List<QuemEMaisBean> listaDeDados;
    private Context context;

    public QuemEMaisItemAdapter(Context context, List<QuemEMaisBean> listaDeDados) {
        this.context = context;
        this.listaDeDados = listaDeDados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.quem_e_mais_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuemEMaisBean objeto = listaDeDados.get(position);
        holder.bind(objeto);
    }

    @Override
    public int getItemCount() {
        return listaDeDados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewStart, imageViewEnd;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewStart = itemView.findViewById(R.id.image_view_resumo_item_start);
            imageViewEnd = itemView.findViewById(R.id.image_view_resumo_item_end);
            textView = itemView.findViewById(R.id.text_view_resumo_item);
        }

        public void bind(QuemEMaisBean objeto) {
            this.textView.setText(objeto.getPergunta() + " " + objeto.getNome());

            Drawable drawable = null;

            if (objeto.getEscolha() == 1) {
                drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.redcoracao, null);
            } else {
                drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.bluecoracao, null);
            }

            imageViewStart.setBackground(drawable);
            imageViewEnd.setBackground(drawable);

        }
    }
}
