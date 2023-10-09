package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.view.MainActivity;
import com.example.myapplication.view.MainActivityHome;
import com.example.myapplication.view.MainActivityLogin;
import com.example.myapplication.view.PinviewActivity;

public class VerifyRegisterViewModel extends ViewModel {
    private UserRepository userRepository;
    public Context context;

    public VerifyRegisterViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void verifyRegister(String otp, String token){
        ApiServiceClient.setToken(token);
        userRepository.verifyRegister(otp, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                Toast.makeText(context,apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context,apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                if(apiResponse.getErrMessage().equals("Verify Code Wrong more 3 times, your account don't use OTP in 1 day")){
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }
    public void resendOtp(String email){
        userRepository.resendOtp(email, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                Toast.makeText(context,apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context,apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
