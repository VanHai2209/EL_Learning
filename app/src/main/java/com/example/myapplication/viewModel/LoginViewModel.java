package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.MainActivityHome;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.view.MainActivityForgetPass1;
import com.example.myapplication.view.MainActivityLogin;

public class LoginViewModel extends ViewModel {
    private Context context;
    private UserRepository userRepository;
    public LoginViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void login(String email, String password){
        userRepository.login(email, password, new UserRepository.ILoginResponse() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                context.startActivity(new Intent(context, MainActivityHome.class));
//                ((MainActivityLogin) context).finish();
            }

            @Override
            public void onFail(LoginResponse loginResponse) {
                Toast.makeText(context, loginResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void forgetPass(){
        context.startActivity(new Intent(context, MainActivityForgetPass1.class));
    }
}
