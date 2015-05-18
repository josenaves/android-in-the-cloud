package com.josenaves.android.cloud;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta classe representa a previsão do tempo para um determinado dia.
 * Created by josenaves on 5/17/15.
 */
public class Weather {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    private String date;
    private String forecast;
    private double maxTemp;
    private double minTemp;

    public Weather() {
    }

    public static Weather getFromJson(String json, int day) throws Exception {
        Weather weather = new Weather();
        weather.parseJson(json, day);
        return weather;
    }

    private void parseJson(String json, int day) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray array = jsonObject.getJSONArray("list");
        JSONObject jsonDay = array.getJSONObject(day);

        Long timeInMillis = Long.valueOf(jsonDay.getLong("dt")) * 1000l;
        this.date = SDF.format(new Date(timeInMillis));

        JSONObject jsonTemp = jsonDay.getJSONObject("temp");
        this.maxTemp = jsonTemp.getDouble("max");
        this.minTemp = jsonTemp.getDouble("min");

        JSONArray jsonDescription = jsonDay.getJSONArray("weather");
        this.forecast = jsonDescription.getJSONObject(0).getString("description");
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
        return "Weather{" +
                "date='" + date + '\'' +
                ", forecast='" + forecast + '\'' +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }
}
