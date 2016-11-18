package com.example.whether;

import com.example.whether.Pojo.MyWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Полина on 17.11.2016.
 */

public interface Service {
    String appid = "a6e19e5296297364bce1a911d669ccc9";
    @GET("/data/2.5/weather")
    Call<MyWeather> myWeather(@Query("q") String cityName, @Query("appid") String appid);
}
