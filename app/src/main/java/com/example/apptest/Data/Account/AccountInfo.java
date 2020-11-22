package com.example.apptest.Data.Account;

import com.example.apptest.Data.Account.Account;
import com.squareup.moshi.Json;

import java.util.List;

// 계좌 목록

public class AccountInfo {

    @Json(name = "api_tran_id")
    private String apiTranId;
    @Json(name = "rsp_code")
    private String rspCode;
    @Json(name = "rsp_message")
    private String rspMessage;
    @Json(name = "api_tran_dtm")
    private String apiTranDtm;
    @Json(name = "user_seq_no")
    private String userSeqNo;
    @Json(name = "user_ci")
    private String userCi;
    @Json(name = "user_name")
    private String userName;
    @Json(name = "res_cnt")
    private String resCnt;
    @Json(name = "res_list")
    private List<Account> accountInfos = null;

    public String getApiTranId() {
        return apiTranId;
    }

    public void setApiTranId(String apiTranId) {
        this.apiTranId = apiTranId;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMessage() {
        return rspMessage;
    }

    public void setRspMessage(String rspMessage) {
        this.rspMessage = rspMessage;
    }

    public String getApiTranDtm() {
        return apiTranDtm;
    }

    public void setApiTranDtm(String apiTranDtm) {
        this.apiTranDtm = apiTranDtm;
    }

    public String getUserSeqNo() {
        return userSeqNo;
    }

    public void setUserSeqNo(String userSeqNo) {
        this.userSeqNo = userSeqNo;
    }

    public String getUserCi() {
        return userCi;
    }

    public void setUserCi(String userCi) {
        this.userCi = userCi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResCnt() {
        return resCnt;
    }

    public void setResCnt(String resCnt) {
        this.resCnt = resCnt;
    }

    public List<Account> getAccountInfo() {
        return accountInfos;
    }

    public void setAccountInfo(List<Account> resList) {
        this.accountInfos = resList;
    }

}