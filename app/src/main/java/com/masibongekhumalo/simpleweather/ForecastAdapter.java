package com.masibongekhumalo.simpleweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ForecastAdapter extends ArrayAdapter<Forecast> {

    public ForecastAdapter(@NonNull Context context, ArrayList<Forecast>WeatherArrayList) {
        super(context, 0, WeatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Forecast forecast = getItem(position);
        Context context;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_item, parent, false);
        }

        TextView MaxTemp = convertView.findViewById(R.id.txt_maxTemp);
        TextView MinTemp = convertView.findViewById(R.id.txt_minTemp);
        TextView Date = convertView.findViewById(R.id.txt_date);

        MaxTemp.setText(forecast.getMaxTemp());
        MinTemp.setText(forecast.getMinTemp());
        Date.setText(forecast.getDate());

        context = convertView.getContext();
        return convertView;
    }


}
