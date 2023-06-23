package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class DialogIntroGame extends Dialog {
    private DialogCallback dialogCallback;

    public DialogIntroGame (Context context, DialogCallback dialogCallback){
        super(context);
        this.dialogCallback = dialogCallback;
    }
    public interface DialogCallback{
        public void onCancelClicked();
        public void onReadyClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_intro_game);
        Button btnReady = findViewById(R.id.btnReady);
        Button btnCancel = findViewById(R.id.btnCancel);
        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCallback.onReadyClicked();
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCallback.onCancelClicked();
                dismiss();
            }
        });
    }
}
