package com.example.whether;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.whether.Pojo.MyWeather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Полина on 17.11.2016.
 */

public class WFragments extends Fragment {
    private AsyncCallback mAsyncCallback;
    private RetrofitAsyncTask asyncTask;
    private String cityName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachCallback(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachCallback(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.cityName = getArguments().getString("city");
        Log.d(RetrofitAsyncTask.TAG, "onCreate: this is city arguments: " + getArguments().getString("city"));
        startTask();
    }

    private void attachCallback(Context context) {
        if (context instanceof AsyncCallback) {
            mAsyncCallback = (AsyncCallback) context;
        } else {
            throw new IllegalStateException("You aren't implementing AsyncCallback in activity");
        }
    }

    public void startTask() {
        if (asyncTask == null) {
            asyncTask = new RetrofitAsyncTask();
            Log.d(RetrofitAsyncTask.TAG, "startTask: this is cityname: " + cityName);
            asyncTask.execute(cityName);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAsyncCallback = null;
    }


    public class RetrofitAsyncTask extends AsyncTask<String, Void, String> {

        public static final String TAG = "mylogs";

        @Override
        protected String doInBackground(String... params) {

            String temp = null;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                String cityName = params[0];

                for (String str :
                        params) {
                    Log.d(TAG, "doInBackground: this is params: " + str);
                }

                Log.d(TAG, "doInBackground: this is city name " + cityName);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.openweathermap.org")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Service service = retrofit.create(Service.class);

                Call<MyWeather> myWeatherCall = service.myWeather(cityName, Service.appid);


                Response<MyWeather> response = myWeatherCall.execute();

                if (response.isSuccessful()) {
                    MyWeather myWeather = response.body();
                    temp = String.valueOf(myWeather.getMain().getTemp() - 273.15);
                } else {
                    temp = "Error: " + response.code();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "doInBackground: this is temp " + temp);


            return temp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAsyncCallback.getTemp(s);
        }
    }

}
