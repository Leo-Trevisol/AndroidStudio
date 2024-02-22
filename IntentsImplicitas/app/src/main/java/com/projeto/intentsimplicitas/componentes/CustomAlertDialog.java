package com.projeto.intentsimplicitas.componentes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.DrawableRes;

import android.os.Build;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.classes.Global;
import com.projeto.intentsimplicitas.interfaces.Action0;


public class CustomAlertDialog {
    private final AlertDialog.Builder builder;
    private TextView messageTextView, titleTextView;
    private Button negativeButton, positiveButton;
    private ImageView iconImageView;
    private AlertDialog presentedDialog;
    private boolean typeSystemAlert;
    private CustomAlertDialog(Context context, boolean typeSystemAlert) {
        builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.RoundedDialog));
        this.typeSystemAlert = typeSystemAlert;


        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialog_custom_positive_negative, null, false);

        builder.setView(viewInflated);

        titleTextView = viewInflated.findViewById(R.id.textView_titleDialog);
        negativeButton = viewInflated.findViewById(R.id.btn_negative);
        positiveButton = viewInflated.findViewById(R.id.btn_positive);
        iconImageView = viewInflated.findViewById(R.id.imageView_iconDialog);
        iconImageView.setBackgroundResource(android.R.drawable.ic_dialog_alert);

        messageTextView = viewInflated.findViewById(R.id.textView_customDialogMessage);
        messageTextView.setVisibility(  View.GONE);

        final int mainColor = Global.getInstance().getDefaultColorRed();
        negativeButton.setTextColor(mainColor);
        positiveButton.setTextColor(mainColor);
    }

    public CustomAlertDialog setTitle(String title) {
        titleTextView.setText(title);

        return this;
    }

    public CustomAlertDialog setPositiveListener(Action0 listener) {
        positiveButton.setOnClickListener(v -> {
            if (listener != null) listener.call();
            presentedDialog.dismiss();
        });

        return this;
    }

    public CustomAlertDialog setNegativeListener(Action0 listener) {
        negativeButton.setOnClickListener(v -> {
            if (listener != null) listener.call();
            presentedDialog.dismiss();
        });

        return this;
    }

    public CustomAlertDialog setCancelable(boolean cancelable) {
        return setCancelable(cancelable, null);
    }

    public CustomAlertDialog setCancelable(boolean cancelable, DialogInterface.OnCancelListener onCancelListener) {
        builder.setCancelable(cancelable);

        if (cancelable && onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }

        return this;
    }

    public CustomAlertDialog setMessage(String message) {
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message);

        return this;
    }

    public CustomAlertDialog setMessageHtml(String message) {
        messageTextView.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            messageTextView.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
        } else {
            messageTextView.setText(Html.fromHtml(message));
        }

        return this;
    }

    public CustomAlertDialog isSingleButton(String title, Action0 listener) {
        positiveButton.setText(title);
        negativeButton.setVisibility(View.GONE);
        setPositiveListener(listener);

        return this;
    }

    public void show() {
        presentedDialog = builder.create();
        if (typeSystemAlert) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                presentedDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            else
                presentedDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        presentedDialog.show();
    }

    public CustomAlertDialog setDescricaoBtnPositive(String descricao){
        positiveButton.setText(descricao);
        return this;
    }

    public CustomAlertDialog setDescricaoBtnNegative(String descricao){
        negativeButton.setText(descricao);
        return this;
    }

    public CustomAlertDialog setIcon(@DrawableRes int resid) {
        iconImageView.setBackgroundResource(resid);
        return  this;
    }

    public void dismiss() {
        presentedDialog.dismiss();
    }

    public static CustomAlertDialog create(Context context) {
        return new CustomAlertDialog(context, false);
    }

    public static CustomAlertDialog create(Context context, boolean typeSystemAlert) {
        return new CustomAlertDialog(context, typeSystemAlert);
    }
}
