package com.example.myapplication.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.SearchWordResponse;

public class FrgMyListViewModel extends ViewModel {
    private MutableLiveData<SearchWordResponse> data = new MutableLiveData<>();
    public LiveData<SearchWordResponse> getData(){
        return data;
    }
    private UserRepository userRepository;

    public FrgMyListViewModel(){
        userRepository = new UserRepository();
    }
    public void listPersonWord(String idPerson, String token){
        ApiServiceClient.setToken(token);
        userRepository.listPersonWord(idPerson, new UserRepository.ISearchWordResponse() {
            @Override
            public void onSuccess(SearchWordResponse searchWordResponse) {
                data.setValue(searchWordResponse);
            }

            @Override
            public void onFail(SearchWordResponse searchWordResponse) {
                data.setValue(searchWordResponse);
            }
        });
    }

}
