package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progreesBar);
        progressBar.setVisibility(View.INVISIBLE);
        btnLogin = findViewById(R.id.btnLogin);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.setContext(this);
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
        loginViewModel.getLoginStateLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(View.INVISIBLE);
                if(aBoolean){
                    Intent intent = new Intent(MainActivityLogin.this, MainActivityHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginViewModel.checkField(txtEmail.getText().toString(), txtPasswd.getText().toString())){
                    progressBar.setVisibility(View.VISIBLE);
                    loginViewModel.login(txtEmail.getText().toString(),txtPasswd.getText().toString());

                }
                else{
                    Toast.makeText(MainActivityLogin.this, "Fields must not be left blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}