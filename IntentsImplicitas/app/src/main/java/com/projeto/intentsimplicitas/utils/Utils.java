package com.projeto.intentsimplicitas.utils;

import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.Date;

public class Utils {

    public static Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeHierarchyAdapter(byte[].class, new DataTypeDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer()).create();
    }

    public static boolean isEmpty(String txt) {
        return txt == null || txt.trim().isEmpty();
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(EditText edit) {
        if (edit.getText() == null || edit.getText().toString().trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


}
