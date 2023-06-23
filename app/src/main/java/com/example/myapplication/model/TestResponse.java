package com.example.myapplication.model;

import java.util.List;

public class TestResponse extends ApiResponse{
    List<TestModel> listTest;

    public List<TestModel> getListTest() {
        return listTest;
    }

    public void setListTest(List<TestModel> listTest) {
        this.listTest = listTest;
    }
}
