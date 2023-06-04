package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class MainActivityForgetPass2 extends AppCompatActivity {
    AppCompatButton btnConfirmCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_pass2);
        btnConfirmCode = findViewById(R.id.btnConfirmCode);
        btnConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityForgetPass2.this, MainActivityForgetPass3.class));
            }
        });
    }
}