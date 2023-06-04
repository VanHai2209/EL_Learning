package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class DialogEditProfile extends Dialog {
    public DialogEditProfile(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_edit_profile);
    }
}
