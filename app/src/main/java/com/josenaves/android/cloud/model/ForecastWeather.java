package com.josenaves.android.cloud.model;

/**
 * Esta classe representa a previsão do tempo para um determinado dia.
 * Created by josenaves on 5/17/15.
 */
public class ForecastWeather {

    private String date;

    private String forecast;
    private double maxTemp;
    private double minTemp;

    public ForecastWeather() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "ForecastWeather{" +
                "date='" + date + '\'' +
                ", forecast='" + forecast + '\'' +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }
}
