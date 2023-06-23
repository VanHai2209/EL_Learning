package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.view.MainActivityForgetPass1;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> loginStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> getLoginStateLiveData() {
        return loginStateLiveData;
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    SharedPreferences sharedPreferences;
    private UserRepository userRepository;
    public LoginViewModel(){
        userRepository = new UserRepository();
    }
    public void login(String email, String password){
        userRepository.login(email, password, new UserRepository.ILoginResponse() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                loginStateLiveData.setValue(true);
                sharedPreferences = context.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("Token_Login", loginResponse.getToken()).apply();
                sharedPreferences.edit().putString("Email", email).apply();
                sharedPreferences.edit().putString("IdPerson", loginResponse.getUserData().getId()).apply();
            }

            @Override
            public void onFail(LoginResponse loginResponse) {
                loginStateLiveData.setValue(false);
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
