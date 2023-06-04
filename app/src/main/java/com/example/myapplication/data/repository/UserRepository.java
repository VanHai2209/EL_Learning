package com.example.myapplication.data.repository;
import android.util.Log;

import com.example.myapplication.data.api.ApiService;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.model.RegisterResponse;
import com.example.myapplication.model.VerifyRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserRepository {
    public void login(String username, String password, ILoginResponse loginResponse) {
        ApiService apiService = ApiServiceClient.getApiService();
        Call<LoginResponse> call = apiService.login(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    if(response.body().getErrCode() == 0 ) {
                        loginResponse.onSuccess(response.body());
                    }
                    else {
                        loginResponse.onFail(response.body());
                    }
                } else {
                    // Xử lý khi yêu cầu đăng nhập thất bại
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý khi xảy ra lỗi
            }
        });

    }
    public interface ILoginResponse {
        void onSuccess(LoginResponse loginResponse);
        void onFail(LoginResponse loginResponse);
    }
    public void register(String email, String password, String username, String name, String address, String telephone, String gender, String birthday, IRegisterResponse registerResponse){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<RegisterResponse> call = apiService.register(email, password, username, name, address, telephone, gender,birthday);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {

                    if(response.body().getErrCode() == 0 ) {
                       registerResponse.onSuccess(response.body());
                    }
                    else {
                        registerResponse.onFail((response.body()));
                    }
                } else {
                    // Xử lý khi yêu cầu đăng nhập thất bại
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
    public interface IRegisterResponse{
        void onSuccess(RegisterResponse registerResponse);
        void onFail(RegisterResponse registerResponse);
    }
    public void verifyRegister(String token, String otp,IVerifyRegisterResponse iVerifyRegisterResponse ){
        ApiService apiService = ApiServiceClient.getApiService();
        String bearerToken = "Bearer "+token;
        Call<VerifyRegisterResponse> call = apiService.verifyRegister(bearerToken, otp);
        call.enqueue(new Callback<VerifyRegisterResponse>() {
            @Override
            public void onResponse(Call<VerifyRegisterResponse> call, Response<VerifyRegisterResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getErrCode() == 0 ) {
                        iVerifyRegisterResponse.onSuccess(response.body());
                    }
                    else {
                        iVerifyRegisterResponse.onFail((response.body()));
                    }
                } else {
                    // Xử lý khi yêu cầu đăng nhập thất bại
                }
            }

            @Override
            public void onFailure(Call<VerifyRegisterResponse> call, Throwable t) {

            }
        });

    }
    public interface IVerifyRegisterResponse{
        void onSuccess(VerifyRegisterResponse verifyRegisterResponse);
        void onFail(VerifyRegisterResponse verifyRegisterResponse);
    }
}
