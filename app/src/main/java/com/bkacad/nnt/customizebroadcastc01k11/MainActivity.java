package com.bkacad.nnt.customizebroadcastc01k11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private Button btnShowDialog;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        btnShowDialog = findViewById(R.id.btnShowDialog);

        myDialog = new MyDialog(this);

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show dialog lên
                myDialog.show();
            }
        });


        // Sự kiện khi click vào button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chủ động tạo ra 1 broadcast
                Intent intent = new Intent();
                intent.setAction("com.bkacad.nnt.DEMO");
                sendBroadcast(intent);
            }
        });

        // Tạo ra 1 broadcast lắng  nghe
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case "com.bkacad.nnt.DEMO":
                        Toast.makeText(MainActivity.this, "Nhận sự kiện tự tạo",Toast.LENGTH_SHORT).show();
                        break;
                    case "com.bkacad.nnt.CANCEL_FROM_DIALOG":
                        Toast.makeText(MainActivity.this,"Thoát từ diloag",Toast.LENGTH_SHORT).show();
                        break;
                    case "com.bkacad.nnt.SEND_FROM_DIALOG":
                        String value = intent.getStringExtra("key");
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.bkacad.nnt.DEMO");
        intentFilter.addAction("com.bkacad.nnt.SEND_FROM_DIALOG");
        intentFilter.addAction("com.bkacad.nnt.CANCEL_FROM_DIALOG");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}