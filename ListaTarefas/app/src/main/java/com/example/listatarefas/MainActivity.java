package com.example.listatarefas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private Button button;

    private ArrayList<String> lstTarefas;
    private ArrayList<Integer> lstIds;

    private ArrayAdapter lstAdapter;

    private SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text);
        listView = findViewById(R.id.list);
        button = findViewById(R.id.button);

        carregarTarefas();

        button.setOnClickListener(v -> {
            adicionarTarefa(editText.getText().toString());
            editText.setText("");
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                alertDeletar(lstIds.get(position));
                return false;
            }
        });

    }



    public void adicionarTarefa(String tarefa){
        try{
        if (tarefa.isEmpty()){
                Toast.makeText(this, "Insira uma tarefa!", Toast.LENGTH_SHORT).show();
            }else{
            Toast.makeText(this, "Tarefa inserida!", Toast.LENGTH_SHORT).show();
            bancoDados.execSQL("INSERT INTO minhas_tarefas (tarefa) values ('" + tarefa +"')");
            carregarTarefas();
            }

        }catch(Exception e){
            e.printStackTrace();

        }

    }

    public void carregarTarefas(){

            try {

                bancoDados = openOrCreateDatabase("bancoLista", MODE_PRIVATE, null);
                bancoDados.execSQL("DROP TABLE IF  EXISTS minhas_tarefas");
                bancoDados.execSQL("CREATE TABLE IF NOT EXISTS minhas_tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

                Cursor cursor = bancoDados.rawQuery("SELECT * FROM minhas_tarefas ORDER BY id DESC", null);

                int idxColunaId = cursor.getColumnIndex("id");
                int idxColunaTarefa = cursor.getColumnIndex("tarefa");

                lstIds = new ArrayList<>();
                lstTarefas = new ArrayList<>();
                lstAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_2,
                        android.R.id.text1,
                        lstTarefas);

                listView.setAdapter(lstAdapter);




                cursor.moveToFirst();
                while (cursor != null) {
                    Log.i("Log", "id: " + cursor.getString(idxColunaId) + " tarefa: " + cursor.getString(idxColunaTarefa));
                    lstIds.add(Integer.parseInt(cursor.getString((idxColunaId))));
                    lstTarefas.add(cursor.getString(idxColunaTarefa));
                    cursor.moveToNext();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    public void apagarTarefa(int id){

        bancoDados.execSQL("DELETE FROM minhas_tarefas WHERE id = ('" +id+"')");
        carregarTarefas();

    }

    public void alertDeletar(int id){
        String tarefaSelected = lstTarefas.get(id);
        final Integer idFinal = id;

        new AlertDialog.Builder(MainActivity.this).setTitle("Aviso!")
                .setMessage("Desej apagar a tarefa: " + tarefaSelected + "?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apagarTarefa(idFinal);
                    }
                }).setNegativeButton("Nao", null);

    }
}