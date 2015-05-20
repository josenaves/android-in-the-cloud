package com.josenaves.android.cloud.service;

import com.josenaves.android.cloud.ForecastWeather;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface ForecastService {
    @GET("/data/2.5/forecast/daily")
    List<ForecastWeather> getForecast(@Query("q") String query, @Query("units") String units, @Query("cnt") int counter);
}
