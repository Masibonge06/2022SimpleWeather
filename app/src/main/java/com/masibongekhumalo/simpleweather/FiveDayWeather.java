package com.masibongekhumalo.simpleweather;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;


public class FiveDayWeather extends Fragment {

    static FiveDayWeather instance;
    String TAG = "JSONData";

    private FiveDayWeather() {

    }
    ListView listView;
    CardView cardView;
    public static FiveDayWeather getInstance(){
        if(instance == null){
            return new FiveDayWeather();
        }
        return instance;
    }

    public class GetWeatherData extends AsyncTask<URL, Void, String> {
//        String TAG = "JSONData";
        ArrayList<Forecast>dailyweatherForecast = new ArrayList<>();
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String weatherData = null;
            try{
                weatherData = NetworkUtil.getResponse(url);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Log.i(TAG, "do in background");
            return weatherData;
        }
        protected void onPostExecute(String weatherData){
            consumeJSON(weatherData);
            super.onPostExecute(weatherData);
        }
        public void consumeJSON(String weatherData){
            if(dailyweatherForecast!= null){
                dailyweatherForecast.clear();
            }
            if(weatherData!= null){
                try {
                    JSONObject rootWeatherData = new JSONObject(weatherData);
                    JSONArray todayWeather = rootWeatherData.getJSONArray("DailyForecasts");
                    for(int i = 0; i < todayWeather.length(); i++){
                        Forecast individualForecast = new Forecast();
                        JSONObject dailyWeather = todayWeather.getJSONObject(i);
                        String Date = dailyWeather.getString("Date");
                        individualForecast.setDate(Date);

                        JSONObject DailyTemp = dailyWeather.getJSONObject("Temperature");
                        JSONObject MinTemp = DailyTemp.getJSONObject("Minimum");
                        String minTempValue = MinTemp.getString("Value");
                        Log.i(TAG, "Min temp for day is " + minTempValue);
                        individualForecast.setMinTemp(minTempValue);

                        JSONObject MaxTemp = DailyTemp.getJSONObject("Maximum");
                        String maxTempValue = MaxTemp.getString("Value");
                        Log.i(TAG, "Max temp for day is " + maxTempValue);
                        individualForecast.setMinTemp(maxTempValue);

                        JSONObject Day = dailyWeather.getJSONObject("Day");
                        String IconValue = Day.getString("IconPhrase");
                        Log.i(TAG, "The icon phrase will be " + IconValue);
                        individualForecast.setIcon(IconValue);

                      dailyweatherForecast.add(individualForecast);
                      if(dailyweatherForecast!=null){
                       ForecastAdapter adapter = new ForecastAdapter(getContext(),dailyweatherForecast);
                       listView.setAdapter(adapter);
                      }
                    }

                    Log.i(TAG, "consumeJSON" + todayWeather);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_five_day_weather, container, false);
        listView = view.findViewById(R.id.weatherList);
        cardView = view.findViewById(R.id.weatherCardview);

      URL url = NetworkUtil.buildURL();
      Log.i(TAG, "onCreateView " + url);
      new GetWeatherData().execute(url);
      return view;
    }

}