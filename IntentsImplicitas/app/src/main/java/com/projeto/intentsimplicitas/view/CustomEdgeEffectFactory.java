package com.projeto.intentsimplicitas.view;

import android.widget.EdgeEffect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.intentsimplicitas.classes.Global;

public class CustomEdgeEffectFactory extends RecyclerView.EdgeEffectFactory {
    @NonNull
    @Override
    protected EdgeEffect createEdgeEffect(RecyclerView view, int direction) {
        final EdgeEffect edgeEffect = super.createEdgeEffect(view, direction);
        edgeEffect.setColor(Global.getInstance().getDefaultColorRed());

        return edgeEffect;
    }
}