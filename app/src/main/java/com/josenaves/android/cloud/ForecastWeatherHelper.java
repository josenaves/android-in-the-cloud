package com.josenaves.android.cloud;

import android.util.Log;

import com.josenaves.android.cloud.model.ForecastResponse;
import com.josenaves.android.cloud.service.ForecastService;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

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
        List<ForecastWeather> forecastList = new ArrayList<>();
        ForecastWeather forecastWeather;

        try {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .build();

            ForecastService service = restAdapter.create(ForecastService.class);
            ForecastResponse response = service.getForecast("sao+paulo", "metric", 3);

            Log.d(TAG, "response = " + response);

            for (int i = 0; i < 3; i++) {
                forecastWeather = ForecastWeather.getFromResponse(response, i);
                forecastList.add(forecastWeather);
                Log.d(TAG, "ForecastWeather = " + forecastWeather);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error ", e);
        }

        return forecastList;
    }
}
