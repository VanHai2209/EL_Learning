package com.example.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.TopicResponse;

public class TopicViewModel extends ViewModel {
    private MutableLiveData<TopicResponse> data = new MutableLiveData<>();
    public LiveData<TopicResponse> getData(){
        return  data;
    }
    private UserRepository userRepository;
    public TopicViewModel(){
        userRepository = new UserRepository();
    }
    public void listTopic(String token){
        ApiServiceClient.setToken(token);
        userRepository.getListTopic(new UserRepository.IGetListTopic() {
            @Override
            public void onSuccess(TopicResponse topicResponse) {
                Log.d("vanhai", "onSuccess: "+topicResponse.getErrCode());
                data.setValue(topicResponse);
            }
        });
    }
}
