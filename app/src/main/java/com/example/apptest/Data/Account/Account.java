package com.example.apptest.Data.Account;

import com.squareup.moshi.Json;

// 계좌 상세정보

public class Account {
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
    @Json(name = "account_holder_name")
    private String accountHolderName;
    @Json(name = "account_holder_type")
    private String accountHolderType;
    @Json(name = "inquiry_agree_yn")
    private String inquiryAgreeYn;
    @Json(name = "inquiry_agree_dtime")
    private String inquiryAgreeDtime;
    @Json(name = "transfer_agree_yn")
    private String transferAgreeYn;
    @Json(name = "transfer_agree_dtime")
    private String transferAgreeDtime;
    @Json(name = "payer_num")
    private String payerNum;
    @Json(name = "savings_bank_name")
    private String savingsBankName;
    @Json(name = "account_seq")
    private String accountSeq;
    @Json(name = "account_type")
    private String accountType;

    // 잔액
    private String balance;

    public String getBalance(){
        return balance;
    }

    public void setBalance(String balance){
        this.balance = balance;
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

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderType() {
        return accountHolderType;
    }

    public void setAccountHolderType(String accountHolderType) {
        this.accountHolderType = accountHolderType;
    }

    public String getInquiryAgreeYn() {
        return inquiryAgreeYn;
    }

    public void setInquiryAgreeYn(String inquiryAgreeYn) {
        this.inquiryAgreeYn = inquiryAgreeYn;
    }

    public String getInquiryAgreeDtime() {
        return inquiryAgreeDtime;
    }

    public void setInquiryAgreeDtime(String inquiryAgreeDtime) {
        this.inquiryAgreeDtime = inquiryAgreeDtime;
    }

    public String getTransferAgreeYn() {
        return transferAgreeYn;
    }

    public void setTransferAgreeYn(String transferAgreeYn) {
        this.transferAgreeYn = transferAgreeYn;
    }

    public String getTransferAgreeDtime() {
        return transferAgreeDtime;
    }

    public void setTransferAgreeDtime(String transferAgreeDtime) {
        this.transferAgreeDtime = transferAgreeDtime;
    }

    public String getPayerNum() {
        return payerNum;
    }

    public void setPayerNum(String payerNum) {
        this.payerNum = payerNum;
    }

    public String getSavingsBankName() {
        return savingsBankName;
    }

    public void setSavingsBankName(String savingsBankName) {
        this.savingsBankName = savingsBankName;
    }

    public String getAccountSeq() {
        return accountSeq;
    }

    public void setAccountSeq(String accountSeq) {
        this.accountSeq = accountSeq;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}