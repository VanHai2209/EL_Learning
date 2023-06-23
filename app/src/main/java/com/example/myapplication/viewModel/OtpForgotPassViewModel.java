package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.view.MainActivityForgetPass2;
import com.example.myapplication.view.MainActivityForgetPass3;

public class OtpForgotPassViewModel extends ViewModel {
    private Context context;
    public static String email;
    private UserRepository userRepository;
    public OtpForgotPassViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void confirmOtp(String otp){
        userRepository.otpForgotPassword(email, otp, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                context.startActivity(new Intent(context, MainActivityForgetPass3.class));
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context, "Otp was wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
