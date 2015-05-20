package com.josenaves.android.cloud.model;

import java.util.List;

/**
 {
 "dt":1431874800,
 "temp":{
 "day":23.17,
 "min":13.67,
 "max":23.17,
 "night":13.67,
 "eve":19.02,
 "morn":23.17
 },
 "pressure":986.4,
 "humidity":74,
 "weather":[
 {
 "id":801,
 "main":"Clouds",
 "description":"few clouds",
 "icon":"02d"
 }
 ],
 "speed":1.66,
 "deg":149,
 "clouds":12
 }, */
public class Day {
    private long dt;
    private Temperature temp;
    private double pressure;
    private int humidity;
    private List<WeatherDescription> weather;
    private double speed;
    private int deg;
    private int clouds;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<WeatherDescription> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
}
