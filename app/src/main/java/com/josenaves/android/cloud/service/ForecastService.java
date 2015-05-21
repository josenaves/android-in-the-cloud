package com.josenaves.android.cloud.service;

import com.josenaves.android.cloud.model.ForecastResponse;

import retrofit.http.GET;
import retrofit.http.Query;

public interface ForecastService {
    @GET("/data/2.5/forecast/daily")
    ForecastResponse getForecast(@Query("q") String query, @Query("units") String units, @Query("cnt") int counter);
}
