package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.LoginViewModel;
import com.example.myapplication.viewModel.MainActivityViewModel;

public class MainActivityLogin extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private MainActivityViewModel mainActivityViewModel;
    TextView txtForgetPass, txtSignup;
    EditText txtEmail, txtPasswd;
    AppCompatButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        loginViewModel = new LoginViewModel(this);
        mainActivityViewModel = new MainActivityViewModel(this);
        txtForgetPass = findViewById(R.id.txtForgetPass);
        txtSignup = findViewById(R.id.txtSignup);
        txtEmail = findViewById(R.id.txtGmail);
        txtPasswd = findViewById(R.id.txtPassword);
        txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.forgetPass();
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.signup();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginViewModel.checkField(txtEmail.getText().toString(), txtPasswd.getText().toString())){
                    loginViewModel.login(txtEmail.getText().toString(),txtPasswd.getText().toString());
                }
                else{
                    Toast.makeText(MainActivityLogin.this, "Fields must not be left blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}