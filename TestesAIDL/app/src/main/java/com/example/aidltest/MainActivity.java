package com.example.aidltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    IAdditionService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentService = new Intent(this, AdditionService.class);
        bindService(intentService,mConnection, Context.BIND_AUTO_CREATE);

        Button bt = findViewById(R.id.button);

        bt.setOnClickListener(view -> {
            add();
        });
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = IAdditionService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void add(){
        EditText edit1 = findViewById(R.id.edit1);
        EditText edit2 = findViewById(R.id.edit2);

        int val1 = Integer.parseInt(edit1.getText().toString());
        int val2 = Integer.parseInt(edit2.getText().toString());

        int result = 0;

        try{
            result = mService.add(val1,val2);
        }catch(Exception e){
            e.printStackTrace();
        }
        TextView textView = findViewById(R.id.text1);
        textView.setText(result + "");
    }
}