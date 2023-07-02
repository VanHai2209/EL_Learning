package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class DialogIntroGame extends Dialog {
    private DialogCallback dialogCallback;
    private int check;

    public DialogIntroGame (Context context,int check, DialogCallback dialogCallback){
        super(context);
        this.check = check;
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
        LinearLayout layout_intro = findViewById(R.id.layout_intro_game);
        switch (check){
            case 1:
                layout_intro.setBackgroundResource(R.drawable.bgr_dialog_selectword);
                break;
            case 2:
                layout_intro.setBackgroundResource(R.drawable.bgr_dialog_arrangeword);
                break;
            case 3:
                layout_intro.setBackgroundResource(R.drawable.bgr_dialog_completeword);
                break;
            case 4:
                layout_intro.setBackgroundResource(R.drawable.bgr_dialog_solveitout);
                break;
        }
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
