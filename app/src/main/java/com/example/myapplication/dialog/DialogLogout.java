package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class DialogLogout extends Dialog {
    private DialogCallback callback;

    public DialogLogout(Context context, DialogCallback callback) {
        super(context);
        this.callback = callback;
    }

    public interface DialogCallback {
        void onCancelClicked();
        void onLogoutClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_logout);
        TextView cancel = findViewById(R.id.cancel);
        TextView logout = findViewById(R.id.logout);
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
                callback.onLogoutClicked();
                dismiss();
            }
        });
    }
}
