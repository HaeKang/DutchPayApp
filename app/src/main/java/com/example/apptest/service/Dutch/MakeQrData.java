package com.example.apptest.service.Dutch;

import com.example.apptest.Data.Dutch.DutchInsertAns;
import com.example.apptest.Data.Dutch.MyQrData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface MakeQrData {
    String BASE_URL = "http://172.30.1.30:3000";

    @FormUrlEncoded
    @POST("/makeqr")
    Call<List<MyQrData>> fetchQrData (@Field("dutchid") String dutchid);
}
