package com.example.apptest.service.User;

import com.example.apptest.Data.User.jwtToken;
import com.example.apptest.R;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    String BASE_URL = "http://172.30.1.49:3000";

    @FormUrlEncoded
    @POST("/login")
    Call<jwtToken> fetchUserInfo(@Field("userEmail") String userEmail,
                                 @Field("userPassword") String userPassword);
}
