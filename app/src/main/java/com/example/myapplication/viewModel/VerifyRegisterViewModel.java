package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.VerifyRegisterResponse;
import com.example.myapplication.view.MainActivityLogin;
import com.example.myapplication.view.PinviewActivity;

public class VerifyRegisterViewModel extends ViewModel {
    private UserRepository userRepository;
    public Context context;

    public VerifyRegisterViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void verifyRegister(String otp){
        userRepository.verifyRegister(otp, new UserRepository.IVerifyRegisterResponse() {
            @Override
            public void onSuccess(VerifyRegisterResponse verifyRegisterResponse) {
                Toast.makeText(context,verifyRegisterResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainActivityLogin.class));
                ((PinviewActivity) context).finish();
            }

            @Override
            public void onFail(VerifyRegisterResponse verifyRegisterResponse) {
                Toast.makeText(context,verifyRegisterResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
