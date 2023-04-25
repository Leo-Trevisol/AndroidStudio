package com.example.frags.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frags.R;


public class CompromissosFragment extends Fragment {

    private TextView text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compromissos, container, false);

        text = view.findViewById(R.id.txtCompromissos);
        text.setText("MUDANCA DE TEXTO ALOU");

        return view;
    }
}