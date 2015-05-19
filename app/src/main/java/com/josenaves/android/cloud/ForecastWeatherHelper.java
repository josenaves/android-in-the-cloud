package com.josenaves.android.cloud;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar que realiza a requisição ao webservice da OpenWeatherMap
 * @author josenaves
 */
public class ForecastWeatherHelper {

    private static final String TAG = ForecastWeatherHelper.class.getSimpleName();

    public static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/forecast/daily?q=sao+paulo&units=metric&cnt=3";

    /**
     * Acessa o webservice do OpenWeatherMap para obter previsão do tempo.
     * @return String contendo o JSON com as informações
     */
    public List<ForecastWeather> getWeather() {
        List<ForecastWeather> forecastList = new ArrayList<>();
        ForecastWeather forecastWeather;
        String jsonResult;

        try {
            URL url = new URL(API_ENDPOINT);

            // 1 - Cria um novo cliente OkHttp
            OkHttpClient okHttpClient = new OkHttpClient();

            // 2 - Constroe uma nova requisição com a URL
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            // 3 - Executa e obtém a resposta
            Response response = okHttpClient.newCall(request).execute();

            // 4 - Pega o corpo da resposta que contém o JSON
            jsonResult = response.body().string();
            Log.d(TAG, "result = " + jsonResult);

            for (int i = 0; i < 3; i++) {
                forecastWeather = ForecastWeather.getFromJson(jsonResult, i);
                forecastList.add(forecastWeather);
                Log.d(TAG, "ForecastWeather = " + forecastWeather);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error ", e);
        }

        return forecastList;
    }
}
