package com.bkacad.nnt.customizebroadcastc01k11;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    private Button btnSend, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_demo);
        btnSend = findViewById(R.id.btnSend);
        btnCancel = findViewById(R.id.btnCancel);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.bkacad.nnt.SEND_FROM_DIALOG");
                intent.putExtra("key","from dialog");
                getContext().sendBroadcast(intent);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.bkacad.nnt.CANCEL_FROM_DIALOG");
                getContext().sendBroadcast(intent);
                dismiss();
            }
        });
    }
}
