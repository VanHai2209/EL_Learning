package com.example.myapplication.data.repository;

import android.util.Log;

import com.example.myapplication.data.api.ApiService;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.GrammarResponse;
import com.example.myapplication.model.LoginResponse;
import com.example.myapplication.model.RegisterResponse;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.TopicResponse;
import com.example.myapplication.model.VerifyRegisterResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserRepository {
    public void getListTopic(IGetListTopic iGetListTopic){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<TopicResponse> call = apiService.listTopic();
        call.enqueue(new Callback<TopicResponse>() {
            @Override
            public void onResponse(Call<TopicResponse> call, Response<TopicResponse> response) {
                iGetListTopic.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TopicResponse> call, Throwable t) {

            }
        });
    }
    public interface IGetListTopic{
        public void onSuccess(TopicResponse topicResponse);
    }
    public void getAudio(String audioName, IGetImage iGetImage){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<ResponseBody> call = apiService.getAudio(audioName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                iGetImage.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void getImage(String imageName, IGetImage iGetImage){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<ResponseBody> call = apiService.getImage(imageName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                iGetImage.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public interface IGetImage{
        public void onSuccess(ResponseBody responseBody);
    }
    public void getOnlyGrammar(String grammar, IGetOnlyGrammar iGetOnlyGrammar){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<GrammarResponse> call = apiService.onlyGrammar(grammar);
        call.enqueue(new Callback<GrammarResponse>() {
            @Override
            public void onResponse(Call<GrammarResponse> call, Response<GrammarResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getErrCode()==0){
                        iGetOnlyGrammar.onSuccess(response.body());
                    }
                    else iGetOnlyGrammar.onFail(response.body());
                }
            }

            @Override
            public void onFailure(Call<GrammarResponse> call, Throwable t) {

            }
        });
    }
    public interface IGetOnlyGrammar{
        public void onSuccess(GrammarResponse grammarResponse);
        public void onFail(GrammarResponse grammarResponse);
    }
    public void getGrammar(IGetGrammar iGetGrammar){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<List<Grammar>> call = apiService.listGrammar();
        call.enqueue(new Callback<List<Grammar>>() {
            @Override
            public void onResponse(Call<List<Grammar>> call, Response<List<Grammar>> response) {
                iGetGrammar.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Grammar>> call, Throwable t) {

            }
        });
    }
    public interface IGetGrammar{
        public void onSuccess(List<Grammar> listGrammar);

    }
    public void searchWord(ISearchWordResponse iSearchWordResponse){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<SearchWordResponse> call = apiService.searchWord();
        call.enqueue(new Callback<SearchWordResponse>() {
            @Override
            public void onResponse(Call<SearchWordResponse> call, Response<SearchWordResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getErrCode() == 0){
                        iSearchWordResponse.onSuccess(response.body());
                    }
                    else{
                        iSearchWordResponse.onFail(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchWordResponse> call, Throwable t) {

            }
        });
    }
    public interface ISearchWordResponse{
        void onSuccess(SearchWordResponse searchWordResponse);
        void onFail(SearchWordResponse searchWordResponse);
    }
    public void getInfor(String email, IGetInforResponse iGetInforResponse){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<GetInforResponse> call = apiService.getInforUser(email);
        call.enqueue(new Callback<GetInforResponse>() {
            @Override
            public void onResponse(Call<GetInforResponse> call, Response<GetInforResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getErrCode() == 0){
                        iGetInforResponse.onSuccess(response.body());
                    }
                    else {
                        iGetInforResponse.onFail(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetInforResponse> call, Throwable t) {
            }
        });
    }
    public interface IGetInforResponse {
        void onSuccess(GetInforResponse getInforResponse);
        void onFail(GetInforResponse getInforResponse);
    }
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
    public void verifyRegister(String otp,IVerifyRegisterResponse iVerifyRegisterResponse ){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<VerifyRegisterResponse> call = apiService.verifyRegister(otp);
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
    public void forgotPassword(String email,IApiResponse iApiResponse ){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<ApiResponse> call = apiService.forgotPassword(email);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getErrCode() == 0 ) {
                        iApiResponse.onSuccess(response.body());
                    }
                    else {
                        iApiResponse.onFail((response.body()));
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }
    public void otpForgotPassword(String email,String otp,IApiResponse iApiResponse ){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<ApiResponse> call = apiService.otpForgotPassword(email, otp);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getErrCode() == 0 ) {
                        iApiResponse.onSuccess(response.body());
                    }
                    else {
                        iApiResponse.onFail((response.body()));
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }
    public void updatePassword(String email,String password,IApiResponse iApiResponse ){
        ApiService apiService = ApiServiceClient.getApiService();
        Call<ApiResponse> call = apiService.updatePassword(email, password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getErrCode() == 0 ) {
                        iApiResponse.onSuccess(response.body());
                    }
                    else {
                        iApiResponse.onFail((response.body()));
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }
    public interface IApiResponse{
        void onSuccess(ApiResponse apiResponse);
        void onFail(ApiResponse apiResponse);
    }
}
