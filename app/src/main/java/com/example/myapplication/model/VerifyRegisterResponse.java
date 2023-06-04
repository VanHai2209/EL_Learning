package com.example.myapplication.model;

public class VerifyRegisterResponse {
    private int errCode;
    private String errorMessage;

    public int getErrCode() {
        return errCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
