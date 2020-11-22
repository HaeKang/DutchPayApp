package com.example.apptest.service.Withdraw;

import com.example.apptest.Data.Withdraw.Withdraw;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WithdrawService {
    String BASE_URL = "https://testapi.openbanking.or.kr";

    @FormUrlEncoded
    @POST("/v2.0/transfer/withdraw/fin_num")
    Call<Withdraw> fetchWithdraw(@Header("Authorization") String token,
                                 @Field("bank_tran_id") String bank_tran_id,
                                 @Field("cntr_account_type") String cntr_account_type,
                                 @Field("cntr_account_num") String cntr_account_num,
                                 @Field("dps_print_content") String dps_print_content,
                                 @Field("fintech_use_num") String fintech_use_num,
                                 @Field("wd_print_content") String wd_print_content,
                                 @Field("tran_amt") String tran_amt,
                                 @Field("tran_dtime") String tran_dtime,
                                 @Field("req_client_name") String req_client_name,
                                 @Field("req_client_fintech_use_num") String req_client_fintech_use_num,
                                 @Field("req_client_num") String req_client_num,
                                 @Field("transfer_purpose") String transfer_purpose,
                                 @Field("recv_client_name") String recv_client_name,
                                 @Field("recv_client_bank_code") String recv_client_bank_code,
                                 @Field("recv_client_account_num") String recv_client_account_num
                                 );
}
