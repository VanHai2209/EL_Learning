package com.example.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.RankResponse;

public class FrgRankViewModel extends ViewModel {
    private MutableLiveData<RankResponse> data = new MutableLiveData<>();
    public LiveData<RankResponse> getData(){
        return  data;
    }
    UserRepository userRepository;
    public FrgRankViewModel(){
        userRepository = new UserRepository();
    }
    public void getListRank(String token_login){
        ApiServiceClient.setToken(token_login);
        userRepository.getListRank(new UserRepository.IGetListRank() {
            @Override
            public void onSuccess(RankResponse rankResponse) {
                data.setValue(rankResponse);
            }

            @Override
            public void onFail(RankResponse rankResponse) {

            }
        });
    }
}
