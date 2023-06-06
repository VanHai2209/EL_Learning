package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.OtpForgotPassViewModel;

public class MainActivityForgetPass2 extends AppCompatActivity {
    private OtpForgotPassViewModel otpForgotPassViewModel;
    AppCompatButton btnConfirmCode;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    TextView txtMail;
    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_pass2);
        txtMail = findViewById(R.id.textView7);
        txtMail.setText("Verification code sent to "+email);
        otpForgotPassViewModel = new OtpForgotPassViewModel(this);
        btnConfirmCode = findViewById(R.id.btnConfirmCode);
        otp1 = findViewById(R.id.otp1_fg);
        otp2 = findViewById(R.id.otp2_fg);
        otp3 = findViewById(R.id.otp3_fg);
        otp4 = findViewById(R.id.otp4_fg);
        otp5 = findViewById(R.id.otp5_fg);
        otp6 = findViewById(R.id.otp6_fg);
        btnConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();
                otpForgotPassViewModel.confirmOtp(otp);
            }
        });
    }
}