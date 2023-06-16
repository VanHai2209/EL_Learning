package com.example.myapplication.model;

import java.util.List;

public class TopicResponse extends ApiResponse{
    private List<Topic> list;

    public List<Topic> getList() {
        return list;
    }

    public void setList(List<Topic> list) {
        this.list = list;
    }
}
