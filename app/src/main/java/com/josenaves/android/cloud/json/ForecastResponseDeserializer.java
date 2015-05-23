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

/**
 * Classe responsável por deserializar o JSON para o modelo de dados da aplicação
 */
public class ForecastResponseDeserializer implements JsonDeserializer<ForecastResponse> {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public ForecastResponse deserialize(JsonElement json,
                                        Type type,
                                        JsonDeserializationContext context) throws JsonParseException {

        List<ForecastWeather> forecastList = new ArrayList<>();

        JsonArray list = json.getAsJsonObject().get("list").getAsJsonArray();

        for (JsonElement e : list) {
            ForecastWeather forecast = new ForecastWeather();
            forecast.setDate(SDF.format(e.getAsJsonObject().get("dt").getAsLong() * 1000l));

            JsonElement temp = e.getAsJsonObject().get("temp");
            forecast.setMinTemp(temp.getAsJsonObject().get("min").getAsDouble());
            forecast.setMaxTemp(temp.getAsJsonObject().get("max").getAsDouble());

            JsonElement weather = e.getAsJsonObject().get("weather");
            JsonElement description =
                    weather.getAsJsonArray().get(0).getAsJsonObject().get("description");

            forecast.setForecast(description.getAsString());
            forecastList.add(forecast);
        }

        return new ForecastResponse(forecastList);
    }
}
