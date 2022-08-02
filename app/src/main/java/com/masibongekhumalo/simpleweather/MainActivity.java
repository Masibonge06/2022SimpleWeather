package com.masibongekhumalo.simpleweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "accuWeatherURL";
    Fragment FiveDayWeather;
    Fragment TideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FiveDayWeather = new FiveDayWeather();
        TideFragment = new TideFragment();

        URL accuWeatherURL = NetworkUtil.buildURL();
        Log.i(TAG, "onCreate: " + accuWeatherURL);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.weatherFrame, FiveDayWeather);
        transaction.replace(R.id.tideFragment, TideFragment);
        transaction.commit();
    }
}