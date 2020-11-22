package com.example.apptest.service.Balance;

import com.example.apptest.Data.Balance.Balance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BalanceService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @GET("/v2.0/account/balance/fin_num")
    Call<Balance> fetchBalance(@Header("Authorization") String token,
                               @Query("bank_tran_id") String transId,
                               @Query("fintech_use_num") String finusernum,
                               @Query("tran_dtime") String nowTime);
}
