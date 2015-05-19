package com.josenaves.android.cloud;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        String jsonResult = null;
        HttpURLConnection urlConnection = null;

        List<ForecastWeather> forecastList = new ArrayList<>();
        ForecastWeather forecastWeather = null;

        try {
            URL url = new URL(API_ENDPOINT);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            jsonResult = readFromTheCloud(urlConnection.getInputStream());
            Log.d(TAG, "result = " + jsonResult);


            for (int i = 0; i < 3; i++) {
                forecastWeather = ForecastWeather.getFromJson(jsonResult, i);
                forecastList.add(forecastWeather);
                Log.d(TAG, "ForecastWeather = " + forecastWeather);
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "Error ", e);

        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
        } catch (Exception e) {
            Log.e(TAG, "Error ", e);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return forecastList;
    }

    // Lê um InputStream e converte para uma String.
    private String readFromTheCloud(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Erro ao fechar o stream", e);
                }
            }
        }

        return sb.toString();
    }
}
