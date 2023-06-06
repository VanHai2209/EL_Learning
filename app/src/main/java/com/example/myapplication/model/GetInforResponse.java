package com.example.myapplication.model;

public class GetInforResponse extends ApiResponse {
    UserData dataUser;
    public void setDataUser(UserData dataUser) {
        this.dataUser = dataUser;
    }
    public UserData getDataUser() {
        return dataUser;
    }

}
