package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.ForgotPasswordViewModel;
import com.example.myapplication.viewModel.OtpForgotPassViewModel;
import com.example.myapplication.viewModel.UpdatePassViewModel;

public class MainActivityForgetPass1 extends AppCompatActivity {
    AppCompatButton btnConfirmMail;
    EditText txtEmail;
    private ForgotPasswordViewModel forgotPasswordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_pass1);
        forgotPasswordViewModel = new ForgotPasswordViewModel(this);
        btnConfirmMail = findViewById(R.id.btnConfirmMail);
        txtEmail = findViewById(R.id.txtEmail_fg);
        btnConfirmMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtpForgotPassViewModel.email = txtEmail.getText().toString();
                UpdatePassViewModel.email = txtEmail.getText().toString();
                forgotPasswordViewModel.confirmMail(txtEmail.getText().toString());
            }
        });

    }
}