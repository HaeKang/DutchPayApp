package com.example.apptest.service.Dutch;

import com.example.apptest.Data.Dutch.MyPayListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MyPayList {
    String BASE_URL = "http://172.30.1.30:3000";

    @GET("/mypaylist")
    Call<List<MyPayListData>> fetchMyPayList(@Header("ourtoken") String jwtToken);
}
