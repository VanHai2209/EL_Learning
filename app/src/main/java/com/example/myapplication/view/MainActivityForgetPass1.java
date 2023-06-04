package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class MainActivityForgetPass1 extends AppCompatActivity {
    AppCompatButton btnConfirmMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_pass1);
        btnConfirmMail = findViewById(R.id.btnConfirmMail);
        btnConfirmMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityForgetPass1.this, MainActivityForgetPass2.class));
            }
        });

    }
}