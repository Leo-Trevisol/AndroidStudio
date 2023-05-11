package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private Button button;

    private SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text);
        listView = findViewById(R.id.list);
        button = findViewById(R.id.button);


        button.setOnClickListener(v -> {

            try {

                bancoDados = openOrCreateDatabase("bancoLista", MODE_PRIVATE, null);
                bancoDados.execSQL("CREATE TABLE IF NOT EXISTS minhas_tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

                String tarefa = editText.getText().toString();

                bancoDados.execSQL("INSERT INTO minhas_tarefas (tarefa) VALUES ('" + tarefa + "')");

                Cursor cursor = bancoDados.rawQuery("SELECT * FROM minhas_tarefas", null);

                int idxColunaId = cursor.getColumnIndex("id");
                int idxColunaTarefa = cursor.getColumnIndex("tarefa");

                cursor.moveToFirst();
                while (cursor != null) {
                    Log.i("Log", "id: " + cursor.getString(idxColunaId) + " tarefa: " + cursor.getString(idxColunaTarefa));
                    cursor.moveToNext();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}