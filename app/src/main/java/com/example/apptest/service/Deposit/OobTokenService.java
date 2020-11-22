package com.example.apptest.service.Deposit;

import com.example.apptest.Data.Token.oobToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OobTokenService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @FormUrlEncoded
    @POST("/oauth/2.0/token")
    Call<oobToken> fetchOobToken(@Header("Content-Type") String Content_Type,
                                 @Field("client_id") String client_id,
                                 @Field("client_secret") String client_secret,
                                 @Field("scope") String scope,
                                 @Field("grant_type") String grant_type);
}
