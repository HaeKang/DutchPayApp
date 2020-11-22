package com.example.apptest.Data.Deposit;

import com.example.apptest.Data.Deposit.Deposit;
import com.squareup.moshi.Json;

import java.util.List;

public class DepositInfo {
    @Json(name = "api_tran_id")
    private String apiTranId;
    @Json(name = "rsp_code")
    private String rspCode;
    @Json(name = "rsp_message")
    private String rspMessage;
    @Json(name = "api_tran_dtm")
    private String apiTranDtm;
    @Json(name = "wd_bank_code_std")
    private String wdBankCodeStd;
    @Json(name = "wd_bank_code_sub")
    private String wdBankCodeSub;
    @Json(name = "wd_bank_name")
    private String wdBankName;
    @Json(name = "wd_account_num_masked")
    private String wdAccountNumMasked;
    @Json(name = "wd_print_content")
    private String wdPrintContent;
    @Json(name = "wd_account_holder_name")
    private String wdAccountHolderName;
    @Json(name = "res_cnt")
    private String resCnt;
    @Json(name = "deposit")
    private List<Deposit> deposit = null;

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

    public String getWdBankCodeStd() {
        return wdBankCodeStd;
    }

    public void setWdBankCodeStd(String wdBankCodeStd) {
        this.wdBankCodeStd = wdBankCodeStd;
    }

    public String getWdBankCodeSub() {
        return wdBankCodeSub;
    }

    public void setWdBankCodeSub(String wdBankCodeSub) {
        this.wdBankCodeSub = wdBankCodeSub;
    }

    public String getWdBankName() {
        return wdBankName;
    }

    public void setWdBankName(String wdBankName) {
        this.wdBankName = wdBankName;
    }

    public String getWdAccountNumMasked() {
        return wdAccountNumMasked;
    }

    public void setWdAccountNumMasked(String wdAccountNumMasked) {
        this.wdAccountNumMasked = wdAccountNumMasked;
    }

    public String getWdPrintContent() {
        return wdPrintContent;
    }

    public void setWdPrintContent(String wdPrintContent) {
        this.wdPrintContent = wdPrintContent;
    }

    public String getWdAccountHolderName() {
        return wdAccountHolderName;
    }

    public void setWdAccountHolderName(String wdAccountHolderName) {
        this.wdAccountHolderName = wdAccountHolderName;
    }

    public String getResCnt() {
        return resCnt;
    }

    public void setResCnt(String resCnt) {
        this.resCnt = resCnt;
    }

    public List<Deposit> getDeposit() {
        return deposit;
    }

    public void setDeposit(List<Deposit> deposit) {
        this.deposit = deposit;
    }

}
