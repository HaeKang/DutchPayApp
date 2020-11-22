package com.example.apptest.Data.Deposit;

import com.squareup.moshi.Json;

public class DepositReqList {
    @Json(name = "tran_no")
    private String tranNo;
    @Json(name = "bank_tran_id")
    private String bankTranId;
    @Json(name = "fintech_use_num")
    private String fintechUseNum;
    @Json(name = "print_content")
    private String printContent;
    @Json(name = "tran_amt")
    private String tranAmt;
    @Json(name = "req_client_name")
    private String reqClientName;
    @Json(name = "req_client_num")
    private String reqClientNum;
    @Json(name = "req_client_fintech_use_num")
    private String reqClientFintechUseNum;
    @Json(name = "transfer_purpose")
    private String transferPurpose;

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

    public String getFintechUseNum() {
        return fintechUseNum;
    }

    public void setFintechUseNum(String fintechUseNum) {
        this.fintechUseNum = fintechUseNum;
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

    public String getReqClientName() {
        return reqClientName;
    }

    public void setReqClientName(String reqClientName) {
        this.reqClientName = reqClientName;
    }

    public String getReqClientNum() {
        return reqClientNum;
    }

    public void setReqClientNum(String reqClientNum) {
        this.reqClientNum = reqClientNum;
    }

    public String getReqClientFintechUseNum() {
        return reqClientFintechUseNum;
    }

    public void setReqClientFintechUseNum(String reqClientFintechUseNum) {
        this.reqClientFintechUseNum = reqClientFintechUseNum;
    }

    public String getTransferPurpose() {
        return transferPurpose;
    }

    public void setTransferPurpose(String transferPurpose) {
        this.transferPurpose = transferPurpose;
    }

}