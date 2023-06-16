package com.example.myapplication.viewModel;

import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class AudioViewModel extends ViewModel {
    private MutableLiveData<MediaPlayer> data = new MutableLiveData<>();
    public LiveData<MediaPlayer> getData(){
        return  data;
    }
    UserRepository userRepository;
    public AudioViewModel(){
        userRepository = new UserRepository();
    }
    public void getAudioData(String audio, String token){
        ApiServiceClient.setToken(token);
        userRepository.getAudio(audio, new UserRepository.IGetImage() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {

                    byte[] audioData = responseBody.bytes();
                    File tempFile = File.createTempFile("temp_audio", ".mp3");
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(audioData);
                    fos.close();

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(tempFile.getAbsolutePath());
                    mediaPlayer.prepare();

                    // Gán dữ liệu vào MutableLiveData thông qua setValue
                    data.setValue(mediaPlayer);
                } catch (IOException e) {
                    // Xử lý lỗi khi xử lý dữ liệu audio
                    e.printStackTrace();
                }
            }
        });
    }
}
