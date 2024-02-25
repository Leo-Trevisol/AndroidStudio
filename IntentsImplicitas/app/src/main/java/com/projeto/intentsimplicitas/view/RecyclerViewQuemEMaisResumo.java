package com.projeto.intentsimplicitas.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewQuemEMaisResumo extends RecyclerView {
    public RecyclerViewQuemEMaisResumo(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewQuemEMaisResumo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewQuemEMaisResumo(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
    }
}
