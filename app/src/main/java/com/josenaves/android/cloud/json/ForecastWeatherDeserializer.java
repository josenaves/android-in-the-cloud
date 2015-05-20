package com.josenaves.android.cloud.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.josenaves.android.cloud.ForecastWeather;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 "cnt":3,
 "list":[
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
 },
 {
 "dt":1431961200,
 "temp":{
 "day":22.95,
 "min":14.61,
 "max":23.48,
 "night":15.37,
 "eve":19.76,
 "morn":14.61
 },
 "pressure":985.6,
 "humidity":73,
 "weather":[
 {
 "id":800,
 "main":"Clear",
 "description":"sky is clear",
 "icon":"02d"
 }
 ],
 "speed":1.66,
 "deg":175,
 "clouds":8
 },
 {
 "dt":1432047600,
 "temp":{
 "day":22.4,
 "min":17.62,
 "max":22.4,
 "night":17.62,
 "eve":18.54,
 "morn":19.65
 },
 "pressure":982.86,
 "humidity":0,
 "weather":[
 {
 "id":500,
 "main":"Rain",
 "description":"light rain",
 "icon":"10d"
 }
 ],
 "speed":2.27,
 "deg":144,
 "clouds":49,
 "rain":2.95
 }
 ]
 } */
public class ForecastWeatherDeserializer implements JsonDeserializer<List<ForecastWeather>> {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<ForecastWeather> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        List<ForecastWeather> forecastList = new ArrayList<>();

        JsonArray list = json.getAsJsonObject().get("list").getAsJsonArray();

        for (JsonElement e : list) {
            ForecastWeather forecast = new ForecastWeather();
            forecast.setDate(SDF.format(e.getAsJsonObject().get("dt").getAsLong() * 1000l));
            forecast.setMinTemp(e.getAsJsonObject().get("temp").getAsJsonObject().get("min").getAsDouble());
            forecast.setMaxTemp(e.getAsJsonObject().get("temp").getAsJsonObject().get("max").getAsDouble());
            forecast.setForecast(e.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());
            forecastList.add(forecast);
        }

        return forecastList;
    }
}
