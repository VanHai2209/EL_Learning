package com.example.myapplication.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.dialog.DialogChangePass;
import com.example.myapplication.dialog.DialogEditProfile;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.UserData;
import com.example.myapplication.view.fragment.ProfileFragment;

public class FrgProfileViewModel {
    LiveData<GetInforResponse> data;
    DialogEditProfile dialogEditProfile;
    DialogChangePass dialogChangePass;
    Context context;
    UserRepository userRepository;

    public FrgProfileViewModel(Context context){
        this.context = context;
//        this.data = data;
        userRepository = new UserRepository();
    }

    public void changePass(){
        dialogChangePass = new DialogChangePass(context, new DialogChangePass.DialogCallback() {
            @Override
            public void onCancelClicked() {

            }

            @Override
            public void onSaveClicked() {

            }
        });
        dialogChangePass.setCanceledOnTouchOutside(false);
        dialogChangePass.show();
    }
    public void editProfile(){
        dialogEditProfile = new DialogEditProfile(context, data,new DialogEditProfile.DialogCallback() {
            @Override
            public void onCancelClicked() {

            }

            @Override
            public void onSaveClicked() {

            }
        });
        dialogEditProfile.setCanceledOnTouchOutside(false);
        dialogEditProfile.show();
    }
    public String checkGender(String gender){
        if(gender == "1"){
            return "Male";
        }
        else {
            return "Female";
        }
    }
}
