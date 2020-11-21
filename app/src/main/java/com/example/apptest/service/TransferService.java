package com.example.apptest.service;

import com.example.apptest.Data.Balance;
import com.example.apptest.Data.Transfer;
import com.example.apptest.Data.TransferInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TransferService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @GET("/v2.0/account/transaction_list/fin_num")
    Call<TransferInfo> fetchTransfer(@Header("Authorization") String token,
                                     @Query("bank_tran_id") String transId,
                                     @Query("fintech_use_num") String finusernum,
                                     @Query("inquiry_type") String inquiry_type,
                                     @Query("inquiry_base") String inquiry_base,
                                     @Query("from_date") String from_date,
                                     @Query("to_date") String to_date,
                                     @Query("sort_order") String sort_order,
                                     @Query("tran_dtime") String nowTime);
}
