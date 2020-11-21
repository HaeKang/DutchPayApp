package com.example.apptest.service;

import com.example.apptest.Data.AccountInfo;
import com.example.apptest.Data.UserInfo;
import com.example.apptest.Data.jwtToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    String BASE_URL = "http://172.30.1.18:3000";

    @FormUrlEncoded
    @POST("/login")
    Call<jwtToken> fetchUserInfo(@Field("userEmail") String userEmail,
                                 @Field("userPassword") String userPassword);
}
