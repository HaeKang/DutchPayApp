package com.example.apptest.Data.Deposit;

import com.squareup.moshi.Json;

public class Deposit {
    @Json(name = "tran_no")
    private String tranNo;
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
    @Json(name = "account_holder_name")
    private String accountHolderName;
    @Json(name = "tran_amt")
    private String tranAmt;
    @Json(name = "cms_num")
    private String cmsNum;
    @Json(name = "savings_bank_name")
    private String savingsBankName;

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
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

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getCmsNum() {
        return cmsNum;
    }

    public void setCmsNum(String cmsNum) {
        this.cmsNum = cmsNum;
    }

    public String getSavingsBankName() {
        return savingsBankName;
    }

    public void setSavingsBankName(String savingsBankName) {
        this.savingsBankName = savingsBankName;
    }

}
