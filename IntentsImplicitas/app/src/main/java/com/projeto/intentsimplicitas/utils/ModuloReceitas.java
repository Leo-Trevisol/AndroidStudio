package com.projeto.intentsimplicitas.utils;

import android.view.View;

public class ModuloReceitas {

    private final String title;
    private int resourceId;
    private View.OnClickListener onClickListener;
    private boolean enabled;

    private Integer receitaId;

    public ModuloReceitas(int resourceId, String title, View.OnClickListener listener, Integer receitaId) {
        this(resourceId, true, title, listener, receitaId );
    }

    public ModuloReceitas(int resourceId, boolean enabled, String title, View.OnClickListener listener, Integer receitaId) {
        onClickListener = listener;
        this.resourceId = resourceId;
        this.enabled = enabled;
        this.title = title;
        this.receitaId = receitaId;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public int getResourceId() {
        return resourceId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getTitle() {
        return title;
    }
}
