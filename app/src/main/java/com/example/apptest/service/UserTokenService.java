package com.example.apptest.service;

import com.example.apptest.Data.UserTokenInfo;
import com.example.apptest.Data.jwtToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserTokenService {
    String BASE_URL = "http://172.30.1.18:3000";

    @POST("/userTokenInfo")
    Call<UserTokenInfo> fetchUserTokenInfo(@Header("ourtoken") String jwtToken);
}
