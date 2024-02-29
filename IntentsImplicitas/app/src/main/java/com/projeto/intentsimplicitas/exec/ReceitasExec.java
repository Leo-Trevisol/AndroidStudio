package com.projeto.intentsimplicitas.exec;

import com.projeto.intentsimplicitas.bean.ReceitasResponseBean;

import java.io.Serializable;

public class ReceitasExec implements Serializable {

    private static ReceitasExec instance;

    ReceitasResponseBean receitasResponseBean;

    public static ReceitasExec getInstance() {
        if (instance == null) {
            instance = new ReceitasExec();
        }
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }


}
