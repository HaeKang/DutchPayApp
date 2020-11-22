package com.example.apptest.Data.Transfer;

import com.example.apptest.Data.Transfer.Transfer;
import com.squareup.moshi.Json;

import java.util.List;

public class TransferInfo {
    @Json(name = "api_tran_id")
    private String apiTranId;
    @Json(name = "rsp_code")
    private String rspCode;
    @Json(name = "rsp_message")
    private String rspMessage;
    @Json(name = "api_tran_dtm")
    private String apiTranDtm;
    @Json(name = "bank_tran_id")
    private String bankTranId;
    @Json(name = "bank_tran_date")
    private String bankTranDate;
    @Json(name = "bank_code_tran")
    private String bankCodeTran;
    @Json(name = "bank_rsp_code")
    private String bankRspCode;
    @Json(name = "bank_rsp_message")
    private String bankRspMessage;
    @Json(name = "fintech_use_num")
    private String fintechUseNum;
    @Json(name = "balance_amt")
    private String balanceAmt;
    @Json(name = "page_record_cnt")
    private String pageRecordCnt;
    @Json(name = "next_page_yn")
    private String nextPageYn;
    @Json(name = "befor_inquiry_trace_info")
    private String beforInquiryTraceInfo;
    @Json(name = "bank_name")
    private String bankName;
    @Json(name = "savings_bank_name")
    private String savingsBankName;
    @Json(name = "res_list")
    private List<Transfer> resList = null;

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

    public String getBankTranId() {
        return bankTranId;
    }

    public void setBankTranId(String bankTranId) {
        this.bankTranId = bankTranId;
    }

    public String getBankTranDate() {
        return bankTranDate;
    }

    public void setBankTranDate(String bankTranDate) {
        this.bankTranDate = bankTranDate;
    }

    public String getBankCodeTran() {
        return bankCodeTran;
    }

    public void setBankCodeTran(String bankCodeTran) {
        this.bankCodeTran = bankCodeTran;
    }

    public String getBankRspCode() {
        return bankRspCode;
    }

    public void setBankRspCode(String bankRspCode) {
        this.bankRspCode = bankRspCode;
    }

    public String getBankRspMessage() {
        return bankRspMessage;
    }

    public void setBankRspMessage(String bankRspMessage) {
        this.bankRspMessage = bankRspMessage;
    }

    public String getFintechUseNum() {
        return fintechUseNum;
    }

    public void setFintechUseNum(String fintechUseNum) {
        this.fintechUseNum = fintechUseNum;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public String getPageRecordCnt() {
        return pageRecordCnt;
    }

    public void setPageRecordCnt(String pageRecordCnt) {
        this.pageRecordCnt = pageRecordCnt;
    }

    public String getNextPageYn() {
        return nextPageYn;
    }

    public void setNextPageYn(String nextPageYn) {
        this.nextPageYn = nextPageYn;
    }

    public String getBeforInquiryTraceInfo() {
        return beforInquiryTraceInfo;
    }

    public void setBeforInquiryTraceInfo(String beforInquiryTraceInfo) {
        this.beforInquiryTraceInfo = beforInquiryTraceInfo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSavingsBankName() {
        return savingsBankName;
    }

    public void setSavingsBankName(String savingsBankName) {
        this.savingsBankName = savingsBankName;
    }

    public List<Transfer> getResList() {
        return resList;
    }

    public void setResList(List<Transfer> resList) {
        this.resList = resList;
    }

}