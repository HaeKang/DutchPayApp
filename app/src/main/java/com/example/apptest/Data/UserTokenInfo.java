package com.example.apptest.Data;

public class UserTokenInfo {
    private String userSeqNo;
    private String userAccessToken;

    public String getUserSeqNo() {
        return userSeqNo;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public void setUserSeqNo(String userSeqNo) {
        this.userSeqNo = userSeqNo;
    }
}
