package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class DialogChangePass extends Dialog {
    private DialogCallback callback;
    public DialogChangePass(Context context, DialogCallback callback){
        super(context);
        this.callback = callback;
    }
    public interface DialogCallback{
        void onCancelClicked();
        void onSaveClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_pass);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSaveClicked();
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCancelClicked();
                dismiss();
            }
        });
    }
}
