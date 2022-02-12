package com.example.myapplication;


import com.example.myapplication.DTOs.WResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/onecall?")
    Call<WResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("exclude") String exclude, @Query("APPID") String app_id);
}