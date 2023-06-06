package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

public class DialogEditProfile extends Dialog {
    public DialogEditProfile(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_edit_profile);
    }
}
