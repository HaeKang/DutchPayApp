package com.example.apptest.Data;

import com.squareup.moshi.Json;

public class oobToken {

    @Json(name = "access_token")
    private String accessToken;
    @Json(name = "token_type")
    private String tokenType;
    @Json(name = "expires_in")
    private int expiresIn;
    @Json(name = "scope")
    private String scope;
    @Json(name = "client_use_code")
    private String clientUseCode;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientUseCode() {
        return clientUseCode;
    }

    public void setClientUseCode(String clientUseCode) {
        this.clientUseCode = clientUseCode;
    }
}
