package com.example.myapplication.data.api;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.model.RegisterResponse;
import com.example.myapplication.model.VerifyRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
public interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(
            @Field("email") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("api/register")
    Call<RegisterResponse> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("name") String name,
            @Field("address") String address,
            @Field("telephone") String telephone,
            @Field("gender") String gender,
            @Field("birthday") String birthday);
    @FormUrlEncoded
    @POST("api/verifyRegister")
    Call<VerifyRegisterResponse> verifyRegister(
            @Header("Authorization") String token,
            @Field("OTP") String otp);
    @FormUrlEncoded
    @POST("api/forgotPassword")
    Call<ApiResponse> forgotPassword(
            @Field("email") String email);
    @FormUrlEncoded
    @POST("api/verifyforgotPasswordOTP")
    Call<ApiResponse> otpForgotPassword(
            @Field("email") String email,
            @Field("OTP") String otp);
    @FormUrlEncoded
    @POST("api/updatePassword")
    Call<ApiResponse> updatePassword(
            @Field("email") String email,
            @Field("password") String password);
}
