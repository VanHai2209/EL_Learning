package com.example.myapplication.viewModel;

import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.view.MainActivity;
import com.example.myapplication.view.MainActivityForgetPass3;
import com.example.myapplication.view.MainActivityHome;
import com.example.myapplication.view.MainActivityLogin;

public class UpdatePassViewModel extends ViewModel {
    private Context context;
    public static String email;
    private UserRepository userRepository;
    public UpdatePassViewModel(Context context){
        this.context = context;
        userRepository = new UserRepository();
    }
    public boolean checkConfirmPass(String updatePass,String updatePassConfirm){
       if(updatePass !=null && updatePassConfirm!=null){
           if(updatePass.equals(updatePassConfirm)){
               return true;
           }
           else{
               return false;
           }
       }
       else return false;
    }
    public void updatePass(String password){
        userRepository.updatePassword(email, password, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }

            @Override
            public void onFail(ApiResponse apiResponse) {
                Toast.makeText(context, apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
