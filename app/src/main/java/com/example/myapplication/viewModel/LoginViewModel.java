package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.view.MainActivityHome;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.view.MainActivityForgetPass1;
import com.example.myapplication.view.MainActivityLogin;
import com.example.myapplication.view.testInfor;

public class LoginViewModel extends ViewModel {
    private Context context;
    SharedPreferences sharedPreferences;
    private UserRepository userRepository;
    public LoginViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void login(String email, String password){
        userRepository.login(email, password, new UserRepository.ILoginResponse() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                Intent intent = new Intent(context, MainActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                sharedPreferences = context.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("Token_Login", loginResponse.getToken()).apply();
                sharedPreferences.edit().putString("Email", email).apply();
                sharedPreferences.edit().putString("IdPerson", loginResponse.getUserData().getId()).apply();
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
    public boolean checkField(String email,String pass){
        if(email !=null && pass!=null){
           return true;
        }
        else return false;
    }
}
