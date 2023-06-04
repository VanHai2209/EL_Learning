package com.example.myapplication.model;

public class RegisterResponse extends ApiResponse{

    UserDataSignup userData;

    public void setUserData(UserDataSignup userData) {
        this.userData = userData;
    }
    public UserDataSignup getUserData(){
        return userData;
    }
}
