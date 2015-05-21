package com.josenaves.android.cloud.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.josenaves.android.cloud.model.ForecastResponse;
import com.josenaves.android.cloud.model.ForecastWeather;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ForecastResponseDeserializer implements JsonDeserializer<ForecastResponse> {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public ForecastResponse deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

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

        return new ForecastResponse(forecastList);
    }
}
