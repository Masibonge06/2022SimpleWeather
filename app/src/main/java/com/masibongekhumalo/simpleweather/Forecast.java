package com.masibongekhumalo.simpleweather;

public class Forecast {
    String minTemp;
    String maxTemp;
    String Date;
    String Url;
    String Icon;



    public Forecast(String minTemp, String maxTemp, String date, String url, String Icon) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        Date = date;
        Url = url;
        this.Icon=Icon;
    }

    public Forecast() {

    }


    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setUrl(String url) {
        Url = url;
    }


    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getDate() {
        return Date;
    }

    public String getUrl() {
        return Url;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }
}
