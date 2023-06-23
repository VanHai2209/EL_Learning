package com.example.myapplication.data.api;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.GrammarResponse;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.model.RankResponse;
import com.example.myapplication.model.RegisterResponse;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.TestResponse;
import com.example.myapplication.model.TopicResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<ApiResponse> verifyRegister(
            @Field("OTP") String otp);
    @FormUrlEncoded
    @POST("api/resendOTP")
    Call<ApiResponse> resendOtp(
            @Field("email") String email);
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
    @GET("api/InfoUser")
    Call<GetInforResponse> getInforUser(
            @Query("email") String email);
    @GET("api/searchWord")
    Call<SearchWordResponse> searchWord();
    @GET("api/grammar")
    Call<List<Grammar>> listGrammar();
    @GET("api/grammar")
    Call<GrammarResponse> onlyGrammar(
            @Query("grammar") String grammar);
    @GET("images/{imageName}")
    Call<ResponseBody> getImage(@Path("imageName") String imageName);
    @GET("{audioName}")
    Call<ResponseBody> getAudio(@Path("audioName") String audioName);
    @GET("api/list-topic")
    Call<TopicResponse> listTopic();
    @GET("api/list-topic")
    Call<SearchWordResponse> searchWordTopic(
            @Query("topic") String topic);
    @FormUrlEncoded
    @POST("api/addPersonWord")
    Call<ApiResponse> addWordPerson(
            @Field("idPerson") String idPerson,
            @Field("idWord") String idWord);
    @GET("api/listPersonWord")
    Call<SearchWordResponse> listPersonWord(
            @Query("idPerson") String idPerSon);
    @GET("api/checkExistPersonWord")
    Call<ApiResponse> checkExistPersonWord(
            @Query("idPerson") String idPerSon,
            @Query("idWord") String idWord);
    @GET("api/getRankListUser")
    Call<RankResponse> getListRank();
    @GET("api/test")
    Call<TestResponse> getTest(
            @Query("name") String nameTest);
    @FormUrlEncoded
    @PUT("api/updateScore")
    Call<ApiResponse> updateScore(
            @Field("idPerson") String idPerson,
            @Field("score") String score);
    @FormUrlEncoded
    @PUT("api/changePassword")
    Call<ApiResponse> changePassword(
            @Field("email") String email,
            @Field("oldPass") String oldPass,
            @Field("newPass") String newPass);
    @FormUrlEncoded
    @PUT("api/updateUser")
    Call<ApiResponse> updateUser(
            @Field("email") String email,
            @Field("username") String username,
            @Field("name") String name,
            @Field("address") String address,
            @Field("telephone") String telephone,
            @Field("gender") String gender,
            @Field("birthday") String birthday);
    @FormUrlEncoded
    @DELETE("api/deletePersonword")
    Call<ApiResponse> deletePersonword(
            @Field("idPerson") String idPerson,
            @Field("idWord") String idWord);
}
