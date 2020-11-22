package com.example.apptest.service.User;

import com.example.apptest.Data.User.UserTokenInfo;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserTokenService {
    String BASE_URL = "http://172.30.1.2:3000";

    @POST("/userTokenInfo")
    Call<UserTokenInfo> fetchUserTokenInfo(@Header("ourtoken") String jwtToken);
}
