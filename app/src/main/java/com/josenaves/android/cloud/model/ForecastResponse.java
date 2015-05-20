package com.josenaves.android.cloud.model;

import java.util.List;

public class ForecastResponse {
    private String cod;
    private double message;
    private City city;
    private int cnt;
    private List<Day> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Day> getList() {
        return list;
    }

    public void setList(List<Day> list) {
        this.list = list;
    }
}
