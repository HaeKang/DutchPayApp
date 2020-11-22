package com.example.apptest.service.Account;

import com.example.apptest.Data.Account.AccountInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AccountListService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @GET("/v2.0/user/me")
    Call<AccountInfo> fetchAccountInfo(@Header("Authorization") String token,
                                       @Query("user_seq_no") String userSeqNo);
}
