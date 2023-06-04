package com.example.myapplication.model;

public class ApiResponse {
    private int errCode;
    private String errMessage;

    public int getErrCode() {
        return errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
