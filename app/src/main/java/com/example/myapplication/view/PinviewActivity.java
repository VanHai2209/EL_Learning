package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.VerifyRegisterViewModel;

public class PinviewActivity extends AppCompatActivity {
    private VerifyRegisterViewModel verifySignupViewModel;
    AppCompatButton btnVerifySignup;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinview);
        verifySignupViewModel = new VerifyRegisterViewModel(this);
        btnVerifySignup = findViewById(R.id.btnVerifySignup);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);


        btnVerifySignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();
                verifySignupViewModel.verifyRegister(otp);
            }
        });
    }
}