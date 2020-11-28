package com.example.apptest.service.Dutch;

import com.example.apptest.Data.Dutch.DutchInsertAns;
import com.example.apptest.Data.User.jwtToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DutchInsert {
    String BASE_URL = "http://172.30.1.30:3000";

    @FormUrlEncoded
    @POST("/dutchinsert")
    Call<DutchInsertAns> fetchInsertDutch(@Header("ourtoken") String jwtToken,
                                          @Field("dutchname") String dutchname,
                                          @Field("finnum") String finnum,
                                          @Field("dutchinfoname") String dutchinfoname,
                                          @Field("money") String money,
                                          @Field("personnum") String personnum,
                                          @Field("personnames") ArrayList<String> personnames

    );
}
