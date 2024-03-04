package com.projeto.intentsimplicitas.async;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projeto.intentsimplicitas.async.CustomAsyncTask;
import com.projeto.intentsimplicitas.interfaces.Action1;
import com.projeto.intentsimplicitas.response.ResponseAbstract;
import com.projeto.intentsimplicitas.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.List;

public class ReceitasAsyncTask<T extends ResponseAbstract> extends CustomAsyncTask {
    private final Action1<T> onCompletionListener;
    private final Class<T> tClass;
    private final String signature;
    private boolean showInfo;
    private WeakReference<Context> contextReference;

    public static <R, T extends ResponseAbstract> ReceitasAsyncTask<T> start(Context context, R request, String signature, Class<T> tClass, String messageWait, Action1<T> onCompletionListener) {
        final Gson g =  new Gson();
        final String json = g.toJson(request, request.getClass());

        return new ReceitasAsyncTask<T>(context, json, tClass, signature, messageWait, onCompletionListener);
    }

    private ReceitasAsyncTask(Context context, String json, Class<T> tClass, String signature, String messageWait, Action1<T> onCompletionListener) {
        super(context, json);
        this.tClass = tClass;
        this.signature = signature;
        if ((messageWait == null || messageWait.isEmpty()))
            super.setMenssagemAguarde(messageWait);
        contextReference = new WeakReference<>(context);
        this.onCompletionListener = onCompletionListener;
    }

    public ReceitasAsyncTask showInfo() {
        this.showInfo = true;

        return this;
    }

    public ReceitasAsyncTask avoidShowInterface() {
        super.setAvoidShowInterface(true);

        return this;
    }

    @Override
    public void customOnPostExecute() {
        final Context context = contextReference.get();
        if (this.getConteudoRetorno() != null) {
            final T resp = Utils.getGson().fromJson(this.getConteudoRetorno(), tClass);

            if (!Utils.isEmpty(resp.getListDesErro())) {
                List<String> erros = resp.getListDesErro();
                for (String erro : erros) {
                    Toast.makeText(context, erro, Toast.LENGTH_LONG).show();
                }
                if (onCompletionListener != null) onCompletionListener.call(null);
            } else {
                if (showInfo && !Utils.isEmpty(resp.getListDesInfo())) {
                    final StringBuilder error = new StringBuilder();
                    for (String info : resp.getListDesInfo()) {
                        if (error.length() > 0) error.append("\n");

                        error.append(info);
                    }
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                }

                if (onCompletionListener != null) onCompletionListener.call(resp);
            }

        } else if (this.getRetornoErro() != null && context != null) {
            String erro = getRetornoErro();
            Toast.makeText(context, erro, Toast.LENGTH_LONG).show();
            if (onCompletionListener != null) onCompletionListener.call(null);
        }
    }

    public void execute() {
        execute(signature);
    }
}