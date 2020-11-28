package com.example.apptest.Data.Dutch;

import com.squareup.moshi.Json;

public class MyDutchListData {
    @Json(name = "dutchid")
    private int dutchid;
    @Json(name = "name")
    private String name;
    @Json(name = "id")
    private String id;
    @Json(name = "finnum")
    private String finnum;

    public int getDutchid() {
        return dutchid;
    }

    public void setDutchid(int dutchid) {
        this.dutchid = dutchid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinnum() {
        return finnum;
    }

    public void setFinnum(String finnum) {
        this.finnum = finnum;
    }

}