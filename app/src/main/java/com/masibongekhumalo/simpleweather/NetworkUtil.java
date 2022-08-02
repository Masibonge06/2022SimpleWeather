package com.masibongekhumalo.simpleweather;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import static  java.lang.System.in;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    private static final String BaseURL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    private static final String API_Key = "K4HUHOJr1xd923OF8HGbB468cY2HCA30";
    private static final String Metric = "metric";
    private static final String PARM_APIKey = "apikey";
    private static final String PARM_Metric = "true";
    static String TAG = "NetworkUtil";

    public static URL buildURL(){
        Uri uri = Uri.parse(BaseURL).buildUpon()
                .appendQueryParameter(PARM_APIKey, API_Key)
                .appendQueryParameter(Metric, PARM_Metric).build();

        URL url = null;
        try{
            url = new URL((uri).toString());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponse(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);

            scanner.useDelimiter("//A");
            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            }
            else{
                Log.i(TAG, "getResponse: " + scanner.next());
                return null;
            }

        } finally {
            httpURLConnection.disconnect();
        }


    }

}
