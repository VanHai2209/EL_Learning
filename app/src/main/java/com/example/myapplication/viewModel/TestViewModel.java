package com.example.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.TestModel;
import com.example.myapplication.model.TestResponse;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestViewModel extends ViewModel {
    private MutableLiveData<TestResponse> data = new MutableLiveData<>();
    public LiveData<TestResponse> getData(){
        return data;
    }
    private UserRepository userRepository;
    public TestViewModel(){
        userRepository = new UserRepository();
    }
    public void getTest(String nameTest,String token_login){
        ApiServiceClient.setToken(token_login);
        userRepository.getTest(nameTest, new UserRepository.IGetTest() {
            @Override
            public void onSuccess(TestResponse testResponse) {
                data.setValue(testResponse);
            }

            @Override
            public void onFail(TestResponse testResponse) {
            }
        });
    }
    public List<TestModel> randomList(List<TestModel> list){
        Collections.shuffle(list);
        return  list;
    }
}
