package com.example.frags.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.frags.R;
import com.example.frags.fragments.BuscarFragment;
import com.example.frags.fragments.CompromissosFragment;
import com.example.frags.fragments.NovoFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnNovo, btnCompromissos, btnBuscar;
    private NovoFragment novoFragment;
    private CompromissosFragment compromissosFragment;
    private BuscarFragment buscarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovo = findViewById(R.id.btnNovo);
        btnCompromissos = findViewById(R.id.btnCompromissos);
        btnBuscar = findViewById(R.id.btnBuscar);

        compromissosFragment = new CompromissosFragment();
        novoFragment = new NovoFragment();
        buscarFragment = new BuscarFragment();

        trocaFragment(btnCompromissos, compromissosFragment);
        trocaFragment(btnNovo, novoFragment);
        trocaFragment(btnBuscar, buscarFragment);
    }

    public void trocaFragment(Button bt, Fragment fragment){
        bt.setOnClickListener(v -> {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameConteudo, fragment);
            ft.commit();
        });
    }

}