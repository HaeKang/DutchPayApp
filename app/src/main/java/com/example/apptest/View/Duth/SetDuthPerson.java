package com.example.apptest.View.Duth;

public class SetDuthPerson {
    String name;
    boolean Join;
    boolean Pay;

    public SetDuthPerson(String name, boolean join, boolean pay){
        this.name = name;
        this.Join = join;
        this.Pay = pay;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setJoin(boolean join) {
        Join = join;
    }

    public boolean getJoin() {
        return Join;
    }

    public void setPay(boolean pay) {
        Pay = pay;
    }

    public boolean getPay() {
        return Pay;
    }
}
