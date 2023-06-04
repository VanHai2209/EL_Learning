package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.view.PinviewActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.RegisterResponse;

public class RegisterViewModel extends ViewModel {
    private UserRepository userRepository;
    private Context context;
    public RegisterViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public void register(String email, String password, String username, String name, String address, String telephone, String gender, String birthday){
        userRepository.register(email, password, username, name, address, telephone, gender, birthday, new UserRepository.IRegisterResponse(){
            @Override
            public void onSuccess(RegisterResponse registerResponse){
                context.startActivity(new Intent(context, PinviewActivity.class));
                Toast.makeText(context,"We sent otp to "+email, Toast.LENGTH_LONG).show();
                VerifyRegisterViewModel.token = registerResponse.getUserData().getToken();
            }
            @Override
            public void onFail(RegisterResponse registerResponse){
                Toast.makeText(context,registerResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String checkRadio(int checkedId) {
        String stringGender ="-1";
        if (checkedId == -1) {

        }
        else if(checkedId == R.id.radio_male){
            stringGender = "1";
        } else if (checkedId == R.id.radio_female) {
            stringGender = "2";
        }
        return stringGender;
    }
}