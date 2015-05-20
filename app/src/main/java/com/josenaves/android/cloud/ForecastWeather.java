package com.josenaves.android.cloud;

import com.josenaves.android.cloud.model.Day;
import com.josenaves.android.cloud.model.ForecastResponse;
import com.josenaves.android.cloud.model.Temperature;

import java.text.SimpleDateFormat;

/**
 * Esta classe representa a previsão do tempo para um determinado dia.
 * Created by josenaves on 5/17/15.
 */
public class ForecastWeather {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    private String date;
    private String forecast;
    private double maxTemp;
    private double minTemp;

    public ForecastWeather() {
    }

    public static ForecastWeather getFromResponse(ForecastResponse response, int day) throws Exception {
        ForecastWeather forecastWeather = new ForecastWeather();

        Day dayAux = response.getList().get(day);
        forecastWeather.date = SDF.format(dayAux.getDt() * 1000l);
        Temperature temp = dayAux.getTemp();
        forecastWeather.maxTemp = temp.getMax();
        forecastWeather.minTemp = temp.getMin();
        forecastWeather.forecast = dayAux.getWeather().get(0).getDescription();

        return forecastWeather;
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
