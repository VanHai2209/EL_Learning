package com.example.myapplication.data.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceClient {
    private static String token;

    public static void setToken(String token) {
        ApiServiceClient.token = token;
    }
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {

            if(token != null){
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl("http://192.168.21.109:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
            }
            else {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.21.109:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
            }
        }
        return apiService;
    }
}

