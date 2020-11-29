package com.example.apptest.service.Deposit;

import com.example.apptest.Data.Deposit.DepositInfo;
import com.example.apptest.Data.Deposit.DepositReqList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DepositService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @FormUrlEncoded
    @POST("/v2.0/transfer/deposit/fin_num")
    Call<DepositInfo> fetchDeposit(@Header("Authorization") String token,
                                   @Field("cntr_account_type") String cntr_account_type,
                                   @Field("cntr_account_num") String cntr_account_num,
                                   @Field("wd_pass_phrase") String wd_pass_phrase,
                                   @Field("wd_print_content") String wd_print_content,
                                   @Field("name_check_option") String name_check_option,
                                   @Field("tran_dtime") String tran_dtime,
                                   @Field("req_cnt") String req_cnt,
                                   @Field("req_list") DepositReqList req_list);
}
