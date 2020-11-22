package com.example.apptest.Data.Withdraw;

import com.squareup.moshi.Json;

public class Withdraw {
    @Json(name = "api_tran_id")
    private String apiTranId;
    @Json(name = "rsp_code")
    private String rspCode;
    @Json(name = "rsp_message")
    private String rspMessage;
    @Json(name = "api_tran_dtm")
    private String apiTranDtm;
    @Json(name = "dps_bank_code_std")
    private String dpsBankCodeStd;
    @Json(name = "dps_bank_code_sub")
    private String dpsBankCodeSub;
    @Json(name = "dps_bank_name")
    private String dpsBankName;
    @Json(name = "dps_account_num_masked")
    private String dpsAccountNumMasked;
    @Json(name = "dps_print_content")
    private String dpsPrintContent;
    @Json(name = "dps_account_holder_name")
    private String dpsAccountHolderName;
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
    @Json(name = "account_alias")
    private String accountAlias;
    @Json(name = "bank_code_std")
    private String bankCodeStd;
    @Json(name = "bank_code_sub")
    private String bankCodeSub;
    @Json(name = "bank_name")
    private String bankName;
    @Json(name = "account_num_masked")
    private String accountNumMasked;
    @Json(name = "print_content")
    private String printContent;
    @Json(name = "tran_amt")
    private String tranAmt;
    @Json(name = "account_holder_name")
    private String accountHolderName;
    @Json(name = "wd_limit_remain_amt")
    private String wdLimitRemainAmt;
    @Json(name = "savings_bank_name")
    private String savingsBankName;

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

    public String getDpsBankCodeStd() {
        return dpsBankCodeStd;
    }

    public void setDpsBankCodeStd(String dpsBankCodeStd) {
        this.dpsBankCodeStd = dpsBankCodeStd;
    }

    public String getDpsBankCodeSub() {
        return dpsBankCodeSub;
    }

    public void setDpsBankCodeSub(String dpsBankCodeSub) {
        this.dpsBankCodeSub = dpsBankCodeSub;
    }

    public String getDpsBankName() {
        return dpsBankName;
    }

    public void setDpsBankName(String dpsBankName) {
        this.dpsBankName = dpsBankName;
    }

    public String getDpsAccountNumMasked() {
        return dpsAccountNumMasked;
    }

    public void setDpsAccountNumMasked(String dpsAccountNumMasked) {
        this.dpsAccountNumMasked = dpsAccountNumMasked;
    }

    public String getDpsPrintContent() {
        return dpsPrintContent;
    }

    public void setDpsPrintContent(String dpsPrintContent) {
        this.dpsPrintContent = dpsPrintContent;
    }

    public String getDpsAccountHolderName() {
        return dpsAccountHolderName;
    }

    public void setDpsAccountHolderName(String dpsAccountHolderName) {
        this.dpsAccountHolderName = dpsAccountHolderName;
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

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public String getBankCodeStd() {
        return bankCodeStd;
    }

    public void setBankCodeStd(String bankCodeStd) {
        this.bankCodeStd = bankCodeStd;
    }

    public String getBankCodeSub() {
        return bankCodeSub;
    }

    public void setBankCodeSub(String bankCodeSub) {
        this.bankCodeSub = bankCodeSub;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumMasked() {
        return accountNumMasked;
    }

    public void setAccountNumMasked(String accountNumMasked) {
        this.accountNumMasked = accountNumMasked;
    }

    public String getPrintContent() {
        return printContent;
    }

    public void setPrintContent(String printContent) {
        this.printContent = printContent;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getWdLimitRemainAmt() {
        return wdLimitRemainAmt;
    }

    public void setWdLimitRemainAmt(String wdLimitRemainAmt) {
        this.wdLimitRemainAmt = wdLimitRemainAmt;
    }

    public String getSavingsBankName() {
        return savingsBankName;
    }

    public void setSavingsBankName(String savingsBankName) {
        this.savingsBankName = savingsBankName;
    }

}
