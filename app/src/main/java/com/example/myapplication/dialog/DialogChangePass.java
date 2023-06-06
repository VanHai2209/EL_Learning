package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class DialogChangePass extends Dialog {
    public DialogChangePass(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_change_pass);
    }
}
