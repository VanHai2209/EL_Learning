package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.GetInforResponse;

public class testInfor extends AppCompatActivity {
    String email, token;
    SharedPreferences sharedPreferences;
    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_infor);
        sharedPreferences = getSharedPreferences("EL_Learning",MODE_PRIVATE);
        token = sharedPreferences.getString("Token_Login", null);
        email = sharedPreferences.getString("Email", null);
        ApiServiceClient.setToken(token);
        userRepository = new UserRepository();
        userRepository.getInfor(email,new UserRepository.IGetInforResponse() {
            @Override
            public void onSuccess(GetInforResponse getInforResponse) {
            }

            @Override
            public void onFail(GetInforResponse getInforResponse) {

            }
        });
    }
}