package com.example.whether;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Полина on 17.11.2016.
 */

public class CityProvider {
    private static CityProvider sCityProvider = new CityProvider();
    public static CityProvider getInstance(){
        return sCityProvider;
    }
    public static final String NAME = "citypref";
    private List<String> cities;
    private CityProvider(){
        cities = new ArrayList<>();
    }
    public void recordCity(Context context, String cityName){
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(cityName,cityName);
        editor.commit();
        cities.add(cityName);
    }
    public void restoreCity(Context context){
        cities.clear();
        SharedPreferences preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        for (Map.Entry<String, ?> entry: map.entrySet()){
            cities.add(entry.getKey());
        }
    }
    public List<String> getCities(){
        return cities;
    }


}
