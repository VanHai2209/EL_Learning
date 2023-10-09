package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class DialogGame extends Dialog {
    private String score;
    private DialogCallback callback;
    public DialogGame(Context context, String score, DialogCallback callback){
        super(context);
        this.callback = callback;
        this.score = score;
    }
    public DialogGame(Context context, DialogCallback dialogCallback){
        super(context);
        this.callback = dialogCallback;
    }
    public interface DialogCallback{
        public void onSaveClicked();
        public void onRePlayClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_game);
        TextView txtScore = findViewById(R.id.txtScore);
        if (score == null){
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
        else {
            txtScore.setText(score);
        }
        Button btnSave = findViewById(R.id.saveScore);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSaveClicked();
                dismiss();
            }
        });
        Button btnReplay = findViewById(R.id.rePlay);
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onRePlayClicked();
                dismiss();
            }
        });
    }
}
