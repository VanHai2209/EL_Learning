package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class DialogChangePass extends Dialog {
    public DialogChangePass(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_change_pass);
    }
}
