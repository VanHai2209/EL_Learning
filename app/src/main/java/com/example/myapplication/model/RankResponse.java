package com.example.myapplication.model;

import java.util.List;

public class RankResponse extends ApiResponse{
    List<RankModel> listUsers;

    public List<RankModel> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<RankModel> listUsers) {
        this.listUsers = listUsers;
    }
}
