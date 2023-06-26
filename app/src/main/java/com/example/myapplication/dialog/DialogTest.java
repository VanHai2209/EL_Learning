package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class DialogTest extends Dialog {

    private String answer;
    private DialogCallback callback;
    public interface DialogCallback{
        public void onClicked();
    }
    public DialogTest(Context context, String answer, DialogCallback callback){
        super(context);
        this.answer = answer;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        Window window = this.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setAttributes(layoutParams);
        setContentView(R.layout.dialog_test);
        TextView txtAnswer = findViewById(R.id.keyAnswer);
        Button btnGotIt = findViewById(R.id.btnGotIt);
        txtAnswer.setText(answer);
        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClicked();
                dismiss();
            }
        });
    }
}
