package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.view.MainActivityForgetPass1;
import com.example.myapplication.view.MainActivityForgetPass2;

public class ForgotPasswordViewModel extends ViewModel {
    private Context context;
    private UserRepository userRepository;
    public ForgotPasswordViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }

    public void confirmMail(String email){

        userRepository.forgotPassword(email, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse forgotPasswordResponse) {
                context.startActivity(new Intent(context, MainActivityForgetPass2.class));
                ((MainActivityForgetPass1) context).finish();
            }

            @Override
            public void onFail(ApiResponse forgotPasswordResponse) {
                Toast.makeText(context, forgotPasswordResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
