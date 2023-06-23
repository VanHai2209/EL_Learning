package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.view.MainActivityLogin;
import com.example.myapplication.view.MainActivitySignUp;

public class MainActivityViewModel extends ViewModel {
    private Context context;
    public  MainActivityViewModel(Context context){
        this.context = context;
    }
    public void login(){
        context.startActivity(new Intent(context, MainActivityLogin.class));
    }
    public void signup(){
        context.startActivity(new Intent(context, MainActivitySignUp.class));
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
