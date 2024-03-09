package com.projeto.intentsimplicitas.utils;

import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static List<String> splitString(String input) {
        List<String> items = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^,]+\\([^)]+\\)|[^,]+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            items.add(matcher.group().trim());
        }

        return items;
    }


}
