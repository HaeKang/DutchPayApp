package com.example.apptest.Data;

public class testJSON {
    private String client_id;
    private String client_secret;
    private String scope = "oob";
    private String grant_type = "client_credentials";

    testJSON(String client_id, String client_secret){
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getClient_id(){
        return client_id;
    }

    public String getClient_secret(){
        return client_secret;
    }

    public String getScope(){
        return scope;
    }

    public String getGrant_type(){
        return grant_type;
    }
}
