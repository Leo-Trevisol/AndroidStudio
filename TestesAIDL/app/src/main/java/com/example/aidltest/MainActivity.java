package com.example.aidltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    IScanInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentService = new Intent(this, ScannerService.class);
        bindService(intentService,mConnection, Context.BIND_AUTO_CREATE);

        Button bt = findViewById(R.id.button);

        bt.setOnClickListener(view -> {
            scan();
        });
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = IScanInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void scan(){
        EditText edit1 = findViewById(R.id.edit1);
        EditText edit2 = findViewById(R.id.edit2);

        int val1 = Integer.parseInt(edit1.getText().toString());
        int val2 = Integer.parseInt(edit2.getText().toString());

        int result = 0;

        try{
            mService.scan();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}