package com.example.brookelin.goodstart.weatherapi;

/**
 * Created by aaronn spiewak on 4/20/17.
 */

public class CurrentObservation {


    /* Temperature in F */
    private double temp_f;

    private String relative_humidity;

    /* Basic weather string (e.g. "Partly Cloudy") */
    private String weather;

    private String wind_string;

    public double getTempF() {
        return temp_f;
    }

    public String getRelativeHumidity() {
        return relative_humidity;
    }

    public String getWindString() {
        return wind_string;
    }

    public String getWeather() {
        return weather;
    }
}
