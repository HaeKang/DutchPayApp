package com.example.apptest.Data.Dutch;

import com.squareup.moshi.Json;

public class MyQrData {
    @Json(name = "finnum")
    String finnum;
    @Json(name = "money")
    String money;
    @Json(name = "person")
    String person;

    public void setMoney(String money) {
        this.money = money;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getFinnum() {
        return finnum;
    }

    public void setFinnum(String finnum) {
        this.finnum = finnum;
    }

    public String getMoney() {
        return money;
    }

    public String getPerson() {
        return person;
    }
}
