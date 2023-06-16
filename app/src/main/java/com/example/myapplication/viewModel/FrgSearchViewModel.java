package com.example.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.SearchWordResponse;

public class FrgSearchViewModel extends ViewModel {
    private MutableLiveData<SearchWordResponse> wordData = new MutableLiveData<>();
    public LiveData<SearchWordResponse> getData(){
        return  wordData;
    }
    UserRepository userRepository;
    public FrgSearchViewModel(){
        userRepository = new UserRepository();
    }
    public void listWord(String token){
        ApiServiceClient.setToken(token);
        userRepository.searchWord(new UserRepository.ISearchWordResponse() {
            @Override
            public void onSuccess(SearchWordResponse searchWordResponse) {
                wordData.setValue(searchWordResponse);
            }

            @Override
            public void onFail(SearchWordResponse searchWordResponse) {

            }
        });
    }
}
