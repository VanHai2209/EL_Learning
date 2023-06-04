package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.MainActivityHome;
import com.example.myapplication.R;
import com.example.myapplication.viewModel.LoginViewModel;

public class MainActivityLogin extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    TextView txtForgetPass;
    EditText txtEmail, txtPasswd;
    AppCompatButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        loginViewModel = new LoginViewModel(this);
        txtForgetPass = findViewById(R.id.txtForgetPass);
        txtEmail = findViewById(R.id.txtGmail);
        txtPasswd = findViewById(R.id.txtPassword);
        txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.forgetPass();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.login(txtEmail.getText().toString(),txtPasswd.getText().toString());
            }
        });
    }
}