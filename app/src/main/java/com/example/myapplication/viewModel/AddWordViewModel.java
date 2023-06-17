package com.example.myapplication.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;

public class AddWordViewModel extends ViewModel {
    private Context context;
    UserRepository userRepository;
    public AddWordViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void addWordPerson(String idPerson, String idWord ){
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
}
