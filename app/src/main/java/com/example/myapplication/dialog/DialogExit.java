package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class DialogExit extends Dialog {
    private DialogCallback callback;
    public DialogExit(Context context, DialogCallback callback){
        super(context);
        this.callback = callback;
    }
    public interface DialogCallback{
        void onCancelClicked();
        void onExitClicked();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_logout);
        TextView notification = findViewById(R.id.txtNotifi);
        notification.setText("Are you sure ?\nAll data in test will be lost !");
        TextView cancel = findViewById(R.id.cancel);
        TextView logout = findViewById(R.id.logout);
        logout.setText("EXIT");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCancelClicked();
                dismiss();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onExitClicked();
                dismiss();
            }
        });
    }
}
