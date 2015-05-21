package com.josenaves.android.cloud.model;

import java.util.ArrayList;
import java.util.List;

public class ForecastResponse {

    private List<ForecastWeather> forecastList = new ArrayList<>();

    public ForecastResponse(List<ForecastWeather> forecastList) {
        this.forecastList = forecastList;
    }

    public List<ForecastWeather> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<ForecastWeather> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public String toString() {
        return "ForecastResponse{" +
                "forecastList=" + forecastList +
                '}';
    }
}
