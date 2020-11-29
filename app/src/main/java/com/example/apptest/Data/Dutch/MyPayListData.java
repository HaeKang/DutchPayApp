package com.example.apptest.Data.Dutch;

public class MyPayListData {
    String name;
    String finnum;
    int money;
    int person;
    String myfinnum;

    public String getMyfinnum() {
        return myfinnum;
    }

    public void setMyfinnum(String myfinnum) {
        this.myfinnum = myfinnum;
    }

    public void setFinnum(String finnum) {
        this.finnum = finnum;
    }

    public String getFinnum() {
        return finnum;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPerson() {
        return person;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
