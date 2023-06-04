package com.example.myapplication.model;

public class LoginResponse extends ApiResponse{

    UserDataLogin userData;
    private String token;


    // Getter Methods


    public UserDataLogin getUserData() {
        return userData;
    }

    public String getToken() {
        return token;
    }

    // Setter Methods


    public void setUserData(UserDataLogin userData) {
        this.userData = userData;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

