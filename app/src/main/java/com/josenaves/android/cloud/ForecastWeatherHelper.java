package com.josenaves.android.cloud;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josenaves.android.cloud.json.ForecastWeatherDeserializer;
import com.josenaves.android.cloud.service.ForecastService;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Classe auxiliar que realiza a requisição ao webservice da OpenWeatherMap
 * @author josenaves
 */
public class ForecastWeatherHelper {

    private static final String TAG = ForecastWeatherHelper.class.getSimpleName();

    public static final String API_ENDPOINT = "http://api.openweathermap.org";

    /**
     * Acessa o webservice do OpenWeatherMap para obter previsão do tempo.
     * @return Lista de objetos ForecastWeater contendo informações do tempo.
     */
    public List<ForecastWeather> getWeather() {
        List<ForecastWeather> forecastList = null;

        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ForecastWeather.class, new ForecastWeatherDeserializer())
                    .create();

            GsonConverter converter = new GsonConverter(gson);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .setConverter(converter)
                    .build();

            ForecastService service = restAdapter.create(ForecastService.class);
            forecastList = service.getForecast("sao+paulo", "metric", 3);

            Log.d(TAG, "forecastList = " + forecastList);

        } catch (Exception e) {
            Log.e(TAG, "Error ", e);
        }

        return forecastList;
    }
}
