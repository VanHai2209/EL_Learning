package com.example.myapplication.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.GrammarResponse;

import java.util.List;

public class FrgGrammarViewModel extends ViewModel {
    private MutableLiveData<List<Grammar>> data = new MutableLiveData<>();
    public LiveData<List<Grammar>> geData(){
        return data;
    }
    UserRepository userRepository;
    public FrgGrammarViewModel(){
        userRepository = new UserRepository();
    }
    public void getGrammar(String token){
        ApiServiceClient.setToken(token);
        userRepository.getGrammar(new UserRepository.IGetGrammar() {
            @Override
            public void onSuccess(List<Grammar> listGrammar) {
                data.setValue(listGrammar);
            }
        });
    }

}
