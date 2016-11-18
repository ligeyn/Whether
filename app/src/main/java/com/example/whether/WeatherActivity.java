package com.example.whether;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Полина on 17.11.2016.
 */

public class WeatherActivity extends AppCompatActivity implements AsyncCallback{

    TextView cityName;
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        cityName = (TextView) findViewById(R.id.city_name_weather);
        temp = (TextView) findViewById(R.id.temp);

        cityName.setText(getIntent().getStringExtra("city"));

        getWeatherFragment();
    }

    public WFragments getWeatherFragment(){
        FragmentManager manager = getSupportFragmentManager();
        WFragments fragment = (WFragments) manager.findFragmentByTag(WFragments.class.getName());

        if (fragment == null){
            fragment = new WFragments();

            Bundle bundle = new Bundle();
            bundle.putString("city", getIntent().getStringExtra("city"));

            Log.d(WFragments.RetrofitAsyncTask.TAG, "getWeatherFragment: this is city: " + getIntent().getStringExtra("city"));

            fragment.setArguments(bundle);

            manager.beginTransaction()
                    .add(fragment, WFragments.class.getName())
                    .commit();
        }
        return fragment;

    }

    @Override
    public void getTemp(String temp) {
        this.temp.setText(temp);
    }
}