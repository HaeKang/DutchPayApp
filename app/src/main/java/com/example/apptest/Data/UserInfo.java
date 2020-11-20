package com.example.apptest.Data;

public class UserInfo {
    private String userAccessToken;
    private String userSeqNo;

    public String getUserAccessToken(){
        return this.userAccessToken;
    }

    public String  getUserSeqNo(){
        return this.userSeqNo;
    }

    public void setUserAccessToken(String userAccessToken){
        this.userAccessToken = userAccessToken;
    }

    public void setUserSeqNo(String userSeqNo){
        this.userSeqNo = userSeqNo;
    }
}
