package com.example.apptest.Data;

import com.squareup.moshi.Json;

public class Transfer {
    @Json(name = "tran_date")
    private String tranDate;
    @Json(name = "tran_time")
    private String tranTime;
    @Json(name = "inout_type")
    private String inoutType;
    @Json(name = "tran_type")
    private String tranType;
    @Json(name = "print_content")
    private String printContent;
    @Json(name = "tran_amt")
    private String tranAmt;
    @Json(name = "after_balance_amt")
    private String afterBalanceAmt;
    @Json(name = "branch_name")
    private String branchName;

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getInoutType() {
        return inoutType;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
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

    public String getAfterBalanceAmt() {
        return afterBalanceAmt;
    }

    public void setAfterBalanceAmt(String afterBalanceAmt) {
        this.afterBalanceAmt = afterBalanceAmt;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}