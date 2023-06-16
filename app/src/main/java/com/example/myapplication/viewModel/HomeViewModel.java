package com.example.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.UserData;
import com.example.myapplication.view.fragment.ProfileFragment;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<GetInforResponse> data = new MutableLiveData<>();
    public LiveData<GetInforResponse> getData(){
        return data;
    }
    UserRepository userRepository;
    public HomeViewModel(){
        userRepository = new UserRepository();
    }
    public void getInforUser(String email, String token){
        ApiServiceClient.setToken(token);
        userRepository.getInfor(email,new UserRepository.IGetInforResponse() {
            @Override
            public void onSuccess(GetInforResponse getInforResponse) {
                data.setValue(getInforResponse);
            }

            @Override
            public void onFail(GetInforResponse getInforResponse) {

            }
        });
    }

}
