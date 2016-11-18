package com.example.whether;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Полина on 17.11.2016.
 */

public class NewCityActivity extends AppCompatActivity {
    EditText cityName;
    Button addCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);
        cityName = (EditText) findViewById(R.id.newcity);
        addCity = (Button) findViewById(R.id.addcity);
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityProvider.getInstance().recordCity(NewCityActivity.this, cityName.getText().toString());
                finish();
            }
        });
    }
}
