package com.example.myapplication.viewModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.GrammarResponse;

import java.io.InputStream;

import okhttp3.ResponseBody;

public class GrammarViewModel extends ViewModel {
    private MutableLiveData<Bitmap> imageLiveData = new MutableLiveData<>();

    public LiveData<Bitmap> getImageGrammar() {
        return imageLiveData;
    }
    UserRepository userRepository;
    public GrammarViewModel(){
        userRepository = new UserRepository();
    }
    public void getImage(String grammar, String token){
        ApiServiceClient.setToken(token);
        userRepository.getImage(grammar, new UserRepository.IGetImage() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                InputStream inputStream = responseBody.byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageLiveData.setValue(bitmap);
            }
        });
    }
}
