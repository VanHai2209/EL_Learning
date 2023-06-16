package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.view.Grammar;
import com.example.myapplication.view.MainActivityLogin;
import com.example.myapplication.view.MainActivitySignUp;
import com.example.myapplication.view.testInfor;

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
