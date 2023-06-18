package com.example.myapplication.data.api;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.GrammarResponse;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.model.RankResponse;
import com.example.myapplication.model.RegisterResponse;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.TopicResponse;
import com.example.myapplication.model.VerifyRegisterResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<VerifyRegisterResponse> verifyRegister(
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
    @GET("api/getRankListUser")
    Call<RankResponse> getListRank();
    @FormUrlEncoded
    @PUT("api/updateScore")
    Call<ApiResponse> updateScore(
            @Field("idPerson") String idPerson,
            @Field("score") String score);
}
