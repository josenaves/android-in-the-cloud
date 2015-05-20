package com.josenaves.android.cloud.model;

/**
 "city":{
 "id":3448439,
 "name":"Sao Paulo",
 "coord":{
 "lon":-46.636108,
 "lat":-23.547501
 },
 "country":"BR",
 "population":0,
 "sys":{
 "population":0
 }
 }, */
public class City {
    private long id;
    private String name;
    private Coord coord;
    private String country;
    private long population;
    private Sys sys;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }
}
