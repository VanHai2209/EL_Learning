package com.example.myapplication.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;

public class WordViewModel extends ViewModel {
    private MutableLiveData<String> data = new MutableLiveData<>();
    public LiveData<String> geData(){
        return data;
    }
    private Context context;
    UserRepository userRepository;

    public void setContext(Context context) {
        this.context = context;
    }

    public WordViewModel(){
        userRepository = new UserRepository();
    }
    public void addWordPerson(String idPerson, String idWord, String token ){
        ApiServiceClient.setToken(token);
        userRepository.addWordPerson(idPerson, idWord, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deletePersonWord(String idPerson, String idWord, String token){

        ApiServiceClient.setToken(token);
        userRepository.deletePersonword(idPerson, idWord, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void checkExistPersonWord(String idPerson, String idWord, String token){
        ApiServiceClient.setToken(token);
        userRepository.checkExistPersonWord(idPerson, idWord, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                data.setValue(apiResponse.getErrMessage());
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                data.setValue(apiResponse.getErrMessage());
            }
        });
    }
}
