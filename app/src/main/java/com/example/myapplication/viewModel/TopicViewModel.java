package com.example.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.TopicResponse;

public class TopicViewModel extends ViewModel {
    private MutableLiveData<TopicResponse> data = new MutableLiveData<>();
    private MutableLiveData<SearchWordResponse> dataWord = new MutableLiveData<>();
    public LiveData<TopicResponse> getData(){
        return  data;
    }
    public LiveData<SearchWordResponse> getDataWord(){
        return  dataWord;
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
                data.setValue(topicResponse);
            }
        });
    }
    public void searchWordTopic(String topic,String token){
        ApiServiceClient.setToken(token);
        userRepository.searchWordTopic(topic, new UserRepository.ISearchWordResponse() {
            @Override
            public void onSuccess(SearchWordResponse searchWordResponse) {
                dataWord.setValue(searchWordResponse);
            }

            @Override
            public void onFail(SearchWordResponse searchWordResponse) {

            }
        });
    }
}
